package org.sthapana.aggregation.engine

import com.google.gson.Gson
import com.microsoft.azure.documentdb._
import org.sthapana._
import org.sthapana.aggregation.dataobjects._

import scala.collection.JavaConverters._

class DocumentDbConnector(host: String, masterKey: String, databaseId: String, collectionId: String) {

  lazy val db = documentClient.queryDatabases("SELECT * FROM root r WHERE r.id='" + dbName + "'", null).getQueryIterable.toList.get(0)
  lazy val col: DocumentCollection = documentClient.queryCollections(db.getSelfLink, "SELECT * FROM root r WHERE r.id='" + collectionName + "'", null).getQueryIterable.toList.get(0)
  val documentClient = new DocumentClient(host,
    masterKey, ConnectionPolicy.GetDefault(),
    ConsistencyLevel.Session)

  def getConsolidatedRecord(code: String) = {
    val r = documentClient.queryDocuments(
      col.getSelfLink,
      "SELECT * FROM tyrion where tyrion.doctype=\"dashboard\" and tyrion.code=\"" + code + "\"",
      null).getQueryIterable.asScala.toList
      .maxBy(d => (d.get("currentyear").toString + d.get("currentmonth").toString).toInt)

    (Some(r.getHashMap.asScala.map(x => (x._1, x._2.toString)).toMap),Some(r))
  }

  def updateConsolidatedRecord(old:Document,tyrionEntity:TyrionEntity): Unit = {
    old.set("doctype",tyrionEntity.doctype)
    old.set("code",tyrionEntity.code)
    old.set("suwcount",tyrionEntity.suwcount)
    old.set("muwcount",tyrionEntity.muwcount)
    old.set("normalcount",tyrionEntity.normalcount)
    old.set("totalcount",tyrionEntity.totalcount)
    old.set("malecount",tyrionEntity.malecount)
    old.set("femalecount",tyrionEntity.femalecount)
    old.set("zerotoonecount",tyrionEntity.zerotoonecount)
    old.set("onetotwocount",tyrionEntity.onetotwocount)
    old.set("twotothreecount",tyrionEntity.twotothreecount)
    old.set("threetofourcount",tyrionEntity.threetofourcount)
    old.set("fourtofivecount",tyrionEntity.fourtofivecount)
    old.set("fivetosixcount",tyrionEntity.fivetosixcount)
    old.set("januarycount",tyrionEntity.januarycount)
    old.set("februarycount",tyrionEntity.februarycount)
    old.set("marchcount",tyrionEntity.marchcount)
    old.set("aprilcount",tyrionEntity.aprilcount)
    old.set("maycount",tyrionEntity.maycount)
    old.set("junecount",tyrionEntity.junecount)
    old.set("julycount",tyrionEntity.julycount)
    old.set("augustcount",tyrionEntity.augustcount)
    old.set("septembercount",tyrionEntity.septembercount)
    old.set("octobercount",tyrionEntity.octobercount)
    old.set("novembercount",tyrionEntity.novembercount)
    old.set("decembercount",tyrionEntity.decembercount)
    old.set("currentmonth",tyrionEntity.currentmonth)
    old.set("currentyear",tyrionEntity.currentyear)

    documentClient.replaceDocument(old,null)
  }

  def insertConsolidatedRecord(tyrionEntity: TyrionEntity): Unit = {
    val entityJson = new Gson().toJson(tyrionEntity)
    val entityDocument = new Document(entityJson)
      documentClient.createDocument(col.getSelfLink, entityDocument, null, false).getResource()
    }

}
