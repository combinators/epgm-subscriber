package org.sthapana.aggregation.utils

import org.sthapana.aggregation.dataobjects.GradeWiseConsolidatedEntity

class GradeWiseConsolidationUtils() {
  def updateGradeWiseConsolidated(ge: GradeWiseConsolidatedEntity,
                                  currentGrade: String): GradeWiseConsolidatedEntity = {
    getUpdatedGradeWiseConsolidatedEntity(ge, currentGrade)
  }

  private def getUpdatedGradeWiseConsolidatedEntity
  (gwce: GradeWiseConsolidatedEntity,
   currentGrade: String): GradeWiseConsolidatedEntity = {
    val updatedGWCE = currentGrade match {
      case "0" => GradeWiseConsolidatedEntity("dashboard", gwce.code, gwce.suw, gwce.muw, CommonUtils.incrementByOne(gwce.normal), CommonUtils.incrementByOne(gwce.total))
      case "1" => GradeWiseConsolidatedEntity("dashboard", gwce.code, gwce.suw, CommonUtils.incrementByOne(gwce.muw), gwce.normal, CommonUtils.incrementByOne(gwce.total))
      case "2" => GradeWiseConsolidatedEntity("dashboard", gwce.code, CommonUtils.incrementByOne(gwce.suw), gwce.muw, gwce.normal, CommonUtils.incrementByOne(gwce.total))
      case _ => GradeWiseConsolidatedEntity("dashboard", gwce.code, gwce.suw, gwce.muw, gwce.normal, gwce.total)
    }
    updatedGWCE
  }

}