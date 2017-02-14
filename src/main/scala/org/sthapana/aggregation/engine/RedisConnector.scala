package org.sthapana.aggregation.engine

import com.redis.RedisClient
import org.sthapana.aggregation.dataobjects.{AgeWiseConsolidatedEntity, GenderWiseConsolidatedEntity, GradeWiseConsolidatedEntity, MonthWiseConsolidatedEntity}

/**
  * Created by chocoklate on 14/2/17.
  */
case class RedisConnector(hostName:String, portNumber:Int) {

  val redisClient: RedisClient = new RedisClient(hostName, portNumber)

  def extractValue(key: String, record: Option[Map[String, String]]) = {
    record.get.get(key).get
  }

  def getGradeWiseConsolidatedRecord(code:String): GradeWiseConsolidatedEntity = {
    val record = redisClient.hmget[String,String]("gradewise:" + code,
      "suwcount", "muwcount", "normalcount", "totalcount")
    GradeWiseConsolidatedEntity(code, extractValue("suwcount", record), extractValue("muwcount", record),
      extractValue("normalcount", record), extractValue("totalcount", record))
  }

  def deleteGradeWiseConsolidatedRecord(code:String): Unit = {
    redisClient.del("gradewise:" + code)
  }

  def insertGradeWiseConsolidatedRecord(gradeWiseConsolidatedEntity: GradeWiseConsolidatedEntity): Unit = {
    redisClient.hmset("gradewise:"+gradeWiseConsolidatedEntity.code,
      Map("suwcount" -> gradeWiseConsolidatedEntity.suw, "muwcount" -> gradeWiseConsolidatedEntity.muw,
        "normalcount" -> gradeWiseConsolidatedEntity.normal, "totalcount" -> gradeWiseConsolidatedEntity.total))
  }

  def getGenderWiseConsolidatedRecord(code:String): GenderWiseConsolidatedEntity = {
    val record = redisClient.hmget[String,String]("genderwise:" + code,
      "malecount", "femalecount")
    GenderWiseConsolidatedEntity(code, extractValue("malecount", record)
      , extractValue("femalecount", record))
  }

  def deleteGenderWiseConsolidatedRecord(code:String): Unit = {
    redisClient.del("genderwise:" + code)
  }

  def insertGenderWiseConsolidatedRecord(genderWiseConsolidatedEntity: GenderWiseConsolidatedEntity): Unit = {
    redisClient.hmset("genderwise:"+genderWiseConsolidatedEntity.code,
      Map("malecount" -> genderWiseConsolidatedEntity.male, "femalecount" -> genderWiseConsolidatedEntity.female))
  }

  def getAgeWiseConsolidatedRecord(code:String): AgeWiseConsolidatedEntity = {
    val record = redisClient.hmget[String,String]("agewise:" + code,
      "zerotoonecount", "onetotwocount", "twotothreecount", "threetofourcount", "fourtofivecount", "fivetosixcount")
    AgeWiseConsolidatedEntity(code, extractValue("zerotoonecount", record), extractValue("onetotwocount", record),
      extractValue("twotothreecount", record), extractValue("threetofourcount", record),
      extractValue("fourtofivecount", record), extractValue("fivetosixcount", record))
  }

  def deleteAgeWiseConsolidatedRecord(code:String): Unit = {
    redisClient.del("agewise:" + code)
  }

  def insertAgeWiseConsolidatedRecord(ageWiseConsolidatedEntity: AgeWiseConsolidatedEntity): Unit = {
    redisClient.hmset("agewise:"+ageWiseConsolidatedEntity.code,
      Map("zerotoonecount" -> ageWiseConsolidatedEntity.zeroToOne, "onetotwocount" -> ageWiseConsolidatedEntity.oneToTwo,
        "twotothreecount" -> ageWiseConsolidatedEntity.twoToThree, "threetofourcount" -> ageWiseConsolidatedEntity.threeToFour,
      "fourtofivecount" -> ageWiseConsolidatedEntity.fourToFive, "fivetosixcount" -> ageWiseConsolidatedEntity.fiveToSix))
  }

  def getMonthWiseConsolidatedRecord(code:String): MonthWiseConsolidatedEntity = {
    val record = redisClient.hmget[String,String]("monthwise:" + code,
      "januarycount", "februarycount", "marchcount", "aprilcount", "maycount", "junecount",
      "julycount", "augustcount", "septembercount", "octobercount", "novembercount", "decembercount")
    MonthWiseConsolidatedEntity(code,
      extractValue("januarycount", record), extractValue("februarycount", record),
      extractValue("marchcount", record), extractValue("aprilcount", record),
      extractValue("maycount", record), extractValue("junecount", record),
      extractValue("julycount", record), extractValue("augustcount", record),
      extractValue("septembercount", record), extractValue("octobercount", record),
      extractValue("novembercount", record), extractValue("decembercount", record))
  }

  def deleteMonthWiseConsolidatedRecord(code:String): Unit = {
    redisClient.del("monthwise:" + code)
  }

  def insertMonthWiseConsolidatedRecord(monthWiseConsolidatedEntity: MonthWiseConsolidatedEntity): Unit = {
    redisClient.hmset("monthwise:"+monthWiseConsolidatedEntity.code,
      Map("januarycount" -> monthWiseConsolidatedEntity.jan, "februarycount" -> monthWiseConsolidatedEntity.feb,
        "marchcount" -> monthWiseConsolidatedEntity.mar, "aprilcount" -> monthWiseConsolidatedEntity.apr,
        "maycount" -> monthWiseConsolidatedEntity.may, "junecount" -> monthWiseConsolidatedEntity.jun,
      "julycount" -> monthWiseConsolidatedEntity.jul, "augustcount" -> monthWiseConsolidatedEntity.aug,
      "septembercount" -> monthWiseConsolidatedEntity.sep, "octobercount" -> monthWiseConsolidatedEntity.oct,
      "novembercount" -> monthWiseConsolidatedEntity.nov, "decembercount" -> monthWiseConsolidatedEntity.dec))
  }

}