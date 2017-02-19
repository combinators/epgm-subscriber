package org.sthapana.aggregation.engine

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

    val gradeEntity = new GradeWiseConsolidationUtils().updateGradeWiseConsolidated(docDbConnector,updateEntity.stateCode,
      updateEntity.currentGrade,updateEntity.previousGrade)

    val genderEntity = new GenderWiseConsolidationUtils().updateGenderWiseConsolidated(docDbConnector,updateEntity.stateCode,
      updateEntity.gender,updateEntity.currentGrade,updateEntity.previousGrade)

    val ageEntity = new AgeWiseConsolidationUtils().updateAgeWiseConsolidated(docDbConnector,updateEntity.stateCode,
      updateEntity.currentAge,updateEntity.previousAge,
      updateEntity.currentGrade,updateEntity.previousGrade)

    val monthEntity = new MonthWiseConsolidationUtils().updateMonthWiseConsolidated(docDbConnector,updateEntity.stateCode,
      updateEntity.currentMonth,updateEntity.currentYear,
      updateEntity.currentGrade,updateEntity.previousGrade)

    val tyrionEntity = TyrionEntity("dashboard",updateEntity.stateCode,gradeEntity.suw,gradeEntity.muw,gradeEntity.normal,
      gradeEntity.total,genderEntity.male,genderEntity.female,ageEntity.zeroToOne,ageEntity.oneToTwo,ageEntity.twoToThree,ageEntity.threeToFour,
      ageEntity.fourToFive,ageEntity.fiveToSix,monthEntity.jan,monthEntity.feb,monthEntity.mar,monthEntity.apr,monthEntity.may,monthEntity.jun,
      monthEntity.jul,monthEntity.aug,monthEntity.sep,monthEntity.oct,monthEntity.nov,monthEntity.dec,monthEntity.currmonth,monthEntity.curryear)

    docDbConnector.deleteConsolidatedRecord(updateEntity.stateCode)

    docDbConnector.insertConsolidatedRecord(tyrionEntity)

  }

}
