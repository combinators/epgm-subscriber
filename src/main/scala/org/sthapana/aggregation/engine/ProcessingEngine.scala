package org.sthapana.aggregation.engine

import java.util.Calendar

import org.sthapana.aggregation.dataobjects._
import org.sthapana.aggregation.utils.{AgeWiseConsolidationUtils, GenderWiseConsolidationUtils, GradeWiseConsolidationUtils, MonthWiseConsolidationUtils}

/**
  * Created by chocoklate on 14/2/17.
  */
class ProcessingEngine {

  def updateDB(updateEntity: UpdateEntity): Unit = {

    val docDbConnector = new DocumentDbConnector(
      "https://epgm.documents.azure.com:443/",
      "SlhyMCNEuU55HklqqibVpNAqi58tN5ZcBjYznR2SLUxNOsjNaEH7JT3kLsaB6K9mRFMtTrl10bx3oJYm9DfsAA==",
      "thewall","tyrion")

    val (record, document) = docDbConnector.getConsolidatedRecord(updateEntity.stateCode)

    val gradeEntity = record match {
      case None =>
        new GradeWiseConsolidationUtils().updateGradeWiseConsolidated(GradeWiseConsolidatedEntity("dashboard",
          updateEntity.stateCode, "0", "0", "0", "0"), updateEntity.currentGrade, updateEntity.previousGrade)
      case Some(ge) =>
        new GradeWiseConsolidationUtils().updateGradeWiseConsolidated(GradeWiseConsolidatedEntity("dashboard", updateEntity.stateCode,
          ge.get("suwcount").getOrElse("0"),ge.get("muwcount").getOrElse("0"),ge.get("normalcount").getOrElse("0"),ge.get("totalcount").getOrElse("0")),
          updateEntity.currentGrade, updateEntity.previousGrade)
    }

    val genderEntity = record match {
      case None => new GenderWiseConsolidationUtils().updateGenderWiseConsolidated(GenderWiseConsolidatedEntity("dashboard", updateEntity.stateCode,
        "0", "0"),updateEntity.gender, updateEntity.currentGrade, updateEntity.previousGrade)
      case Some(ge) => new GenderWiseConsolidationUtils().updateGenderWiseConsolidated(GenderWiseConsolidatedEntity("dashboard", updateEntity.stateCode,
        ge.get("malecount").getOrElse("0"),ge.get("femalecount").getOrElse("0")),updateEntity.gender, updateEntity.currentGrade, updateEntity.previousGrade)
    }

    val ageEntity = record match {
      case None =>
        new AgeWiseConsolidationUtils().updateAgeWiseConsolidated(AgeWiseConsolidatedEntity("dashboard", updateEntity.stateCode,
          "0", "0", "0", "0", "0", "0"),updateEntity.currentAge,updateEntity.previousAge, updateEntity.currentGrade, updateEntity.previousGrade)
      case Some(ae) => new AgeWiseConsolidationUtils().updateAgeWiseConsolidated(AgeWiseConsolidatedEntity("dashboard", updateEntity.stateCode,
        ae.get("zerotoonecount").getOrElse("0"), ae.get("onetotwocount").getOrElse("0"), ae.get("twotothreecount").getOrElse("0"),
        ae.get("threetofourcount").getOrElse("0"), ae.get("fourtofivecount").getOrElse("0"), ae.get("fivetosixcount").getOrElse("0")),
        updateEntity.currentAge,updateEntity.previousAge, updateEntity.currentGrade, updateEntity.previousGrade)
    }

    val currMon  = Calendar.getInstance().get(Calendar.MONTH)
    val currYear = Calendar.getInstance().get(Calendar.YEAR)

    val monthEntity = record match {
      case None =>
        new MonthWiseConsolidationUtils().updateMonthWiseConsolidated(MonthWiseConsolidatedEntity("dashboard", updateEntity.stateCode,
          "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0","02","17"), updateEntity.currentMonth, updateEntity.currentYear,
          updateEntity.currentGrade, updateEntity.previousGrade)
      case Some(me) =>  new MonthWiseConsolidationUtils().updateMonthWiseConsolidated(MonthWiseConsolidatedEntity("dashboard", updateEntity.stateCode,
        me.get("januarycount").getOrElse("0"), me.get("februarycount").getOrElse("0"), me.get("marchcount").getOrElse("0"), me.get("aprilcount").getOrElse("0"),
        me.get("maycount").getOrElse("0"), me.get("junecount").getOrElse("0"), me.get("julycount").getOrElse("0"), me.get("augustcount").getOrElse("0"),
        me.get("septembercount").getOrElse("0"), me.get("octobercount").getOrElse("0"), me.get("novembercount").getOrElse("0"),
        me.get("decembercount").getOrElse("0"), me.get("currentmonth").getOrElse("0"), me.get("currentyear").getOrElse("0")), updateEntity.currentMonth,
        updateEntity.currentYear, updateEntity.currentGrade, updateEntity.previousGrade)
    }

    val tyrionEntity = TyrionEntity("dashboard",updateEntity.stateCode,gradeEntity.suw,gradeEntity.muw,gradeEntity.normal,
      gradeEntity.total,genderEntity.male,genderEntity.female,ageEntity.zeroToOne,ageEntity.oneToTwo,ageEntity.twoToThree,ageEntity.threeToFour,
      ageEntity.fourToFive,ageEntity.fiveToSix,monthEntity.jan,monthEntity.feb,monthEntity.mar,monthEntity.apr,monthEntity.may,monthEntity.jun,
      monthEntity.jul,monthEntity.aug,monthEntity.sep,monthEntity.oct,monthEntity.nov,monthEntity.dec,monthEntity.currmonth,monthEntity.curryear)

    record match {
      case None => docDbConnector.insertConsolidatedRecord(tyrionEntity)
      case Some(_) => {
        docDbConnector.deleteConsolidatedRecord(updateEntity.stateCode, document.get)
        docDbConnector.insertConsolidatedRecord(tyrionEntity)
      }
    }


    println(record)
    println(tyrionEntity)
  }

}
