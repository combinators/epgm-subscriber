package org.sthapana.aggregation.utils

import org.sthapana.aggregation.dataobjects.AgeWiseConsolidatedEntity
import org.sthapana.aggregation.engine.{DocumentDbConnector, RedisConnector}

/**
  * Created by chocoklate on 14/2/17.
  */
class AgeWiseConsolidationUtils() {

  def updateAgeWiseConsolidated(ae: AgeWiseConsolidatedEntity,
                                currentAge: String, previousAge: String,
                                currentGrade: String, previousGrade: String):AgeWiseConsolidatedEntity = {

    val ageWiseConsolidatedEntity = ae
//    dc.deleteAgeWiseConsolidatedRecord(code)
    getUpdatedAgeWiseConsolidatedEntity(
      ageWiseConsolidatedEntity,currentAge,previousAge,currentGrade,previousGrade)

  }

  private def convertToGroup(age: String):String = age.toInt % 12 match {
    case -1 => "-1"
    case 0 => (age.toInt/12) + ""
    case _ => (age.toInt/12 + 1) + ""
  }

  private def getUpdatedAgeWiseConsolidatedEntity
  (awce: AgeWiseConsolidatedEntity,currentAge: String, previousAge: String,
   currentGrade:String, previousGrade:String): AgeWiseConsolidatedEntity = {
    val updatedAWCE = (convertToGroup(currentAge),convertToGroup(previousAge),currentGrade,previousGrade) match {

      case ("1","1","0","1") => AgeWiseConsolidatedEntity("dashboard", awce.code,CommonUtils.decrementByOne(awce.zeroToOne),awce.oneToTwo,awce.twoToThree,awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("1","1","0","2") => AgeWiseConsolidatedEntity("dashboard", awce.code,CommonUtils.decrementByOne(awce.zeroToOne),awce.oneToTwo,awce.twoToThree,awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("2","1","0","1") => AgeWiseConsolidatedEntity("dashboard", awce.code,CommonUtils.decrementByOne(awce.zeroToOne),awce.oneToTwo,awce.twoToThree,awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("2","1","0","2") => AgeWiseConsolidatedEntity("dashboard", awce.code,CommonUtils.decrementByOne(awce.zeroToOne),awce.oneToTwo,awce.twoToThree,awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("1","1","1","0") => AgeWiseConsolidatedEntity("dashboard", awce.code,CommonUtils.incrementByOne(awce.zeroToOne),awce.oneToTwo,awce.twoToThree,awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("2","1","1","0") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,CommonUtils.incrementByOne(awce.oneToTwo),awce.twoToThree,awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("2","1","1","1") => AgeWiseConsolidatedEntity("dashboard", awce.code,CommonUtils.decrementByOne(awce.zeroToOne),CommonUtils.incrementByOne(awce.oneToTwo),awce.twoToThree,awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("2","1","1","2") => AgeWiseConsolidatedEntity("dashboard", awce.code,CommonUtils.decrementByOne(awce.zeroToOne),CommonUtils.incrementByOne(awce.oneToTwo),awce.twoToThree,awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("1","1","2","0") => AgeWiseConsolidatedEntity("dashboard", awce.code,CommonUtils.incrementByOne(awce.zeroToOne),awce.oneToTwo,awce.twoToThree,awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("2","1","2","0") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,CommonUtils.incrementByOne(awce.oneToTwo),awce.twoToThree,awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("2","1","2","1") => AgeWiseConsolidatedEntity("dashboard", awce.code,CommonUtils.decrementByOne(awce.zeroToOne),CommonUtils.incrementByOne(awce.oneToTwo),awce.twoToThree,awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("2","1","2","2") => AgeWiseConsolidatedEntity("dashboard", awce.code,CommonUtils.decrementByOne(awce.zeroToOne),CommonUtils.incrementByOne(awce.oneToTwo),awce.twoToThree,awce.threeToFour,awce.fourToFive,awce.fiveToSix)

      case ("2","2","0","1") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,CommonUtils.decrementByOne(awce.oneToTwo),awce.twoToThree,awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("2","2","0","2") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,CommonUtils.decrementByOne(awce.oneToTwo),awce.twoToThree,awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("3","2","0","1") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,CommonUtils.decrementByOne(awce.oneToTwo),awce.twoToThree,awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("3","2","0","2") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,CommonUtils.decrementByOne(awce.oneToTwo),awce.twoToThree,awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("2","2","1","0") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,CommonUtils.incrementByOne(awce.oneToTwo),awce.twoToThree,awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("3","2","1","0") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,awce.oneToTwo,CommonUtils.incrementByOne(awce.twoToThree),awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("3","2","1","1") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,CommonUtils.decrementByOne(awce.oneToTwo),CommonUtils.incrementByOne(awce.twoToThree),awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("3","2","1","2") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,CommonUtils.decrementByOne(awce.oneToTwo),CommonUtils.incrementByOne(awce.twoToThree),awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("2","2","2","0") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,CommonUtils.incrementByOne(awce.oneToTwo),awce.twoToThree,awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("3","2","2","0") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,awce.oneToTwo,CommonUtils.incrementByOne(awce.twoToThree),awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("3","2","2","1") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,CommonUtils.decrementByOne(awce.oneToTwo),CommonUtils.incrementByOne(awce.twoToThree),awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("3","2","2","2") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,CommonUtils.decrementByOne(awce.oneToTwo),CommonUtils.incrementByOne(awce.twoToThree),awce.threeToFour,awce.fourToFive,awce.fiveToSix)

      case ("3","3","0","1") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,awce.oneToTwo,CommonUtils.decrementByOne(awce.twoToThree),awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("3","3","0","2") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,awce.oneToTwo,CommonUtils.decrementByOne(awce.twoToThree),awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("4","3","0","1") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,awce.oneToTwo,CommonUtils.decrementByOne(awce.twoToThree),awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("4","3","0","2") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,awce.oneToTwo,CommonUtils.decrementByOne(awce.twoToThree),awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("3","3","1","0") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,awce.oneToTwo,CommonUtils.incrementByOne(awce.twoToThree),awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("4","3","1","0") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,CommonUtils.incrementByOne(awce.threeToFour),awce.fourToFive,awce.fiveToSix)
      case ("4","3","1","1") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,awce.oneToTwo,CommonUtils.decrementByOne(awce.twoToThree),CommonUtils.incrementByOne(awce.threeToFour),awce.fourToFive,awce.fiveToSix)
      case ("4","3","1","2") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,awce.oneToTwo,CommonUtils.decrementByOne(awce.twoToThree),CommonUtils.incrementByOne(awce.threeToFour),awce.fourToFive,awce.fiveToSix)
      case ("3","3","2","0") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,awce.oneToTwo,CommonUtils.incrementByOne(awce.twoToThree),awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("4","3","2","0") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,CommonUtils.incrementByOne(awce.threeToFour),awce.fourToFive,awce.fiveToSix)
      case ("4","3","2","1") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,awce.oneToTwo,CommonUtils.decrementByOne(awce.twoToThree),CommonUtils.incrementByOne(awce.threeToFour),awce.fourToFive,awce.fiveToSix)
      case ("4","3","2","2") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,awce.oneToTwo,CommonUtils.decrementByOne(awce.twoToThree),CommonUtils.incrementByOne(awce.threeToFour),awce.fourToFive,awce.fiveToSix)

      case ("4","4","0","1") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,CommonUtils.decrementByOne(awce.threeToFour),awce.fourToFive,awce.fiveToSix)
      case ("4","4","0","2") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,CommonUtils.decrementByOne(awce.threeToFour),awce.fourToFive,awce.fiveToSix)
      case ("5","4","0","1") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,CommonUtils.decrementByOne(awce.threeToFour),awce.fourToFive,awce.fiveToSix)
      case ("5","4","0","2") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,CommonUtils.decrementByOne(awce.threeToFour),awce.fourToFive,awce.fiveToSix)
      case ("4","4","1","0") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,CommonUtils.incrementByOne(awce.threeToFour),awce.fourToFive,awce.fiveToSix)
      case ("5","4","1","0") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,awce.threeToFour,CommonUtils.incrementByOne(awce.fourToFive),awce.fiveToSix)
      case ("5","4","1","1") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,CommonUtils.decrementByOne(awce.threeToFour),CommonUtils.incrementByOne(awce.fourToFive),awce.fiveToSix)
      case ("5","4","1","2") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,CommonUtils.decrementByOne(awce.threeToFour),CommonUtils.incrementByOne(awce.fourToFive),awce.fiveToSix)
      case ("4","4","2","0") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,CommonUtils.incrementByOne(awce.threeToFour),awce.fourToFive,awce.fiveToSix)
      case ("5","4","2","0") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,awce.threeToFour,CommonUtils.incrementByOne(awce.fourToFive),awce.fiveToSix)
      case ("5","4","2","1") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,CommonUtils.decrementByOne(awce.threeToFour),CommonUtils.incrementByOne(awce.fourToFive),awce.fiveToSix)
      case ("5","4","2","2") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,CommonUtils.decrementByOne(awce.threeToFour),CommonUtils.incrementByOne(awce.fourToFive),awce.fiveToSix)

      case ("5","5","0","1") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,awce.threeToFour,CommonUtils.decrementByOne(awce.fourToFive),awce.fiveToSix)
      case ("5","5","0","2") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,awce.threeToFour,CommonUtils.decrementByOne(awce.fourToFive),awce.fiveToSix)
      case ("6","5","0","1") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,awce.threeToFour,CommonUtils.decrementByOne(awce.fourToFive),awce.fiveToSix)
      case ("6","5","0","2") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,awce.threeToFour,CommonUtils.decrementByOne(awce.fourToFive),awce.fiveToSix)
      case ("5","5","1","0") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,awce.threeToFour,CommonUtils.incrementByOne(awce.fourToFive),awce.fiveToSix)
      case ("6","5","1","0") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,awce.threeToFour,awce.fourToFive,CommonUtils.incrementByOne(awce.fiveToSix))
      case ("6","5","1","1") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,awce.threeToFour,CommonUtils.decrementByOne(awce.fourToFive),CommonUtils.incrementByOne(awce.fiveToSix))
      case ("6","5","1","2") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,awce.threeToFour,CommonUtils.decrementByOne(awce.fourToFive),CommonUtils.incrementByOne(awce.fiveToSix))
      case ("5","5","2","0") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,awce.threeToFour,CommonUtils.incrementByOne(awce.fourToFive),awce.fiveToSix)
      case ("6","5","2","0") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,awce.threeToFour,awce.fourToFive,CommonUtils.incrementByOne(awce.fiveToSix))
      case ("6","5","2","1") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,awce.threeToFour,CommonUtils.decrementByOne(awce.fourToFive),CommonUtils.incrementByOne(awce.fiveToSix))
      case ("6","5","2","2") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,awce.threeToFour,CommonUtils.decrementByOne(awce.fourToFive),CommonUtils.incrementByOne(awce.fiveToSix))

      case (_,_,_,x) if(!x.equals("-1")) => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,awce.threeToFour,awce.fourToFive,awce.fiveToSix)

      case ("1","-1","1","-1") => AgeWiseConsolidatedEntity("dashboard", awce.code,CommonUtils.incrementByOne(awce.zeroToOne),awce.oneToTwo,awce.twoToThree,awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("2","-1","1","-1") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,CommonUtils.incrementByOne(awce.oneToTwo),awce.twoToThree,awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("3","-1","1","-1") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,awce.oneToTwo,CommonUtils.incrementByOne(awce.twoToThree),awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("4","-1","1","-1") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,CommonUtils.incrementByOne(awce.threeToFour),awce.fourToFive,awce.fiveToSix)
      case ("5","-1","1","-1") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,awce.threeToFour,CommonUtils.incrementByOne(awce.fourToFive),awce.fiveToSix)
      case ("6","-1","1","-1") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,awce.threeToFour,awce.fourToFive,CommonUtils.incrementByOne(awce.fiveToSix))
      case ("1","-1","2","-1") => AgeWiseConsolidatedEntity("dashboard", awce.code,CommonUtils.incrementByOne(awce.zeroToOne),awce.oneToTwo,awce.twoToThree,awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("2","-1","2","-1") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,CommonUtils.incrementByOne(awce.oneToTwo),awce.twoToThree,awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("3","-1","2","-1") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,awce.oneToTwo,CommonUtils.incrementByOne(awce.twoToThree),awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("4","-1","2","-1") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,CommonUtils.incrementByOne(awce.threeToFour),awce.fourToFive,awce.fiveToSix)
      case ("5","-1","2","-1") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,awce.threeToFour,CommonUtils.incrementByOne(awce.fourToFive),awce.fiveToSix)
      case ("6","-1","2","-1") => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,awce.threeToFour,awce.fourToFive,CommonUtils.incrementByOne(awce.fiveToSix))

      case (_,_,_,_) => AgeWiseConsolidatedEntity("dashboard", awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,awce.threeToFour,awce.fourToFive,awce.fiveToSix)

    }

    updatedAWCE
  }

}
