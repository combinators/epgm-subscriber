package org.sthapana.epgmsubscriber

import java.time.LocalDate
import java.time.temporal.ChronoUnit

import com.google.gson.Gson
import com.ikaee.convertors.conversions._
import com.microsoft.azure.documentdb._
import org.joda.time.DateTime
import org.sthapana.aggregation.dataobjects.UpdateEntity
import org.sthapana.aggregation.engine.ProcessingEngine
import org.sthapana.child.ChildRecord
import org.sthapana.record.validation.validation.Validator
import org.sthapana._

class AzureDocumentDB(host: String, password: String, db: String, collection: String) {
  val LOG_DOC_TYPE = "log"
  val documentClient = new DocumentClient(host, password, ConnectionPolicy.GetDefault(), ConsistencyLevel.Session)

  lazy val dbForLink = documentClient.queryDatabases("SELECT * FROM root r WHERE r.id='" + dbName + "'", null).getQueryIterable.toList.get(0)
  lazy val col: DocumentCollection = documentClient.queryCollections(dbForLink.getSelfLink, "SELECT * FROM root r WHERE r.id='" + collectionName + "'", null).getQueryIterable.toList.get(0)

  def insertRecordInDatabase(record: Record) = {
    val results = documentClient.queryDocuments(col.getSelfLink,
      "SELECT * FROM myCollection where myCollection.doctype=\"log\"  and myCollection.aanganwadicode=\"" + record("aanganwadicode") + "\" and myCollection.childcode=\"" + record("childcode") + "\" order by myCollection.recordnumber DESC",
      null).getQueryIterable().toList

    if (results.size() == 0) {
      insertFirstRecord(record)
    }
    else {
      insertNewRecord(record, results.get(0))
    }

  }

  private def insertNewRecord(logRecord: Record, masterDoc: Document) = {
    val rcordCount: Int = Integer.parseInt(masterDoc.get("recordnumber").toString)
    val currentAge = getAgeInMonths(Integer.parseInt(masterDoc.get("yearofbirth").toString), Integer.parseInt(masterDoc.get("monthofbirth").toString), Integer.parseInt(masterDoc.get("dayofbirth").toString))
    val masterRecord = Map("doctype" -> "log", "age" -> currentAge.toString, "recordnumber" -> (rcordCount + 1).toString, "dayofbirth" -> masterDoc.get("dayofbirth").toString, "monthofbirth" -> masterDoc.get("monthofbirth").toString, "yearofbirth" -> masterDoc.get("yearofbirth").toString, "address" -> masterDoc.get("address").toString, "sex" -> masterDoc.get("sex").toString, "name" -> masterDoc.get("name").toString, "fathername" -> masterDoc.get("fathername").toString, "category" -> masterDoc.get("category").toString)

    val combinedRecord = logRecord ++ masterRecord

    Validator.apply(combinedRecord("statecode"), combinedRecord, masterDoc) match {
      case Left(x) => insert(combinedRecord, LOG_DOC_TYPE, rcordCount.toString)
      case Right(x) => println(DateTime.now()+" Error : Malfarmed record of type "+x._2+" record "+logRecord.values.mkString(""))
    }
    print("new record inserted in log data")
    val recordAsMap = logRecord

    val upd = new UpdateEntity(recordAsMap("statecode"), recordAsMap("districtcode"),
      recordAsMap("projectcode"), recordAsMap("sectorcode"), recordAsMap("aanganwadicode"),
      recordAsMap("whounderweight"), masterDoc.get("sex").toString, currentAge.toString,
      recordAsMap("month"), recordAsMap("year"))
    new ProcessingEngine().updateDB(upd)
  }

  private def insertFirstRecord(record: Record) = {

    def getRecordFromMasterData(): Record = {
      val recordAsMap = record
      val results = documentClient.queryDocuments(col.getSelfLink,
        "SELECT * FROM myCollection where myCollection.doctype=\"child\" and myCollection.aanganwadicode=\"" + recordAsMap("aanganwadicode") + "\" and myCollection.childcode=\"" + recordAsMap("childcode") + "\"",
        null).getQueryIterable().toList;
      if (!results.isEmpty) {

        val currentDoc = results.get(0)
        val age = getAgeInMonths(Integer.parseInt(currentDoc.get("yearofbirth").toString), Integer.parseInt(currentDoc.get("monthofbirth").toString), Integer.parseInt(currentDoc.get("dayofbirth").toString))
        Map("doctype" -> "log", "age" -> age.toString, "dayofbirth" -> currentDoc.get("dayofbirth").toString, "monthofbirth" -> currentDoc.get("monthofbirth").toString, "yearofbirth" -> currentDoc.get("yearofbirth").toString, "recordnumber" -> "1", "address" -> currentDoc.get("address").toString, "category" -> currentDoc.get("category").toString, "sex" -> currentDoc.get("sex").toString, "name" -> currentDoc.get("name").toString, "fathername" -> currentDoc.get("fathername").toString)
      }else
        Map()
    }

    val recordFromMaster = getRecordFromMasterData()
    if(recordFromMaster.nonEmpty) {
      val combinedRecord = record ++ recordFromMaster
      insert(combinedRecord, LOG_DOC_TYPE, "1")

      val combinedRecordAsMap = combinedRecord
      val upd = new UpdateEntity(combinedRecordAsMap("statecode"),
        combinedRecordAsMap("districtcode"),
        combinedRecordAsMap("projectcode"),
        combinedRecordAsMap("sectorcode"),
        combinedRecordAsMap("aanganwadicode"),
        combinedRecordAsMap("whounderweight"),
        combinedRecordAsMap("sex"),
        combinedRecordAsMap("age"),
        combinedRecordAsMap("month"), combinedRecordAsMap("year"))

      new ProcessingEngine().updateDB(upd)
    }else
      println(DateTime.now() +" Error : Master data not found for record :"+record.values.mkString(""))
  }

  def insert(childRecord: ChildRecord, docType: String, recordNumber: String): Document = documentClient.createDocument(
    col.getSelfLink, new Document(new Gson().toJson(Marshaller(childRecord, docType, recordNumber))), null, false)
    .getResource

  private def getAgeInMonths(year: Int, month: Int, day: Int): Long = {
    val start = LocalDate.of(year, month, day)
    val end = LocalDate.now()
    Math.abs(ChronoUnit.MONTHS.between(start, end))
  }

}

object AzureDocumentDB {
  def apply(host: String, password: String, db: String, collection: String): AzureDocumentDB =
    new AzureDocumentDB(host, password, db, collection)
}
