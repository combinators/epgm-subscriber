package org.sthapana.aggregation.engine

import org.sthapana.aggregation.dataobjects._

/**
  * Created by chocoklate on 14/2/17.
  */
class ProcessingEngine {

  def incrementByOne(count: String):String = {
    (count.toLong + 1).toString
  }

  def decrementByOne(count: String):String = {
    (count.toLong - 1).toString
  }

  def getUpdatedGradeWiseConsolidatedEntity
  (gwce: GradeWiseConsolidatedEntity,
   currentGrade: String, previousGrade: String): GradeWiseConsolidatedEntity = {
    val tot = gwce.total
    val suw = gwce.suw
    val muw = gwce.muw
    val nor = gwce.normal

    val updatedGWCE = (currentGrade,previousGrade) match {
      case ("0","-1") => GradeWiseConsolidatedEntity(gwce.code,suw,muw,incrementByOne(nor),incrementByOne(tot))
      case ("0","1") => GradeWiseConsolidatedEntity(gwce.code,suw,decrementByOne(muw),incrementByOne(nor),tot)
      case ("0","2") => GradeWiseConsolidatedEntity(gwce.code,decrementByOne(suw),muw,incrementByOne(nor),tot)
      case ("1","-1") => GradeWiseConsolidatedEntity(gwce.code,suw,incrementByOne(muw),nor,incrementByOne(tot))
      case ("1","0") => GradeWiseConsolidatedEntity(gwce.code,suw,incrementByOne(muw),decrementByOne(nor),tot)
      case ("1","2") => GradeWiseConsolidatedEntity(gwce.code,decrementByOne(suw),incrementByOne(muw),nor,tot)
      case ("2","-1") => GradeWiseConsolidatedEntity(gwce.code,incrementByOne(suw),muw,nor,incrementByOne(tot))
      case ("2","0") => GradeWiseConsolidatedEntity(gwce.code,incrementByOne(suw),muw,decrementByOne(nor),tot)
      case ("2","1") => GradeWiseConsolidatedEntity(gwce.code,incrementByOne(suw),decrementByOne(muw),nor,tot)
      case (_,_) => GradeWiseConsolidatedEntity(gwce.code,suw,muw,nor,tot)
    }

    updatedGWCE
  }

  def getUpdatedGenderWiseConsolidatedEntity
  (gwce: GenderWiseConsolidatedEntity,gender: String,
   currentGrade:String, previousGrade:String): GenderWiseConsolidatedEntity = {
    val male = gwce.male
    val female = gwce.female

    val updatedGWCE = (currentGrade,previousGrade,gender) match {
      case ("0","1","M") => GenderWiseConsolidatedEntity(gwce.code,decrementByOne(male),female)
      case ("0","2","M") => GenderWiseConsolidatedEntity(gwce.code,decrementByOne(male),female)
      case ("1","-1","M") => GenderWiseConsolidatedEntity(gwce.code,incrementByOne(male),female)
      case ("1","0","M") => GenderWiseConsolidatedEntity(gwce.code,incrementByOne(male),female)
      case ("2","-1","M") => GenderWiseConsolidatedEntity(gwce.code,incrementByOne(male),female)
      case ("2","0","M") => GenderWiseConsolidatedEntity(gwce.code,incrementByOne(male),female)
      case ("0","1","F") => GenderWiseConsolidatedEntity(gwce.code,male,decrementByOne(female))
      case ("0","2","F") => GenderWiseConsolidatedEntity(gwce.code,male,decrementByOne(female))
      case ("1","-1","F") => GenderWiseConsolidatedEntity(gwce.code,male,incrementByOne(female))
      case ("1","0","F") => GenderWiseConsolidatedEntity(gwce.code,male,incrementByOne(female))
      case ("2","-1","F") => GenderWiseConsolidatedEntity(gwce.code,male,incrementByOne(female))
      case ("2","0","F") => GenderWiseConsolidatedEntity(gwce.code,male,incrementByOne(female))
      case (_,_,_) => GenderWiseConsolidatedEntity(gwce.code,male,female)
    }

    updatedGWCE
  }

  def convertToGroup(age: String):String = age match {
    case x if(x.equals("-1")) => "-1"
    case x if(x.toInt>=0 && x.toInt<=12) => "1"
    case x if(x.toInt>=13 && x.toInt<=24) => "2"
    case x if(x.toInt>=25 && x.toInt<=36) => "3"
    case x if(x.toInt>=37 && x.toInt<=48) => "4"
    case x if(x.toInt>=49 && x.toInt<=60) => "5"
    case x if(x.toInt>=61 && x.toInt<=72) => "6"
  }

  def getUpdatedAgeWiseConsolidatedEntity
  (awce: AgeWiseConsolidatedEntity,currentAge: String, previousAge: String,
   currentGrade:String, previousGrade:String): AgeWiseConsolidatedEntity = {
    val zerotoone = awce.zeroToOne
    val onetotwo = awce.oneToTwo
    val twotothree = awce.twoToThree
    val threetofour = awce.threeToFour
    val fourtofive = awce.fourToFive
    val fivetosix = awce.fiveToSix

    val updatedAWCE = (convertToGroup(currentAge),convertToGroup(previousAge),currentGrade,previousGrade) match {
      case ("1","-1","1","-1") => AgeWiseConsolidatedEntity(awce.code,incrementByOne(zerotoone),onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("2","-1","1","-1") => AgeWiseConsolidatedEntity(awce.code,zerotoone,incrementByOne(onetotwo),twotothree,threetofour,fourtofive,fivetosix)
      case ("3","-1","1","-1") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,incrementByOne(twotothree),threetofour,fourtofive,fivetosix)
      case ("4","-1","1","-1") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,incrementByOne(threetofour),fourtofive,fivetosix)
      case ("5","-1","1","-1") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,incrementByOne(fourtofive),fivetosix)
      case ("6","-1","1","-1") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,incrementByOne(fivetosix))
      case ("1","-1","2","-1") => AgeWiseConsolidatedEntity(awce.code,incrementByOne(zerotoone),onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("2","-1","2","-1") => AgeWiseConsolidatedEntity(awce.code,zerotoone,incrementByOne(onetotwo),twotothree,threetofour,fourtofive,fivetosix)
      case ("3","-1","2","-1") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,incrementByOne(twotothree),threetofour,fourtofive,fivetosix)
      case ("4","-1","2","-1") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,incrementByOne(threetofour),fourtofive,fivetosix)
      case ("5","-1","2","-1") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,incrementByOne(fourtofive),fivetosix)
      case ("6","-1","2","-1") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,incrementByOne(fivetosix))

      case ("1","1","0","1") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("1","1","0","2") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("2","1","0","1") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("2","1","0","2") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("1","1","1","0") => AgeWiseConsolidatedEntity(awce.code,incrementByOne(zerotoone),onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("2","1","1","0") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("2","1","1","1") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("2","1","1","2") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("1","1","2","0") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("1","1","2","1") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("1","1","2","2") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("2","1","2","0") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("2","1","2","1") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("2","1","2","2") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)

      case ("2","2","0","0") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("2","2","0","1") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("2","2","0","2") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("3","2","0","0") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("3","2","0","1") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("3","2","0","2") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("2","2","1","0") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("2","2","1","1") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("2","2","1","2") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("3","2","1","0") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("3","2","1","1") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("3","2","1","2") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("2","2","2","0") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("2","2","2","1") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("2","2","2","2") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("3","2","2","0") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("3","2","2","1") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("3","2","2","2") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)

      case ("3","3","0","0") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("3","3","0","1") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("3","3","0","2") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("4","3","0","0") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("4","3","0","1") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("4","3","0","2") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("3","3","1","0") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("3","3","1","1") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("3","3","1","2") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("4","3","1","0") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("4","3","1","1") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("4","3","1","2") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("3","3","2","0") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("3","3","2","1") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("3","3","2","2") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("4","3","2","0") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("4","3","2","1") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("4","3","2","2") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)

      case ("4","4","0","0") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("4","4","0","1") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("4","4","0","2") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("5","4","0","0") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("5","4","0","1") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("5","4","0","2") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("4","4","1","0") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("4","4","1","1") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("4","4","1","2") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("5","4","1","0") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("5","4","1","1") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("5","4","1","2") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("4","4","2","0") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("4","4","2","1") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("4","4","2","2") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("5","4","2","0") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("5","4","2","1") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("5","4","2","2") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)

      case ("5","5","0","0") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("5","5","0","1") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("5","5","0","2") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("6","5","0","0") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("6","5","0","1") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("6","5","0","2") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("5","5","1","0") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("5","5","1","1") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("5","5","1","2") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("6","5","1","0") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("6","5","1","1") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("6","5","1","2") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("5","5","2","0") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("5","5","2","1") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("5","5","2","2") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("6","5","2","0") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("6","5","2","1") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
      case ("6","5","2","2") => AgeWiseConsolidatedEntity(awce.code,zerotoone,onetotwo,twotothree,threetofour,fourtofive,fivetosix)
    }

    updatedAWCE
  }

  def getUpdatedMonthWiseConsolidatedEntity
  (mwce: MonthWiseConsolidatedEntity,
   currentMonth: String, previousMonth: String): MonthWiseConsolidatedEntity = {

    val updatedMWCE = (currentMonth,previousMonth) match {
    }

    updatedMWCE
  }

  def updateGradeWiseConsolidated(rc:RedisConnector, code: String,
                                  currentGrade: String, previousGrade: String) = {

    val gradeWiseConsolidatedEntity = rc.getGradeWiseConsolidatedRecord(code)
    rc.deleteGradeWiseConsolidatedRecord(code)
    rc.insertGradeWiseConsolidatedRecord(getUpdatedGradeWiseConsolidatedEntity(
      gradeWiseConsolidatedEntity,currentGrade,previousGrade))

  }

  def updateGenderWiseConsolidated(rc:RedisConnector, code: String,gender: String,
                                   currentGrade: String, previousGrade: String) = {

    val genderWiseConsolidatedEntity = rc.getGenderWiseConsolidatedRecord(code)
    rc.deleteGenderWiseConsolidatedRecord(code)
    rc.insertGenderWiseConsolidatedRecord(getUpdatedGenderWiseConsolidatedEntity(
      genderWiseConsolidatedEntity,gender,currentGrade,previousGrade))

  }

  def updateAgeWiseConsolidated(rc:RedisConnector, code: String,
                                currentAge: String, previousAge: String,
                                currentGrade: String, previousGrade: String) = {

    val ageWiseConsolidatedEntity = rc.getAgeWiseConsolidatedRecord(code)
    rc.deleteAgeWiseConsolidatedRecord(code)
    rc.insertAgeWiseConsolidatedRecord(getUpdatedAgeWiseConsolidatedEntity(
      ageWiseConsolidatedEntity,currentAge,previousAge,currentGrade,previousGrade))

  }

  def updateMonthWiseConsolidated(rc:RedisConnector, code: String,
                                   currentMonth: String, previousMonth: String,
                                  currentGrade: String, previousGrade: String) = {

    val monthWiseConsolidatedEntity = rc.getMonthWiseConsolidatedRecord(code)
    rc.deleteMonthWiseConsolidatedRecord(code)
    rc.insertMonthWiseConsolidatedRecord(getUpdatedMonthWiseConsolidatedEntity(
      monthWiseConsolidatedEntity,currentMonth,previousMonth))

  }

  def updateDB(updateEntity: UpdateEntity): Unit ={
    val redisClient = RedisConnector("localhost",6379)

    updateGradeWiseConsolidated(redisClient,updateEntity.stateCode,
      updateEntity.currentGrade,updateEntity.previousGrade)

    updateGenderWiseConsolidated(redisClient,updateEntity.stateCode,
      updateEntity.gender,updateEntity.currentGrade,updateEntity.previousGrade)

    updateAgeWiseConsolidated(redisClient,updateEntity.stateCode,
      updateEntity.currentAge,updateEntity.previousAge,
      updateEntity.currentGrade,updateEntity.previousGrade)

    updateMonthWiseConsolidated(redisClient,updateEntity.stateCode,
      updateEntity.currentMonth,updateEntity.previousMonth,
      updateEntity.currentGrade,updateEntity.previousGrade)
  }

}
