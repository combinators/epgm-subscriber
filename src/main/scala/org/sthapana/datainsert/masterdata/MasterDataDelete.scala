package org.sthapana.datainsert.masterdata

import com.google.gson.Gson
import com.microsoft.azure.documentdb.{ConnectionPolicy, ConsistencyLevel, Document, DocumentClient}
import org.sthapana.aggregation.dataobjects.TyrionEntity
import org.sthapana.{collectionName, dbHost, dbName, dbPassword}

import scala.collection.JavaConverters._

/**
  * Created by kirankumarbs on 19/4/17.
  */
object MasterDataDelete {

  val documentClient = new DocumentClient(dbHost,
    dbPassword, ConnectionPolicy.GetDefault(),
    ConsistencyLevel.Session)

}

object DeleteAllLogData extends App{
import MasterDataDelete._
  val documents = documentClient.queryDocuments(s"dbs/thewall/colls/tyrion",
    "SELECT * FROM tyrion where tyrion.doctype = \"log\"",null)
    .getQueryIterable.asScala

  documents.foreach(document => {
    documentClient.deleteDocument(document.getSelfLink, null)
  })

}

object DeleteAllLog_DashboardDataAndInsertDashboardDataState_27 extends App{
  import MasterDataDelete._
  val documentsLog = documentClient.queryDocuments(s"dbs/thewall/colls/tyrion",
    "SELECT * FROM tyrion where tyrion.doctype in (\"log\") and tyrion.statecode = \"27\"",null)
    .getQueryIterable.asScala

  documentsLog.foreach(document => {
    documentClient.deleteDocument(document.getSelfLink, null)
  })

  val documentsDashboard = documentClient.queryDocuments(s"dbs/thewall/colls/tyrion",
    "SELECT * FROM tyrion where tyrion.doctype in (\"dashboard\") and tyrion.code = \"27\"",null)
    .getQueryIterable.asScala

  documentsDashboard.foreach(document => {
    documentClient.deleteDocument(document.getSelfLink, null)
  })


  val tyrionEntity = TyrionEntity("dashboard","27","0","0","0",
    "0","0","0","0","0","0","0",
    "0","0","0","0","0","0","0","0",
    "0","0","0","0","0","0","01","17")

  val entityJson = new Gson().toJson(tyrionEntity)
  val entityDocument = new Document(entityJson)
  documentClient.createDocument(s"dbs/$dbName/colls/$collectionName",
    entityDocument, null,false).getResource()



}

object DeleteAllLog_DashboardDataAndInsertDashboardDataState_98 extends App{
  import MasterDataDelete._
  val documentsLog = documentClient.queryDocuments(s"dbs/thewall/colls/tyrion",
    "SELECT * FROM tyrion where tyrion.doctype in (\"log\") and tyrion.statecode = \"98\"",null)
    .getQueryIterable.asScala

  documentsLog.foreach(document => {
    documentClient.deleteDocument(document.getSelfLink, null)
  })

  val documentsDashboard = documentClient.queryDocuments(s"dbs/thewall/colls/tyrion",
    "SELECT * FROM tyrion where tyrion.doctype in (\"dashboard\") and tyrion.code = \"98\"",null)
    .getQueryIterable.asScala

  documentsDashboard.foreach(document => {
    documentClient.deleteDocument(document.getSelfLink, null)
  })


  val tyrionEntity = TyrionEntity("dashboard","98","0","0","0",
    "0","0","0","0","0","0","0",
    "0","0","0","0","0","0","0","0",
    "0","0","0","0","0","0","01","17")

  val entityJson = new Gson().toJson(tyrionEntity)
  val entityDocument = new Document(entityJson)
  documentClient.createDocument(s"dbs/$dbName/colls/$collectionName",
    entityDocument, null,false).getResource()



}

object DeleteDashboardData extends App{
  import MasterDataDelete._
  val documents = documentClient.queryDocuments(s"dbs/thewall/colls/tyrion",
    "SELECT * FROM tyrion where tyrion.doctype = \"dashboard\"",null)
    .getQueryIterable.asScala

  documents.foreach(document => {
    documentClient.deleteDocument(document.getSelfLink, null)
  })

}