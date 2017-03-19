package org.sthapana.aggregation.engine

import com.google.gson.Gson
import com.microsoft.azure.documentdb.{ConnectionPolicy, ConsistencyLevel, Document, DocumentClient}
import org.sthapana.aggregation.dataobjects._

import scala.collection.JavaConverters._

class DocumentDbConnector(host: String, masterKey: String, databaseId: String, collectionId: String) {

  val documentClient = new DocumentClient(host,
    masterKey, ConnectionPolicy.GetDefault(),
    ConsistencyLevel.Session)

  def getConsolidatedRecord(code: String) = {
    val r = documentClient.queryDocuments(
      "dbs/" + databaseId + "/colls/" + collectionId,
      "SELECT * FROM tyrion where tyrion.doctype=\"dashboard\" and tyrion.code=\"" + code + "\"",
      null).getQueryIterable().asScala.headOption
    println(r.size)
    (r match {
      case None => None
      case Some(x) => Some(x.getHashMap.asScala.map(x => (x._1, x._2.toString)).toMap)
    }, r)
  }

  def deleteConsolidatedRecord(code: String, r: Document): Unit = {
    documentClient.deleteDocument(r.getSelfLink(), null)
  }

  def insertConsolidatedRecord(tyrionEntity: TyrionEntity): Unit = {
    val entityJson = new Gson().toJson(tyrionEntity)
    val entityDocument = new Document(entityJson)
    documentClient.createDocument(s"dbs/$databaseId/colls/$collectionId",
      entityDocument, null, false).getResource()
  }

}
