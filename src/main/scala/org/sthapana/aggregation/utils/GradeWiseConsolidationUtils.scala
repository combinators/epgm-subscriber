package org.sthapana.aggregation.utils

import org.sthapana.aggregation.dataobjects.GradeWiseConsolidatedEntity
import org.sthapana.aggregation.engine.RedisConnector

/**
  * Created by chocoklate on 14/2/17.
  */
class GradeWiseConsolidationUtils {

  def updateGradeWiseConsolidated(rc:RedisConnector, code: String,
                                  currentGrade: String, previousGrade: String) = {

    val gradeWiseConsolidatedEntity = rc.getGradeWiseConsolidatedRecord(code)
    rc.deleteGradeWiseConsolidatedRecord(code)
    rc.insertGradeWiseConsolidatedRecord(getUpdatedGradeWiseConsolidatedEntity(
      gradeWiseConsolidatedEntity,currentGrade,previousGrade))

  }

  private def getUpdatedGradeWiseConsolidatedEntity
  (gwce: GradeWiseConsolidatedEntity,
   currentGrade: String, previousGrade: String): GradeWiseConsolidatedEntity = {

    val updatedGWCE = (currentGrade,previousGrade) match {
      case ("0","1") => GradeWiseConsolidatedEntity(gwce.code,gwce.suw,CommonUtils.decrementByOne(gwce.muw),CommonUtils.incrementByOne(gwce.normal),gwce.total)
      case ("0","2") => GradeWiseConsolidatedEntity(gwce.code,CommonUtils.decrementByOne(gwce.suw),gwce.muw,CommonUtils.incrementByOne(gwce.normal),gwce.total)
      case ("1","0") => GradeWiseConsolidatedEntity(gwce.code,gwce.suw,CommonUtils.incrementByOne(gwce.muw),CommonUtils.decrementByOne(gwce.normal),gwce.total)
      case ("1","2") => GradeWiseConsolidatedEntity(gwce.code,CommonUtils.decrementByOne(gwce.suw),CommonUtils.incrementByOne(gwce.muw),gwce.normal,gwce.total)
      case ("2","0") => GradeWiseConsolidatedEntity(gwce.code,CommonUtils.incrementByOne(gwce.suw),gwce.muw,CommonUtils.decrementByOne(gwce.normal),gwce.total)
      case ("2","1") => GradeWiseConsolidatedEntity(gwce.code,CommonUtils.incrementByOne(gwce.suw),CommonUtils.decrementByOne(gwce.muw),gwce.normal,gwce.total)
      case (_,x) if(!x.equals("-1")) => GradeWiseConsolidatedEntity(gwce.code,gwce.suw,gwce.muw,gwce.normal,gwce.total)
      case ("0","-1") => GradeWiseConsolidatedEntity(gwce.code,gwce.suw,gwce.muw,CommonUtils.incrementByOne(gwce.normal),CommonUtils.incrementByOne(gwce.total))
      case ("1","-1") => GradeWiseConsolidatedEntity(gwce.code,gwce.suw,CommonUtils.incrementByOne(gwce.muw),gwce.normal,CommonUtils.incrementByOne(gwce.total))
      case ("2","-1") => GradeWiseConsolidatedEntity(gwce.code,CommonUtils.incrementByOne(gwce.suw),gwce.muw,gwce.normal,CommonUtils.incrementByOne(gwce.total))
      case (_,_) => GradeWiseConsolidatedEntity(gwce.code,gwce.suw,gwce.muw,gwce.normal,gwce.total)
    }

    updatedGWCE
  }

}
