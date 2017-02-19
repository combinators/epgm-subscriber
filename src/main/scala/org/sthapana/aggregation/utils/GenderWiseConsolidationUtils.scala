package org.sthapana.aggregation.utils

import org.sthapana.aggregation.dataobjects.GenderWiseConsolidatedEntity
import org.sthapana.aggregation.engine.{DocumentDbConnector, RedisConnector}

/**
  * Created by chocoklate on 14/2/17.
  */
class GenderWiseConsolidationUtils() {

  def updateGenderWiseConsolidated(ge: GenderWiseConsolidatedEntity, gender: String,
                                   currentGrade: String, previousGrade: String): GenderWiseConsolidatedEntity = {

    val genderWiseConsolidatedEntity = ge
//    dc.deleteGenderWiseConsolidatedRecord(code)
    getUpdatedGenderWiseConsolidatedEntity(
      genderWiseConsolidatedEntity,gender,currentGrade,previousGrade)

  }

  private def getUpdatedGenderWiseConsolidatedEntity
  (gwce: GenderWiseConsolidatedEntity,gender: String,
   currentGrade:String, previousGrade:String): GenderWiseConsolidatedEntity = {

    val updatedGWCE = (currentGrade,previousGrade,gender) match {
      case ("0","1","M") => GenderWiseConsolidatedEntity("dashboard", gwce.code,CommonUtils.decrementByOne(gwce.male),gwce.female)
      case ("0","2","M") => GenderWiseConsolidatedEntity("dashboard", gwce.code,CommonUtils.decrementByOne(gwce.male),gwce.female)
      case ("1","0","M") => GenderWiseConsolidatedEntity("dashboard", gwce.code,CommonUtils.incrementByOne(gwce.male),gwce.female)
      case ("2","0","M") => GenderWiseConsolidatedEntity("dashboard", gwce.code,CommonUtils.incrementByOne(gwce.male),gwce.female)
      case ("0","1","F") => GenderWiseConsolidatedEntity("dashboard", gwce.code,gwce.male,CommonUtils.decrementByOne(gwce.female))
      case ("0","2","F") => GenderWiseConsolidatedEntity("dashboard", gwce.code,gwce.male,CommonUtils.decrementByOne(gwce.female))
      case ("1","0","F") => GenderWiseConsolidatedEntity("dashboard", gwce.code,gwce.male,CommonUtils.incrementByOne(gwce.female))
      case ("2","0","F") => GenderWiseConsolidatedEntity("dashboard", gwce.code,gwce.male,CommonUtils.incrementByOne(gwce.female))
      case (_,x,_) if(!x.equals("-1")) => GenderWiseConsolidatedEntity("dashboard", gwce.code,gwce.male,gwce.female)
      case ("1","-1","M") => GenderWiseConsolidatedEntity("dashboard", gwce.code,CommonUtils.incrementByOne(gwce.male),gwce.female)
      case ("2","-1","M") => GenderWiseConsolidatedEntity("dashboard", gwce.code,CommonUtils.incrementByOne(gwce.male),gwce.female)
      case ("1","-1","F") => GenderWiseConsolidatedEntity("dashboard", gwce.code,gwce.male,CommonUtils.incrementByOne(gwce.female))
      case ("2","-1","F") => GenderWiseConsolidatedEntity("dashboard", gwce.code,gwce.male,CommonUtils.incrementByOne(gwce.female))
      case (_,_,_)  => GenderWiseConsolidatedEntity("dashboard", gwce.code,gwce.male,gwce.female)
    }

    updatedGWCE
  }

}
