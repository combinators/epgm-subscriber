package org.sthapana.aggregation.utils

import org.sthapana.aggregation.dataobjects.AgeWiseConsolidatedEntity
import org.sthapana.aggregation.engine.RedisConnector

/**
  * Created by chocoklate on 14/2/17.
  */
class AgeWiseConsolidationUtils {

  def updateAgeWiseConsolidated(rc:RedisConnector, code: String,
                                currentAge: String, previousAge: String,
                                currentGrade: String, previousGrade: String) = {

    val ageWiseConsolidatedEntity = rc.getAgeWiseConsolidatedRecord(code)
    rc.deleteAgeWiseConsolidatedRecord(code)
    rc.insertAgeWiseConsolidatedRecord(getUpdatedAgeWiseConsolidatedEntity(
      ageWiseConsolidatedEntity,currentAge,previousAge,currentGrade,previousGrade))

  }

  private def convertToGroup(age: String):String = age match {
    case x if(x.equals("-1")) => "-1"
    case x if(x.toInt>=0 && x.toInt<=12) => "1"
    case x if(x.toInt>=13 && x.toInt<=24) => "2"
    case x if(x.toInt>=25 && x.toInt<=36) => "3"
    case x if(x.toInt>=37 && x.toInt<=48) => "4"
    case x if(x.toInt>=49 && x.toInt<=60) => "5"
    case x if(x.toInt>=61 && x.toInt<=72) => "6"
  }

  private def getUpdatedAgeWiseConsolidatedEntity
  (awce: AgeWiseConsolidatedEntity,currentAge: String, previousAge: String,
   currentGrade:String, previousGrade:String): AgeWiseConsolidatedEntity = {
    val updatedAWCE = (convertToGroup(currentAge),convertToGroup(previousAge),currentGrade,previousGrade) match {

      case ("1","1","0","1") => AgeWiseConsolidatedEntity(awce.code,CommonUtils.decrementByOne(awce.zeroToOne),awce.oneToTwo,awce.twoToThree,awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("1","1","0","2") => AgeWiseConsolidatedEntity(awce.code,CommonUtils.decrementByOne(awce.zeroToOne),awce.oneToTwo,awce.twoToThree,awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("2","1","0","1") => AgeWiseConsolidatedEntity(awce.code,CommonUtils.decrementByOne(awce.zeroToOne),awce.oneToTwo,awce.twoToThree,awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("2","1","0","2") => AgeWiseConsolidatedEntity(awce.code,CommonUtils.decrementByOne(awce.zeroToOne),awce.oneToTwo,awce.twoToThree,awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("1","1","1","0") => AgeWiseConsolidatedEntity(awce.code,CommonUtils.incrementByOne(awce.zeroToOne),awce.oneToTwo,awce.twoToThree,awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("2","1","1","0") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,CommonUtils.incrementByOne(awce.oneToTwo),awce.twoToThree,awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("2","1","1","1") => AgeWiseConsolidatedEntity(awce.code,CommonUtils.decrementByOne(awce.zeroToOne),CommonUtils.incrementByOne(awce.oneToTwo),awce.twoToThree,awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("2","1","1","2") => AgeWiseConsolidatedEntity(awce.code,CommonUtils.decrementByOne(awce.zeroToOne),CommonUtils.incrementByOne(awce.oneToTwo),awce.twoToThree,awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("1","1","2","0") => AgeWiseConsolidatedEntity(awce.code,CommonUtils.incrementByOne(awce.zeroToOne),awce.oneToTwo,awce.twoToThree,awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("2","1","2","0") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,CommonUtils.incrementByOne(awce.oneToTwo),awce.twoToThree,awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("2","1","2","1") => AgeWiseConsolidatedEntity(awce.code,CommonUtils.decrementByOne(awce.zeroToOne),CommonUtils.incrementByOne(awce.oneToTwo),awce.twoToThree,awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("2","1","2","2") => AgeWiseConsolidatedEntity(awce.code,CommonUtils.decrementByOne(awce.zeroToOne),CommonUtils.incrementByOne(awce.oneToTwo),awce.twoToThree,awce.threeToFour,awce.fourToFive,awce.fiveToSix)

      case ("2","2","0","1") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,CommonUtils.decrementByOne(awce.oneToTwo),awce.twoToThree,awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("2","2","0","2") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,CommonUtils.decrementByOne(awce.oneToTwo),awce.twoToThree,awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("3","2","0","1") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,CommonUtils.decrementByOne(awce.oneToTwo),awce.twoToThree,awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("3","2","0","2") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,CommonUtils.decrementByOne(awce.oneToTwo),awce.twoToThree,awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("2","2","1","0") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,CommonUtils.incrementByOne(awce.oneToTwo),awce.twoToThree,awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("3","2","1","0") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,awce.oneToTwo,CommonUtils.incrementByOne(awce.twoToThree),awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("3","2","1","1") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,CommonUtils.decrementByOne(awce.oneToTwo),CommonUtils.incrementByOne(awce.twoToThree),awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("3","2","1","2") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,CommonUtils.decrementByOne(awce.oneToTwo),CommonUtils.incrementByOne(awce.twoToThree),awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("2","2","2","0") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,CommonUtils.incrementByOne(awce.oneToTwo),awce.twoToThree,awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("3","2","2","0") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,awce.oneToTwo,CommonUtils.incrementByOne(awce.twoToThree),awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("3","2","2","1") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,CommonUtils.decrementByOne(awce.oneToTwo),CommonUtils.incrementByOne(awce.twoToThree),awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("3","2","2","2") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,CommonUtils.decrementByOne(awce.oneToTwo),CommonUtils.incrementByOne(awce.twoToThree),awce.threeToFour,awce.fourToFive,awce.fiveToSix)

      case ("3","3","0","1") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,awce.oneToTwo,CommonUtils.decrementByOne(awce.twoToThree),awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("3","3","0","2") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,awce.oneToTwo,CommonUtils.decrementByOne(awce.twoToThree),awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("4","3","0","1") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,awce.oneToTwo,CommonUtils.decrementByOne(awce.twoToThree),awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("4","3","0","2") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,awce.oneToTwo,CommonUtils.decrementByOne(awce.twoToThree),awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("3","3","1","0") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,awce.oneToTwo,CommonUtils.incrementByOne(awce.twoToThree),awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("4","3","1","0") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,CommonUtils.incrementByOne(awce.threeToFour),awce.fourToFive,awce.fiveToSix)
      case ("4","3","1","1") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,awce.oneToTwo,CommonUtils.decrementByOne(awce.twoToThree),CommonUtils.incrementByOne(awce.threeToFour),awce.fourToFive,awce.fiveToSix)
      case ("4","3","1","2") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,awce.oneToTwo,CommonUtils.decrementByOne(awce.twoToThree),CommonUtils.incrementByOne(awce.threeToFour),awce.fourToFive,awce.fiveToSix)
      case ("3","3","2","0") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,awce.oneToTwo,CommonUtils.incrementByOne(awce.twoToThree),awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("4","3","2","0") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,CommonUtils.incrementByOne(awce.threeToFour),awce.fourToFive,awce.fiveToSix)
      case ("4","3","2","1") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,awce.oneToTwo,CommonUtils.decrementByOne(awce.twoToThree),CommonUtils.incrementByOne(awce.threeToFour),awce.fourToFive,awce.fiveToSix)
      case ("4","3","2","2") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,awce.oneToTwo,CommonUtils.decrementByOne(awce.twoToThree),CommonUtils.incrementByOne(awce.threeToFour),awce.fourToFive,awce.fiveToSix)

      case ("4","4","0","1") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,CommonUtils.decrementByOne(awce.threeToFour),awce.fourToFive,awce.fiveToSix)
      case ("4","4","0","2") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,CommonUtils.decrementByOne(awce.threeToFour),awce.fourToFive,awce.fiveToSix)
      case ("5","4","0","1") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,CommonUtils.decrementByOne(awce.threeToFour),awce.fourToFive,awce.fiveToSix)
      case ("5","4","0","2") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,CommonUtils.decrementByOne(awce.threeToFour),awce.fourToFive,awce.fiveToSix)
      case ("4","4","1","0") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,CommonUtils.incrementByOne(awce.threeToFour),awce.fourToFive,awce.fiveToSix)
      case ("5","4","1","0") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,awce.threeToFour,CommonUtils.incrementByOne(awce.fourToFive),awce.fiveToSix)
      case ("5","4","1","1") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,CommonUtils.decrementByOne(awce.threeToFour),CommonUtils.incrementByOne(awce.fourToFive),awce.fiveToSix)
      case ("5","4","1","2") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,CommonUtils.decrementByOne(awce.threeToFour),CommonUtils.incrementByOne(awce.fourToFive),awce.fiveToSix)
      case ("4","4","2","0") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,CommonUtils.incrementByOne(awce.threeToFour),awce.fourToFive,awce.fiveToSix)
      case ("5","4","2","0") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,awce.threeToFour,CommonUtils.incrementByOne(awce.fourToFive),awce.fiveToSix)
      case ("5","4","2","1") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,CommonUtils.decrementByOne(awce.threeToFour),CommonUtils.incrementByOne(awce.fourToFive),awce.fiveToSix)
      case ("5","4","2","2") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,CommonUtils.decrementByOne(awce.threeToFour),CommonUtils.incrementByOne(awce.fourToFive),awce.fiveToSix)

      case ("5","5","0","1") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,awce.threeToFour,CommonUtils.decrementByOne(awce.fourToFive),awce.fiveToSix)
      case ("5","5","0","2") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,awce.threeToFour,CommonUtils.decrementByOne(awce.fourToFive),awce.fiveToSix)
      case ("6","5","0","1") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,awce.threeToFour,CommonUtils.decrementByOne(awce.fourToFive),awce.fiveToSix)
      case ("6","5","0","2") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,awce.threeToFour,CommonUtils.decrementByOne(awce.fourToFive),awce.fiveToSix)
      case ("5","5","1","0") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,awce.threeToFour,CommonUtils.incrementByOne(awce.fourToFive),awce.fiveToSix)
      case ("6","5","1","0") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,awce.threeToFour,awce.fourToFive,CommonUtils.incrementByOne(awce.fiveToSix))
      case ("6","5","1","1") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,awce.threeToFour,CommonUtils.decrementByOne(awce.fourToFive),CommonUtils.incrementByOne(awce.fiveToSix))
      case ("6","5","1","2") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,awce.threeToFour,CommonUtils.decrementByOne(awce.fourToFive),CommonUtils.incrementByOne(awce.fiveToSix))
      case ("5","5","2","0") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,awce.threeToFour,CommonUtils.incrementByOne(awce.fourToFive),awce.fiveToSix)
      case ("6","5","2","0") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,awce.threeToFour,awce.fourToFive,CommonUtils.incrementByOne(awce.fiveToSix))
      case ("6","5","2","1") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,awce.threeToFour,CommonUtils.decrementByOne(awce.fourToFive),CommonUtils.incrementByOne(awce.fiveToSix))
      case ("6","5","2","2") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,awce.threeToFour,CommonUtils.decrementByOne(awce.fourToFive),CommonUtils.incrementByOne(awce.fiveToSix))

      case (_,_,_,x) if(!x.equals("-1")) => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,awce.threeToFour,awce.fourToFive,awce.fiveToSix)

      case ("1","-1","1","-1") => AgeWiseConsolidatedEntity(awce.code,CommonUtils.incrementByOne(awce.zeroToOne),awce.oneToTwo,awce.twoToThree,awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("2","-1","1","-1") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,CommonUtils.incrementByOne(awce.oneToTwo),awce.twoToThree,awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("3","-1","1","-1") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,awce.oneToTwo,CommonUtils.incrementByOne(awce.twoToThree),awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("4","-1","1","-1") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,CommonUtils.incrementByOne(awce.threeToFour),awce.fourToFive,awce.fiveToSix)
      case ("5","-1","1","-1") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,awce.threeToFour,CommonUtils.incrementByOne(awce.fourToFive),awce.fiveToSix)
      case ("6","-1","1","-1") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,awce.threeToFour,awce.fourToFive,CommonUtils.incrementByOne(awce.fiveToSix))
      case ("1","-1","2","-1") => AgeWiseConsolidatedEntity(awce.code,CommonUtils.incrementByOne(awce.zeroToOne),awce.oneToTwo,awce.twoToThree,awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("2","-1","2","-1") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,CommonUtils.incrementByOne(awce.oneToTwo),awce.twoToThree,awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("3","-1","2","-1") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,awce.oneToTwo,CommonUtils.incrementByOne(awce.twoToThree),awce.threeToFour,awce.fourToFive,awce.fiveToSix)
      case ("4","-1","2","-1") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,CommonUtils.incrementByOne(awce.threeToFour),awce.fourToFive,awce.fiveToSix)
      case ("5","-1","2","-1") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,awce.threeToFour,CommonUtils.incrementByOne(awce.fourToFive),awce.fiveToSix)
      case ("6","-1","2","-1") => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,awce.threeToFour,awce.fourToFive,CommonUtils.incrementByOne(awce.fiveToSix))

      case (_,_,_,_) => AgeWiseConsolidatedEntity(awce.code,awce.zeroToOne,awce.oneToTwo,awce.twoToThree,awce.threeToFour,awce.fourToFive,awce.fiveToSix)

    }

    updatedAWCE
  }

}
