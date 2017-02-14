package org.sthapana.aggregation.utils

import org.sthapana.aggregation.dataobjects.GenderWiseConsolidatedEntity
import org.sthapana.aggregation.engine.RedisConnector

/**
  * Created by chocoklate on 14/2/17.
  */
class GenderWiseConsolidationUtils {

  def updateGenderWiseConsolidated(rc:RedisConnector, code: String,gender: String,
                                   currentGrade: String, previousGrade: String) = {

    val genderWiseConsolidatedEntity = rc.getGenderWiseConsolidatedRecord(code)
    rc.deleteGenderWiseConsolidatedRecord(code)
    rc.insertGenderWiseConsolidatedRecord(getUpdatedGenderWiseConsolidatedEntity(
      genderWiseConsolidatedEntity,gender,currentGrade,previousGrade))

  }

  private def getUpdatedGenderWiseConsolidatedEntity
  (gwce: GenderWiseConsolidatedEntity,gender: String,
   currentGrade:String, previousGrade:String): GenderWiseConsolidatedEntity = {

    val updatedGWCE = (currentGrade,previousGrade,gender) match {
      case ("0","1","M") => GenderWiseConsolidatedEntity(gwce.code,CommonUtils.decrementByOne(gwce.male),gwce.female)
      case ("0","2","M") => GenderWiseConsolidatedEntity(gwce.code,CommonUtils.decrementByOne(gwce.male),gwce.female)
      case ("1","0","M") => GenderWiseConsolidatedEntity(gwce.code,CommonUtils.incrementByOne(gwce.male),gwce.female)
      case ("2","0","M") => GenderWiseConsolidatedEntity(gwce.code,CommonUtils.incrementByOne(gwce.male),gwce.female)
      case ("0","1","F") => GenderWiseConsolidatedEntity(gwce.code,gwce.male,CommonUtils.decrementByOne(gwce.female))
      case ("0","2","F") => GenderWiseConsolidatedEntity(gwce.code,gwce.male,CommonUtils.decrementByOne(gwce.female))
      case ("1","0","F") => GenderWiseConsolidatedEntity(gwce.code,gwce.male,CommonUtils.incrementByOne(gwce.female))
      case ("2","0","F") => GenderWiseConsolidatedEntity(gwce.code,gwce.male,CommonUtils.incrementByOne(gwce.female))
      case (_,x,_) if(!x.equals("-1")) => GenderWiseConsolidatedEntity(gwce.code,gwce.male,gwce.female)
      case ("1","-1","M") => GenderWiseConsolidatedEntity(gwce.code,CommonUtils.incrementByOne(gwce.male),gwce.female)
      case ("2","-1","M") => GenderWiseConsolidatedEntity(gwce.code,CommonUtils.incrementByOne(gwce.male),gwce.female)
      case ("1","-1","F") => GenderWiseConsolidatedEntity(gwce.code,gwce.male,CommonUtils.incrementByOne(gwce.female))
      case ("2","-1","F") => GenderWiseConsolidatedEntity(gwce.code,gwce.male,CommonUtils.incrementByOne(gwce.female))
      case (_,_,_)  => GenderWiseConsolidatedEntity(gwce.code,gwce.male,gwce.female)
    }

    updatedGWCE
  }

}
