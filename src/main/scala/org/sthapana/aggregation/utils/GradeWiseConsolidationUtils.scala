package org.sthapana.aggregation.utils

import org.sthapana.aggregation.dataobjects.GradeWiseConsolidatedEntity
import org.sthapana.aggregation.engine.{DocumentDbConnector, RedisConnector}

/**
  * Created by chocoklate on 14/2/17.
  */

class GradeWiseConsolidationUtils() {
  def updateGradeWiseConsolidated(ge: GradeWiseConsolidatedEntity, currentGrade: String, previousGrade: String): GradeWiseConsolidatedEntity = {

    val gradeWiseConsolidatedEntity = ge
    //    dc.deleteGradeWiseConsolidatedRecord(code)
    getUpdatedGradeWiseConsolidatedEntity(
      gradeWiseConsolidatedEntity,currentGrade,previousGrade)

  }

  private def getUpdatedGradeWiseConsolidatedEntity
  (gwce: GradeWiseConsolidatedEntity,
   currentGrade: String, previousGrade: String): GradeWiseConsolidatedEntity = {

    val updatedGWCE = (currentGrade,previousGrade) match {
      case ("0","1") => GradeWiseConsolidatedEntity("dashboard",gwce.code,gwce.suw,CommonUtils.decrementByOne(gwce.muw),CommonUtils.incrementByOne(gwce.normal),gwce.total)
      case ("0","2") => GradeWiseConsolidatedEntity("dashboard",gwce.code,CommonUtils.decrementByOne(gwce.suw),gwce.muw,CommonUtils.incrementByOne(gwce.normal),gwce.total)
      case ("1","0") => GradeWiseConsolidatedEntity("dashboard",gwce.code,gwce.suw,CommonUtils.incrementByOne(gwce.muw),CommonUtils.decrementByOne(gwce.normal),gwce.total)
      case ("1","2") => GradeWiseConsolidatedEntity("dashboard",gwce.code,CommonUtils.decrementByOne(gwce.suw),CommonUtils.incrementByOne(gwce.muw),gwce.normal,gwce.total)
      case ("2","0") => GradeWiseConsolidatedEntity("dashboard",gwce.code,CommonUtils.incrementByOne(gwce.suw),gwce.muw,CommonUtils.decrementByOne(gwce.normal),gwce.total)
      case ("2","1") => GradeWiseConsolidatedEntity("dashboard",gwce.code,CommonUtils.incrementByOne(gwce.suw),CommonUtils.decrementByOne(gwce.muw),gwce.normal,gwce.total)
      case (_,x) if(!x.equals("-1")) => GradeWiseConsolidatedEntity("dashboard",gwce.code,gwce.suw,gwce.muw,gwce.normal,gwce.total)
      case ("0","-1") => GradeWiseConsolidatedEntity("dashboard",gwce.code,gwce.suw,gwce.muw,CommonUtils.incrementByOne(gwce.normal),CommonUtils.incrementByOne(gwce.total))
      case ("1","-1") => GradeWiseConsolidatedEntity("dashboard",gwce.code,gwce.suw,CommonUtils.incrementByOne(gwce.muw),gwce.normal,CommonUtils.incrementByOne(gwce.total))
      case ("2","-1") => GradeWiseConsolidatedEntity("dashboard",gwce.code,CommonUtils.incrementByOne(gwce.suw),gwce.muw,gwce.normal,CommonUtils.incrementByOne(gwce.total))
      case (_,_) => GradeWiseConsolidatedEntity("dashboard",gwce.code,gwce.suw,gwce.muw,gwce.normal,gwce.total)
    }

    updatedGWCE
  }

}

/*class GradeWiseConsolidationUtils() {
  def updateGradeWiseConsolidated(dc:DocumentDbConnector,code: String,
                                  currentGrade: String, previousGrade: String): GradeWiseConsolidatedEntity = {

    val gradeWiseConsolidatedEntity = dc.getGradeWiseConsolidatedRecord(code)
//    dc.deleteGradeWiseConsolidatedRecord(code)
    getUpdatedGradeWiseConsolidatedEntity(
      gradeWiseConsolidatedEntity,currentGrade,previousGrade)

  }

  private def getUpdatedGradeWiseConsolidatedEntity
  (gwce: GradeWiseConsolidatedEntity,
   currentGrade: String, previousGrade: String): GradeWiseConsolidatedEntity = {

    val updatedGWCE = (currentGrade,previousGrade) match {
      case ("0","1") => GradeWiseConsolidatedEntity("dashboard",gwce.code,gwce.suw,CommonUtils.decrementByOne(gwce.muw),CommonUtils.incrementByOne(gwce.normal),gwce.total)
      case ("0","2") => GradeWiseConsolidatedEntity("dashboard",gwce.code,CommonUtils.decrementByOne(gwce.suw),gwce.muw,CommonUtils.incrementByOne(gwce.normal),gwce.total)
      case ("1","0") => GradeWiseConsolidatedEntity("dashboard",gwce.code,gwce.suw,CommonUtils.incrementByOne(gwce.muw),CommonUtils.decrementByOne(gwce.normal),gwce.total)
      case ("1","2") => GradeWiseConsolidatedEntity("dashboard",gwce.code,CommonUtils.decrementByOne(gwce.suw),CommonUtils.incrementByOne(gwce.muw),gwce.normal,gwce.total)
      case ("2","0") => GradeWiseConsolidatedEntity("dashboard",gwce.code,CommonUtils.incrementByOne(gwce.suw),gwce.muw,CommonUtils.decrementByOne(gwce.normal),gwce.total)
      case ("2","1") => GradeWiseConsolidatedEntity("dashboard",gwce.code,CommonUtils.incrementByOne(gwce.suw),CommonUtils.decrementByOne(gwce.muw),gwce.normal,gwce.total)
      case (_,x) if(!x.equals("-1")) => GradeWiseConsolidatedEntity("dashboard",gwce.code,gwce.suw,gwce.muw,gwce.normal,gwce.total)
      case ("0","-1") => GradeWiseConsolidatedEntity("dashboard",gwce.code,gwce.suw,gwce.muw,CommonUtils.incrementByOne(gwce.normal),CommonUtils.incrementByOne(gwce.total))
      case ("1","-1") => GradeWiseConsolidatedEntity("dashboard",gwce.code,gwce.suw,CommonUtils.incrementByOne(gwce.muw),gwce.normal,CommonUtils.incrementByOne(gwce.total))
      case ("2","-1") => GradeWiseConsolidatedEntity("dashboard",gwce.code,CommonUtils.incrementByOne(gwce.suw),gwce.muw,gwce.normal,CommonUtils.incrementByOne(gwce.total))
      case (_,_) => GradeWiseConsolidatedEntity("dashboard",gwce.code,gwce.suw,gwce.muw,gwce.normal,gwce.total)
    }

    updatedGWCE
  }

}*/
