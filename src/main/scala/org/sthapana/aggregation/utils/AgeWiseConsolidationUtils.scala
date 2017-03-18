package org.sthapana.aggregation.utils

import org.sthapana.aggregation.dataobjects.AgeWiseConsolidatedEntity

class AgeWiseConsolidationUtils() {

  def updateAgeWiseConsolidated(ae: AgeWiseConsolidatedEntity,
                                currentAge: String,
                                currentGrade: String): AgeWiseConsolidatedEntity = {
    getUpdatedAgeWiseConsolidatedEntity(
      ae, currentAge, currentGrade)
  }

  private def getUpdatedAgeWiseConsolidatedEntity
  (awce: AgeWiseConsolidatedEntity, currentAge: String,
   currentGrade: String): AgeWiseConsolidatedEntity = {
    val updatedAWCE = (convertToGroup(currentAge), currentGrade) match {
      case ("1", "1") => AgeWiseConsolidatedEntity("dashboard", awce.code, CommonUtils.incrementByOne(awce.zeroToOne), awce.oneToTwo, awce.twoToThree, awce.threeToFour, awce.fourToFive, awce.fiveToSix)
      case ("1", "2") => AgeWiseConsolidatedEntity("dashboard", awce.code, CommonUtils.incrementByOne(awce.zeroToOne), awce.oneToTwo, awce.twoToThree, awce.threeToFour, awce.fourToFive, awce.fiveToSix)
      case ("2", "1") => AgeWiseConsolidatedEntity("dashboard", awce.code, awce.zeroToOne, CommonUtils.incrementByOne(awce.oneToTwo), awce.twoToThree, awce.threeToFour, awce.fourToFive, awce.fiveToSix)
      case ("2", "2") => AgeWiseConsolidatedEntity("dashboard", awce.code, awce.zeroToOne, CommonUtils.incrementByOne(awce.oneToTwo), awce.twoToThree, awce.threeToFour, awce.fourToFive, awce.fiveToSix)
      case ("3", "1") => AgeWiseConsolidatedEntity("dashboard", awce.code, awce.zeroToOne, awce.oneToTwo, CommonUtils.incrementByOne(awce.twoToThree), awce.threeToFour, awce.fourToFive, awce.fiveToSix)
      case ("3", "2") => AgeWiseConsolidatedEntity("dashboard", awce.code, awce.zeroToOne, awce.oneToTwo, CommonUtils.incrementByOne(awce.twoToThree), awce.threeToFour, awce.fourToFive, awce.fiveToSix)
      case ("4", "1") => AgeWiseConsolidatedEntity("dashboard", awce.code, awce.zeroToOne, awce.oneToTwo, awce.twoToThree, CommonUtils.incrementByOne(awce.threeToFour), awce.fourToFive, awce.fiveToSix)
      case ("4", "2") => AgeWiseConsolidatedEntity("dashboard", awce.code, awce.zeroToOne, awce.oneToTwo, awce.twoToThree, CommonUtils.incrementByOne(awce.threeToFour), awce.fourToFive, awce.fiveToSix)
      case (_, _) => AgeWiseConsolidatedEntity("dashboard", awce.code, awce.zeroToOne, awce.oneToTwo, awce.twoToThree, awce.threeToFour, awce.fourToFive, awce.fiveToSix)
    }
    updatedAWCE
  }

  private def convertToGroup(age: String): String = age.toInt match {
    case -1 => "-1"
    case x if (x >= 0 && x <= 6) => "1"
    case x if (x >= 7 && x <= 24) => "2"
    case x if (x >= 25 && x <= 42) => "3"
    case x if (x >= 43 && x <= 72) => "4"
    case x if (x >= 73) => "20"
  }

}
