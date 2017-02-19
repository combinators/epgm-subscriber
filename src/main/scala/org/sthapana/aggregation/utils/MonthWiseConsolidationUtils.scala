package org.sthapana.aggregation.utils

import org.sthapana.aggregation.dataobjects.MonthWiseConsolidatedEntity
import org.sthapana.aggregation.engine.{DocumentDbConnector, RedisConnector}

/**
  * Created by chocoklate on 14/2/17.
  */
class MonthWiseConsolidationUtils {

  def updateMonthWiseConsolidated(dc:DocumentDbConnector, code: String,
                                  currentMonth: String, currentYear: String,
                                  currentGrade: String, previousGrade: String):MonthWiseConsolidatedEntity = {

    val monthWiseConsolidatedEntity = dc.getMonthWiseConsolidatedRecord(code)
//    dc.deleteMonthWiseConsolidatedRecord(code)
    getUpdatedMonthWiseConsolidatedEntity(
      monthWiseConsolidatedEntity,currentMonth,currentYear,currentGrade,previousGrade)

  }

  private def getUpdatedMonthWiseConsolidatedEntity
  (mwce: MonthWiseConsolidatedEntity,currentMonth: String, currentYear: String,
   currentGrade: String, previousGrade: String): MonthWiseConsolidatedEntity = {

    val updatedMWCE = (currentMonth,currentGrade,previousGrade) match {

      case ("01","0","1") => MonthWiseConsolidatedEntity("dashboard", mwce.code, CommonUtils.decrementByOne(mwce.jan), mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("01","0","2") => MonthWiseConsolidatedEntity("dashboard", mwce.code, CommonUtils.decrementByOne(mwce.jan), mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("01","1","0") => MonthWiseConsolidatedEntity("dashboard", mwce.code, CommonUtils.incrementByOne(mwce.jan), mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("01","2","0") => MonthWiseConsolidatedEntity("dashboard", mwce.code, CommonUtils.incrementByOne(mwce.jan), mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("02","0","1") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, CommonUtils.decrementByOne(mwce.feb), mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("02","0","2") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, CommonUtils.decrementByOne(mwce.feb), mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("02","1","0") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, CommonUtils.incrementByOne(mwce.feb), mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("02","2","0") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, CommonUtils.incrementByOne(mwce.feb), mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("03","0","1") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, CommonUtils.decrementByOne(mwce.mar), mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("03","0","2") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, CommonUtils.decrementByOne(mwce.mar), mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("03","1","0") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, CommonUtils.incrementByOne(mwce.mar), mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("03","2","0") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, CommonUtils.incrementByOne(mwce.mar), mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("04","0","1") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, CommonUtils.decrementByOne(mwce.apr), mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("04","0","2") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, CommonUtils.decrementByOne(mwce.apr), mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("04","1","0") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, CommonUtils.incrementByOne(mwce.apr), mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("04","2","0") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, CommonUtils.incrementByOne(mwce.apr), mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("05","0","1") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, CommonUtils.decrementByOne(mwce.may), mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("05","0","2") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, CommonUtils.decrementByOne(mwce.may), mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("05","1","0") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, CommonUtils.incrementByOne(mwce.may), mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("05","2","0") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, CommonUtils.incrementByOne(mwce.may), mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("06","0","1") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, CommonUtils.decrementByOne(mwce.jun), mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("06","0","2") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, CommonUtils.decrementByOne(mwce.jun), mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("06","1","0") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, CommonUtils.incrementByOne(mwce.jun), mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("06","2","0") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, CommonUtils.incrementByOne(mwce.jun), mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("07","0","1") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, CommonUtils.decrementByOne(mwce.jul), mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("07","0","2") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, CommonUtils.decrementByOne(mwce.jul), mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("07","1","0") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, CommonUtils.incrementByOne(mwce.jul), mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("07","2","0") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, CommonUtils.incrementByOne(mwce.jul), mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("08","0","1") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, CommonUtils.decrementByOne(mwce.aug), mwce.sep, mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("08","0","2") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, CommonUtils.decrementByOne(mwce.aug), mwce.sep, mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("08","1","0") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, CommonUtils.incrementByOne(mwce.aug), mwce.sep, mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("08","2","0") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, CommonUtils.incrementByOne(mwce.aug), mwce.sep, mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("09","0","1") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, CommonUtils.decrementByOne(mwce.sep), mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("09","0","2") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, CommonUtils.decrementByOne(mwce.sep), mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("09","1","0") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, CommonUtils.incrementByOne(mwce.sep), mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("09","2","0") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, CommonUtils.incrementByOne(mwce.sep), mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("10","0","1") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, CommonUtils.decrementByOne(mwce.oct), mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("10","0","2") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, CommonUtils.decrementByOne(mwce.oct), mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("10","1","0") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, CommonUtils.incrementByOne(mwce.oct), mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("10","2","0") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, CommonUtils.incrementByOne(mwce.oct), mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("11","0","1") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, CommonUtils.decrementByOne(mwce.nov), mwce.dec,currentMonth,currentYear)
      case ("11","0","2") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, CommonUtils.decrementByOne(mwce.nov), mwce.dec,currentMonth,currentYear)
      case ("11","1","0") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, CommonUtils.incrementByOne(mwce.nov), mwce.dec,currentMonth,currentYear)
      case ("11","2","0") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, CommonUtils.incrementByOne(mwce.nov), mwce.dec,currentMonth,currentYear)
      case ("12","0","1") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, CommonUtils.decrementByOne(mwce.dec),currentMonth,currentYear)
      case ("12","0","2") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, CommonUtils.decrementByOne(mwce.dec),currentMonth,currentYear)
      case ("12","1","0") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, CommonUtils.incrementByOne(mwce.dec),currentMonth,currentYear)
      case ("12","2","0") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, CommonUtils.incrementByOne(mwce.dec),currentMonth,currentYear)

      case (_,_,x) if(!x.equals("-1")) => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)

      case ("01","1","-1") => MonthWiseConsolidatedEntity("dashboard", mwce.code,CommonUtils.incrementByOne(mwce.jan), mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("01","2","-1") => MonthWiseConsolidatedEntity("dashboard", mwce.code,CommonUtils.incrementByOne(mwce.jan), mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("02","1","-1") => MonthWiseConsolidatedEntity("dashboard", mwce.code,mwce.jan, CommonUtils.incrementByOne(mwce.feb), mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("02","2","-1") => MonthWiseConsolidatedEntity("dashboard", mwce.code,mwce.jan, CommonUtils.incrementByOne(mwce.feb), mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("03","1","-1") => MonthWiseConsolidatedEntity("dashboard", mwce.code,mwce.jan, mwce.feb, CommonUtils.incrementByOne(mwce.mar), mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("03","2","-1") => MonthWiseConsolidatedEntity("dashboard", mwce.code,mwce.jan, mwce.feb, CommonUtils.incrementByOne(mwce.mar), mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("04","1","-1") => MonthWiseConsolidatedEntity("dashboard", mwce.code,mwce.jan, mwce.feb, mwce.mar, CommonUtils.incrementByOne(mwce.apr), mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("04","2","-1") => MonthWiseConsolidatedEntity("dashboard", mwce.code,mwce.jan, mwce.feb, mwce.mar, CommonUtils.incrementByOne(mwce.apr), mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("05","1","-1") => MonthWiseConsolidatedEntity("dashboard", mwce.code,mwce.jan, mwce.feb, mwce.mar, mwce.apr, CommonUtils.incrementByOne(mwce.may), mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("05","2","-1") => MonthWiseConsolidatedEntity("dashboard", mwce.code,mwce.jan, mwce.feb, mwce.mar, mwce.apr, CommonUtils.incrementByOne(mwce.may), mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("06","1","-1") => MonthWiseConsolidatedEntity("dashboard", mwce.code,mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, CommonUtils.incrementByOne(mwce.jun), mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("06","2","-1") => MonthWiseConsolidatedEntity("dashboard", mwce.code,mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, CommonUtils.incrementByOne(mwce.jun), mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("07","1","-1") => MonthWiseConsolidatedEntity("dashboard", mwce.code,mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, CommonUtils.incrementByOne(mwce.jul), mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("07","2","-1") => MonthWiseConsolidatedEntity("dashboard", mwce.code,mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, CommonUtils.incrementByOne(mwce.jul), mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("08","1","-1") => MonthWiseConsolidatedEntity("dashboard", mwce.code,mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, CommonUtils.incrementByOne(mwce.aug), mwce.sep, mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("08","2","-1") => MonthWiseConsolidatedEntity("dashboard", mwce.code,mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, CommonUtils.incrementByOne(mwce.aug), mwce.sep, mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("09","1","-1") => MonthWiseConsolidatedEntity("dashboard", mwce.code,mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, CommonUtils.incrementByOne(mwce.sep), mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("09","2","-1") => MonthWiseConsolidatedEntity("dashboard", mwce.code,mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, CommonUtils.incrementByOne(mwce.sep), mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("10","1","-1") => MonthWiseConsolidatedEntity("dashboard", mwce.code,mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, CommonUtils.incrementByOne(mwce.oct), mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("10","2","-1") => MonthWiseConsolidatedEntity("dashboard", mwce.code,mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, CommonUtils.incrementByOne(mwce.oct), mwce.nov, mwce.dec,currentMonth,currentYear)
      case ("11","1","-1") => MonthWiseConsolidatedEntity("dashboard", mwce.code,mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, CommonUtils.incrementByOne(mwce.nov), mwce.dec,currentMonth,currentYear)
      case ("11","2","-1") => MonthWiseConsolidatedEntity("dashboard", mwce.code,mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, CommonUtils.incrementByOne(mwce.nov), mwce.dec,currentMonth,currentYear)
      case ("12","1","-1") => MonthWiseConsolidatedEntity("dashboard", mwce.code,mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, CommonUtils.incrementByOne(mwce.dec),currentMonth,currentYear)
      case ("12","2","-1") => MonthWiseConsolidatedEntity("dashboard", mwce.code,mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, CommonUtils.incrementByOne(mwce.dec),currentMonth,currentYear)

      case (_,_,_) => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec,currentMonth,currentYear)
    }

    updatedMWCE
  }

}
