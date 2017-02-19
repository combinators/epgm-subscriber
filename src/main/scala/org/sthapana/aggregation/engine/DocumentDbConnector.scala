package org.sthapana.aggregation.engine

import com.google.gson.Gson
import com.microsoft.azure.documentdb.{ConnectionPolicy, ConsistencyLevel, Document, DocumentClient}

import scala.collection.JavaConverters._
import org.sthapana.aggregation.dataobjects._


/**
  * Created by chocoklate on 18/2/17.
  */
class DocumentDbConnector(host:String, masterKey:String, databaseId:String, collectionId:String) {

  val documentClient = new DocumentClient(host,
    masterKey, ConnectionPolicy.GetDefault(),
    ConsistencyLevel.Session)
  var record = List[Document]()

  def extractValue(key: String, record: List[Document]):String = {
    record.head.get(key).asInstanceOf[String]
  }

  def getGradeWiseConsolidatedRecord(code:String): GradeWiseConsolidatedEntity = {
      record = documentClient.queryDocuments(
        "dbs/"+databaseId+"/colls/"+collectionId,
        "SELECT * FROM tyrion where tyrion.doctype=\"dashboard\" and tyrion.code=\""+code+"\"",
        null).getQueryIterable().asScala.toList
    println(record)
      GradeWiseConsolidatedEntity("dashboard", code, extractValue("suwcount", record), extractValue("muwcount", record),
        extractValue("normalcount", record), extractValue("totalcount", record))
    }
//
//    def deleteGradeWiseConsolidatedRecord(code:String): Unit = {
//      documentClient.deleteDocument(record.head.getSelfLink(), null)
//    }
//
//    def insertGradeWiseConsolidatedRecord(gradeWiseConsolidatedEntity: GradeWiseConsolidatedEntity): Unit = {
//      val entityJson = new Gson().toJson(gradeWiseConsolidatedEntity)
//      val entityDocument = new Document(entityJson)
//      documentClient.createDocument(s"dbs/$databaseId/colls/$collectionId",
//        entityDocument, null,false).getResource()
//    }

    def getGenderWiseConsolidatedRecord(code:String): GenderWiseConsolidatedEntity = {
      record = documentClient.queryDocuments(
        s"dbs/$databaseId/colls/$collectionId",
        "SELECT * FROM tyrion where tyrion.doctype=\"dashboard\" and tyrion.code=\""+code+"\"",
        null).getQueryIterable().asScala.toList
      GenderWiseConsolidatedEntity("dashboard", code, extractValue("malecount", record)
        , extractValue("femalecount", record))
    }

//    def deleteGenderWiseConsolidatedRecord(code:String): Unit = {
//      documentClient.deleteDocument(record.head.getSelfLink(), null)
//    }
//
//    def insertGenderWiseConsolidatedRecord(genderWiseConsolidatedEntity: GenderWiseConsolidatedEntity): Unit = {
//      val entityJson = new Gson().toJson(genderWiseConsolidatedEntity)
//      val entityDocument = new Document(entityJson)
//      documentClient.createDocument(s"dbs/$databaseId/colls/$collectionId",
//        entityDocument, null,false).getResource()
//    }

    def getAgeWiseConsolidatedRecord(code:String): AgeWiseConsolidatedEntity = {
      record = documentClient.queryDocuments(
        s"dbs/$databaseId/colls/$collectionId",
        "SELECT * FROM tyrion where tyrion.doctype=\"dashboard\" and tyrion.code=\""+code+"\"",
        null).getQueryIterable().asScala.toList
      AgeWiseConsolidatedEntity("dashboard", code, extractValue("zerotoonecount", record), extractValue("onetotwocount", record),
        extractValue("twotothreecount", record), extractValue("threetofourcount", record),
        extractValue("fourtofivecount", record), extractValue("fivetosixcount", record))
    }

//    def deleteAgeWiseConsolidatedRecord(code:String): Unit = {
//      documentClient.deleteDocument(record.head.getSelfLink(), null)
//    }
//
//    def insertAgeWiseConsolidatedRecord(ageWiseConsolidatedEntity: AgeWiseConsolidatedEntity): Unit = {
//      val entityJson = new Gson().toJson(ageWiseConsolidatedEntity)
//      val entityDocument = new Document(entityJson)
//      documentClient.createDocument(s"dbs/$databaseId/colls/$collectionId",
//        entityDocument, null,false).getResource()
//    }

    def getMonthWiseConsolidatedRecord(code:String): MonthWiseConsolidatedEntity = {
      record = documentClient.queryDocuments(
        s"dbs/$databaseId/colls/$collectionId",
        "SELECT * FROM tyrion where tyrion.doctype=\"dashboard\" and tyrion.code=\""+code+"\"",
        null).getQueryIterable().asScala.toList
      MonthWiseConsolidatedEntity("dashboard", code,
        extractValue("januarycount", record), extractValue("februarycount", record),
        extractValue("marchcount", record), extractValue("aprilcount", record),
        extractValue("maycount", record), extractValue("junecount", record),
        extractValue("julycount", record), extractValue("augustcount", record),
        extractValue("septembercount", record), extractValue("octobercount", record),
        extractValue("novembercount", record), extractValue("decembercount", record),
        extractValue("currentmonth", record), extractValue("currentyear", record))
    }

//    def deleteMonthWiseConsolidatedRecord(code:String): Unit = {
//      documentClient.deleteDocument(record.head.getSelfLink(), null)
//    }
//
//    def insertMonthWiseConsolidatedRecord(monthWiseConsolidatedEntity: MonthWiseConsolidatedEntity): Unit = {
//      val entityJson = new Gson().toJson(monthWiseConsolidatedEntity)
//      val entityDocument = new Document(entityJson)
//      documentClient.createDocument(s"dbs/$databaseId/colls/$collectionId",
//        entityDocument, null,false).getResource()
//    }

      def deleteConsolidatedRecord(code:String): Unit = {
        documentClient.deleteDocument(record.head.getSelfLink(), null)
      }

      def insertConsolidatedRecord(tyrionEntity: TyrionEntity): Unit = {
        val entityJson = new Gson().toJson(tyrionEntity)
        val entityDocument = new Document(entityJson)
        documentClient.createDocument(s"dbs/$databaseId/colls/$collectionId",
          entityDocument, null,false).getResource()
      }

  }
