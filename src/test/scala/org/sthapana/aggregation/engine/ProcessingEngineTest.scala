package org.sthapana.aggregation.engine

import com.redis.RedisClient
import org.junit.{Assert, Test}
import org.junit.Assert._
import org.sthapana.aggregation.dataobjects.UpdateEntity

/**
  * Created by chocoklate on 14/2/17.
  */
class ProcessingEngineTest {

  val rc = new RedisClient("localhost",6379)

  @Test
  def itShouldUpdateValues() = {
    //given
    val updateEntity = UpdateEntity("27","27001","2700102","270010203","27001020304","1","-1","M","13","-1","02","17")
    rc.hmset("gradewise:27",Map("suwcount" -> "20","muwcount" -> "20","normalcount" -> "60", "totalcount" -> "100"))
    rc.hmset("genderwise:27",Map("malecount" -> "64","femalecount" -> "36"))
    rc.hmset("agewise:27",Map("zerotoonecount" -> "12","onetotwocount" -> "14","twotothreecount" -> "08", "threetofourcount" -> "22",
      "fourtofivecount" -> "27", "fivetosixcount" -> "5"))
    rc.hmset("monthwise:27",Map("januarycount" -> "20","februarycount" -> "20","marchcount" -> "60", "aprilcount" -> "100",
      "maycount" -> "20","junecount" -> "20","julycount" -> "60", "augustcount" -> "100",
      "septembercount" -> "20","octobercount" -> "20","novembercount" -> "60", "decembercount" -> "100",
      "currentmonth" -> "60", "currentyear" -> "100"))

    //when
    new ProcessingEngine().updateDB(updateEntity)
    val graderecord = rc.hmget[String,String]("gradewise:27", "suwcount","muwcount", "normalcount","totalcount").get
    val agerecord = rc.hmget[String,String]("agewise:27", "zerotoonecount" ,"onetotwocount" ,"twotothreecount" , "threetofourcount" ,
      "fourtofivecount", "fivetosixcount").get
    val genderrecord = rc.hmget[String,String]("genderwise:27", "malecount","femalecount").get
    val monthrecord = rc.hmget[String,String]("monthwise:27", "januarycount","februarycount","marchcount", "aprilcount",
      "maycount","junecount","julycount", "augustcount",
      "septembercount","octobercount","novembercount", "decembercount",
      "currentmonth", "currentyear").get

    //then
    val expectedMUWCount = "21"
    val expectedTotalCount = "101"
    val expectedMaleCount = "65"
    val expectedAgeCount = "15"
    val expectedMonthCount = "21"
    Assert.assertEquals(expectedMUWCount,graderecord.get("muwcount").get)
    Assert.assertEquals(expectedTotalCount,graderecord.get("totalcount").get)
    Assert.assertEquals(expectedMaleCount,genderrecord.get("malecount").get)
    Assert.assertEquals(expectedAgeCount,agerecord.get("onetotwocount").get)
    Assert.assertEquals(expectedMonthCount,monthrecord.get("februarycount").get)
  }

}
