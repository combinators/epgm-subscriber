package org.sthapana.epgmsubscriber


import java.time.LocalDate
import java.time.temporal.ChronoUnit

import com.google.gson.Gson
import com.microsoft.azure.documentdb._
import org.sthapana.aggregation.dataobjects.UpdateEntity
import org.sthapana.aggregation.engine.ProcessingEngine

class AzureDocumentDB(host: String, password: String,db:String,collection:String) {
  val documentClient = new DocumentClient(host, password, ConnectionPolicy.GetDefault(), ConsistencyLevel.Session)

  def insert(record: Record): Document = documentClient.createDocument(
    "dbs/" + db + "/colls/" + collection, new Document(new Gson().toJson(Marshaller(record))), null, false)
    .getResource

  def getPreviousRecord(record: Record) = {

    val recordAsMap = record.toMap
    val aanganwadiCode = recordAsMap("aanganwadicode")
    val childCode = recordAsMap("childcode")

//    println(aanganwadiCode)
//    println(childCode)

    val results = documentClient.queryDocuments("dbs/" + db + "/colls/" + collection,
      "SELECT * FROM myCollection where myCollection.doctype=\"log\"  and myCollection.aanganwadicode=\"" + aanganwadiCode + "\" and myCollection.childcode=\"" + childCode + "\" order by myCollection.recordnumber DESC",
      null).getQueryIterable().toList;

    if (results.size() == 0) {
      insertFirstRecord(record)
    }
    else {
      insertNewRecord(record, results.get(0))
    }

//
//
//    val results1 = documentClient.queryDocuments("dbs/" + db + "/colls/" + "log_data",
//      //      "SELECT * FROM myCollection",
//      "SELECT * FROM myCollection",
//      null).getQueryIterable().toList;
//
//    print("total size"+results1.size())

  }

  private def insertNewRecord(record: Record, results: Document) = {
    val rcordCount: Int = Integer.parseInt(results.get("recordnumber").toString)
    val currentAge=getAgeInMonths(Integer.parseInt(results.get("yearofbirth").toString),Integer.parseInt(results.get("monthofbirth").toString),Integer.parseInt(results.get("dayofbirth").toString))
    val previousRecord=List(("doctype","log"),("age",currentAge.toString),("recordnumber", (rcordCount + 1).toString),("dayofbirth", results.get("dayofbirth").toString), ("monthofbirth", results.get("monthofbirth").toString), ("yearofbirth", results.get("yearofbirth").toString), ("address", results.get("address").toString), ("sex", results.get("sex").toString), ("name", results.get("name").toString), ("fathername", results.get("fathername").toString),("category",results.get("category").toString))
    println(record ++ previousRecord)
    insert(record ++ previousRecord)
    val recordAsMap=record.toMap
    val upd=new UpdateEntity(recordAsMap("statecode"),recordAsMap("districtcode"),recordAsMap("projectcode"),recordAsMap("sectorcode"),recordAsMap("aanganwadicode"),recordAsMap("whounderweight"),results.get("whounderweight").toString,results.get("sex").toString,currentAge.toString,results.get("age").toString,recordAsMap("month"),recordAsMap("year"))
    new ProcessingEngine().updateDB(upd)


  }




  private def insertFirstRecord(record: Record) = {

    def getRecordFromMasterData(): Record = {

      val recordAsMap=record.toMap


        val results = documentClient.queryDocuments("dbs/" + db + "/colls/" + collection,
                  "SELECT * FROM myCollection where myCollection.doctype=\"child\" and myCollection.aanganwadicode=\""+recordAsMap("aanganwadicode")+"\" and myCollection.childcode=\""+recordAsMap("childcode")+"\"",
          null).getQueryIterable().toList;
          if(!results.isEmpty) {
            val currentDoc = results.get(0)
            val age = getAgeInMonths(Integer.parseInt(currentDoc.get("yearofbirth").toString), Integer.parseInt(currentDoc.get("monthofbirth").toString), Integer.parseInt(currentDoc.get("dayofbirth").toString))
            List(("doctype","log"),("age", age.toString), ("dayofbirth", currentDoc.get("dayofbirth").toString), ("monthofbirth", currentDoc.get("monthofbirth").toString), ("yearofbirth", currentDoc.get("yearofbirth").toString), ("recordnumber", "1"), ("address", currentDoc.get("address").toString), ("category", currentDoc.get("category").toString), ("sex", currentDoc.get("sex").toString), ("name", currentDoc.get("name").toString), ("fathername", currentDoc.get("fathername").toString))
          }
      else List()

    }
    val recordFromMaster=getRecordFromMasterData()
    val combinedRecord=record++recordFromMaster
    insert(combinedRecord)
    println("-----> record Inserted : "+combinedRecord)
    val combinedRecordAsMap=combinedRecord.toMap
    val upd=new UpdateEntity(combinedRecordAsMap("statecode"),combinedRecordAsMap("districtcode"),combinedRecordAsMap("projectcode"),combinedRecordAsMap("sectorcode"),combinedRecordAsMap("aanganwadicode"),combinedRecordAsMap("whounderweight"),"-1",combinedRecordAsMap("sex"),combinedRecordAsMap("age"),"-1",combinedRecordAsMap("month"),combinedRecordAsMap("year"))
    new ProcessingEngine().updateDB(upd)
  }



  private def getAgeInMonths(year:Int,month:Int,day:Int) : Long={
    val start = LocalDate.of(year, month, day)
    val end = LocalDate.now()
    Math.abs(ChronoUnit.MONTHS.between(start, end))
  }


}

object AzureDocumentDB {
  def apply(host: String, password: String,db:String,collection:String): AzureDocumentDB =
    new AzureDocumentDB(host,password,db,collection)
}
