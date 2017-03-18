package org.sthapana.aggregation.utils

import org.sthapana.aggregation.dataobjects.GenderWiseConsolidatedEntity

class GenderWiseConsolidationUtils() {

  def updateGenderWiseConsolidated(ge: GenderWiseConsolidatedEntity, gender: String,
                                   currentGrade: String): GenderWiseConsolidatedEntity = {
    getUpdatedGenderWiseConsolidatedEntity(
      ge, gender, currentGrade)
  }

  private def getUpdatedGenderWiseConsolidatedEntity
  (gwce: GenderWiseConsolidatedEntity, gender: String,
   currentGrade: String): GenderWiseConsolidatedEntity = {
    val updatedGWCE = (currentGrade, gender) match {
      case ("1", "M") => GenderWiseConsolidatedEntity("dashboard", gwce.code, CommonUtils.incrementByOne(gwce.male), gwce.female)
      case ("2", "M") => GenderWiseConsolidatedEntity("dashboard", gwce.code, CommonUtils.incrementByOne(gwce.male), gwce.female)
      case ("1", "F") => GenderWiseConsolidatedEntity("dashboard", gwce.code, gwce.male, CommonUtils.incrementByOne(gwce.female))
      case ("2", "F") => GenderWiseConsolidatedEntity("dashboard", gwce.code, gwce.male, CommonUtils.incrementByOne(gwce.female))
      case (_, _) => GenderWiseConsolidatedEntity("dashboard", gwce.code, gwce.male, gwce.female)
    }
    updatedGWCE
  }

}
