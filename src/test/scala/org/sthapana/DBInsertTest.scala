package org.sthapana

import com.google.gson.Gson
import com.microsoft.azure.documentdb._
import org.junit.Test
import org.sthapana.aggregation.dataobjects.{TyrionEntity, UpdateEntity}
import org.sthapana.aggregation.engine.ProcessingEngine

class DBInsertTest {

  @Test
  def testInsertAzureDocDB(): Unit ={
    //given
     /*
    val documentClient = new DocumentClient(dbHost, dbPassword, ConnectionPolicy.GetDefault(), ConsistencyLevel.Session)
    val db = documentClient.queryDatabases("SELECT * FROM root r WHERE r.id='" + dbName + "'",null).getQueryIterable.toList.get(0)

    val col: DocumentCollection = documentClient.queryCollections(db.getSelfLink,"SELECT * FROM root r WHERE r.id='" + collectionName + "'",null).getQueryIterable.toList.get(0)
    val tyrionEntity = TyrionEntity("dashboard","27","20","20","60",
      "100","64","36","5","4","9","6","2","7","1","2","3","4","5","6",
      "7","8","9","10","11","12","02","17")
    val entityJson = new Gson().toJson(tyrionEntity)
    val entityDocument = new Document(entityJson)
    //when
    documentClient.createDocument(col.getSelfLink,entityDocument,null,false)*/
     val updateEntityWithOutMonthChange: UpdateEntity = UpdateEntity("27", "", "", "", "", "2", "0", "M", "5", "4", "03", "17")
     new ProcessingEngine().updateDB(updateEntityWithOutMonthChange)
    //then
  }
}
