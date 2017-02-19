package org.sthapana.aggregation.engine

import com.google.gson.Gson
import com.microsoft.azure.documentdb.{ConnectionPolicy, ConsistencyLevel, Document, DocumentClient}
import com.redis.RedisClient
import org.junit.{Assert, Test}
import org.junit.Assert._
import org.sthapana.aggregation.dataobjects.{TyrionEntity, UpdateEntity}

/**
  * Created by chocoklate on 14/2/17.
  */
class ProcessingEngineTest {
  @Test
  def itShouldUpdateValues() = {
    //given
    val updateEntity = UpdateEntity("27","27001","2700102","270010203","27001020304","1","-1","M","13","-1","02","17")
//    rc.hmset("gradewise:27",Map("suwcount" -> "20","muwcount" -> "20","normalcount" -> "60", "totalcount" -> "100"))
//    rc.hmset("genderwise:27",Map("malecount" -> "64","femalecount" -> "36"))
//    rc.hmset("agewise:27",Map("zerotoonecount" -> "12","onetotwocount" -> "14","twotothreecount" -> "08", "threetofourcount" -> "22",
//      "fourtofivecount" -> "27", "fivetosixcount" -> "5"))
//    rc.hmset("monthwise:27",Map("januarycount" -> "20","februarycount" -> "20","marchcount" -> "60", "aprilcount" -> "100",
//      "maycount" -> "20","junecount" -> "20","julycount" -> "60", "augustcount" -> "100",
//      "septembercount" -> "20","octobercount" -> "20","novembercount" -> "60", "decembercount" -> "100",
//      "currentmonth" -> "02", "currentyear" -> "17"))
//
//    //when
    new ProcessingEngine().updateDB(updateEntity)
//    val graderecord = rc.hmget[String,String]("gradewise:27", "suwcount","muwcount", "normalcount","totalcount").get
//    val agerecord = rc.hmget[String,String]("agewise:27", "zerotoonecount" ,"onetotwocount" ,"twotothreecount" , "threetofourcount" ,
//      "fourtofivecount", "fivetosixcount").get
//    val genderrecord = rc.hmget[String,String]("genderwise:27", "malecount","femalecount").get
//    val monthrecord = rc.hmget[String,String]("monthwise:27", "januarycount","februarycount","marchcount", "aprilcount",
//      "maycount","junecount","julycount", "augustcount",
//      "septembercount","octobercount","novembercount", "decembercount",
//      "currentmonth", "currentyear").get

    //then
    val expectedMUWCount = "21"
    val expectedTotalCount = "101"
    val expectedMaleCount = "65"
    val expectedAgeCount = "15"
    val expectedMonthCount = "21"

    val HOST = "https://epgm.documents.azure.com:443/"
    val MASTER_KEY = "Bhn3zf9QiYGzgJoiJByEHeByBBHkzTsRHnqH0HcNO0shAIyC7yUGEokAsB507XmBJruTBSxMTpHONjG6xlb4Tg=="
    val DATABASE_ID = "thewall"
    val COLLECTION_ID = "tyrion"

    val documentClient = new DocumentClient(HOST,
      MASTER_KEY, ConnectionPolicy.GetDefault(),
      ConsistencyLevel.Session)

    val tyrionEntity = TyrionEntity("dashboard","27","20","20","60",
      "100","64","36","5","4","9","6","2","7","1","2","3","4","5","6",
      "7","8","9","10","11","12","02","17")
//
//    val results2 = documentClient.queryDocuments(
//      "dbs/" + DATABASE_ID + "/colls/" + COLLECTION_ID,
//      "SELECT * FROM logdata  ",
//      null).getQueryIterable().toList()
//    println("--------->results:"+results2.size())
//
//    for(i <- 0 to results2.size()-1)
//        documentClient.deleteDocument(results2.get(i).getSelfLink(), null);
//
//    val results = documentClient.queryDocuments(
//      "dbs/" + DATABASE_ID + "/colls/" + COLLECTION_ID,
//      "SELECT * FROM logdata  ",
//      null).getQueryIterable().toList()
//    println("--------->results:"+results.size())
//
    var someJson = new Gson().toJson(tyrionEntity);
    var someDocument = new Document(someJson)
    documentClient.createDocument("dbs/" + DATABASE_ID + "/colls/" + COLLECTION_ID, someDocument, null,false).getResource();

    val results1 = documentClient.queryDocuments(
      "dbs/" + DATABASE_ID + "/colls/" + COLLECTION_ID,
      "SELECT * FROM tyrion where tyrion.doctype=\"dashboard\" and tyrion.code=\"27\" ",
//      "SELECT * FROM tyrion ",
      null).getQueryIterable().toList()
    println("--------->results:"+results1)

    Assert.assertEquals(expectedMUWCount,results1.get(0).get("muwcount"))
    Assert.assertEquals(expectedTotalCount,results1.get(0).get("totalcount"))
    Assert.assertEquals(expectedMaleCount,results1.get(0).get("malecount"))
    Assert.assertEquals(expectedAgeCount,results1.get(0).get("onetotwocount"))
    Assert.assertEquals(expectedMonthCount,results1.get(0).get("februarycount"))

  }

}

git add src/main/scala/org/sthapana/aggregation/dataobjects/Entity.scala
git add src/main/scala/org/sthapana/aggregation/engine/ProcessingEngine.scala
git add src/main/scala/org/sthapana/aggregation/engine/RedisConnector.scala
git add src/main/scala/org/sthapana/aggregation/utils/AgeWiseConsolidationUtils.scala
git add src/main/scala/org/sthapana/aggregation/utils/CommonUtils.scala
git add src/main/scala/org/sthapana/aggregation/utils/GenderWiseConsolidationUtils.scala
git add src/main/scala/org/sthapana/aggregation/utils/GradeWiseConsolidationUtils.scala
git add src/main/scala/org/sthapana/aggregation/utils/MonthWiseConsolidationUtils.scala
git add src/test/scala/org/sthapana/aggregation/engine/ProcessingEngineTest.scala

