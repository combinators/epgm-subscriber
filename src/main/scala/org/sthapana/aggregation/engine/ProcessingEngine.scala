package org.sthapana.aggregation.engine

import com.microsoft.azure.documentdb.Document
import org.sthapana.aggregation.dataobjects._
import org.sthapana.aggregation.utils.{AgeWiseConsolidationUtils, GenderWiseConsolidationUtils, GradeWiseConsolidationUtils, MonthWiseConsolidationUtils}
import org.sthapana._

class ProcessingEngine {

  def updateDB(updateEntity: UpdateEntity): Unit = {

    println("UpdateEntity:  ", updateEntity);

    val docDbConnector = new DocumentDbConnector(
      dbHost,
      dbPassword,
      dbName,
      collectionName)

    val (record, document) = updateIfMonthIsNotSame(docDbConnector.getConsolidatedRecord(updateEntity.stateCode), updateEntity.currentMonth, updateEntity.currentYear)
    val gradeEntity = record match {
      case None =>
        new GradeWiseConsolidationUtils().updateGradeWiseConsolidated(GradeWiseConsolidatedEntity("dashboard",
          updateEntity.stateCode, "0", "0", "0", "0"), updateEntity.currentGrade)
      case Some(ge) =>
        new GradeWiseConsolidationUtils().updateGradeWiseConsolidated(GradeWiseConsolidatedEntity("dashboard", updateEntity.stateCode,
          ge.get("suwcount").getOrElse("0"), ge.get("muwcount").getOrElse("0"), ge.get("normalcount").getOrElse("0"), ge.get("totalcount").getOrElse("0")),
          updateEntity.currentGrade)
    }
    val genderEntity = record match {
      case None => new GenderWiseConsolidationUtils().updateGenderWiseConsolidated(GenderWiseConsolidatedEntity("dashboard", updateEntity.stateCode,
        "0", "0"), updateEntity.gender, updateEntity.currentGrade)
      case Some(ge) => new GenderWiseConsolidationUtils().updateGenderWiseConsolidated(GenderWiseConsolidatedEntity("dashboard", updateEntity.stateCode,
        ge.get("malecount").getOrElse("0"), ge.get("femalecount").getOrElse("0")), updateEntity.gender, updateEntity.currentGrade)
    }
    val ageEntity = record match {
      case None =>
        new AgeWiseConsolidationUtils().updateAgeWiseConsolidated(AgeWiseConsolidatedEntity("dashboard", updateEntity.stateCode,
          "0", "0", "0", "0", "0", "0"), updateEntity.currentAge, updateEntity.currentGrade)
      case Some(ae) => new AgeWiseConsolidationUtils().updateAgeWiseConsolidated(AgeWiseConsolidatedEntity("dashboard", updateEntity.stateCode,
        ae.get("zerotoonecount").getOrElse("0"), ae.get("onetotwocount").getOrElse("0"), ae.get("twotothreecount").getOrElse("0"),
        ae.get("threetofourcount").getOrElse("0"), ae.get("fourtofivecount").getOrElse("0"), ae.get("fivetosixcount").getOrElse("0")),
        updateEntity.currentAge, updateEntity.currentGrade)
    }

    val monthEntity = record match {
      case None =>
        new MonthWiseConsolidationUtils().updateMonthWiseConsolidated(MonthWiseConsolidatedEntity("dashboard", updateEntity.stateCode,
          "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "02", "17"), updateEntity.currentMonth, updateEntity.currentYear,
          updateEntity.currentGrade)
      case Some(me) => new MonthWiseConsolidationUtils().updateMonthWiseConsolidated(MonthWiseConsolidatedEntity("dashboard", updateEntity.stateCode,
        me.get("januarycount").getOrElse("0"), me.get("februarycount").getOrElse("0"), me.get("marchcount").getOrElse("0"), me.get("aprilcount").getOrElse("0"),
        me.get("maycount").getOrElse("0"), me.get("junecount").getOrElse("0"), me.get("julycount").getOrElse("0"), me.get("augustcount").getOrElse("0"),
        me.get("septembercount").getOrElse("0"), me.get("octobercount").getOrElse("0"), me.get("novembercount").getOrElse("0"),
        me.get("decembercount").getOrElse("0"), me.get("currentmonth").getOrElse("0"), me.get("currentyear").getOrElse("0")), updateEntity.currentMonth,
        updateEntity.currentYear, updateEntity.currentGrade)
    }

    val tyrionEntity = TyrionEntity("dashboard", updateEntity.stateCode, gradeEntity.suw, gradeEntity.muw, gradeEntity.normal,
      gradeEntity.total, genderEntity.male, genderEntity.female, ageEntity.zeroToOne, ageEntity.oneToTwo, ageEntity.twoToThree, ageEntity.threeToFour,
      ageEntity.fourToFive, ageEntity.fiveToSix, monthEntity.jan, monthEntity.feb, monthEntity.mar, monthEntity.apr, monthEntity.may, monthEntity.jun,
      monthEntity.jul, monthEntity.aug, monthEntity.sep, monthEntity.oct, monthEntity.nov, monthEntity.dec, monthEntity.currmonth, monthEntity.curryear)
    record.get.get("insert") match {
      case None =>
        docDbConnector.updateConsolidatedRecord(document.get,tyrionEntity)
      case Some(_) => {
        docDbConnector.insertConsolidatedRecord(tyrionEntity)
      }
    }
  }

  def updateIfMonthIsNotSame(tuple: (Option[Map[String, String]], Option[Document]),
                             currentMonth: String, currentYear: String): (Option[Map[String, String]], Option[Document]) = {
    println("tuple update --- " + tuple)
    val output = tuple._1 match {
      case Some(te) if te.get("currentmonth").getOrElse("01").equals(currentMonth) => te
      case Some(te) if !te.get("currentmonth").getOrElse("01").equals(currentMonth) => {
        te.map(kv => kv._1 match {
          case k if k.equals("currentmonth") => "currentmonth" -> currentMonth
          case k if k.equals("currentyear") => "currentyear" -> currentYear
          case k if k.equals("suwcount") => "suwcount" -> "0"
          case k if k.equals("muwcount") => "muwcount" -> "0"
          case k if k.equals("normalcount") => "normalcount" -> "0"
          case k if k.equals("totalcount") => "totalcount" -> "0"
          case k if k.equals("malecount") => "malecount" -> "0"
          case k if k.equals("femalecount") => "femalecount" -> "0"
          case k if k.equals("zerotoonecount") => "zerotoonecount" -> "0"
          case k if k.equals("onetotwocount") => "onetotwocount" -> "0"
          case k if k.equals("twotothreecount") => "twotothreecount" -> "0"
          case k if k.equals("threetofourcount") => "threetofourcount" -> "0"
          case k if k.equals("fourtofivecount") => "fourtofivecount" -> "0"
          case k if k.equals("fivetosixcount") => "fivetosixcount" -> "0"
          case k => k -> te.get(k).get
        }) ++ Map("insert" -> "true")
      }
    }
    (Option(output), tuple._2)
  }

}
