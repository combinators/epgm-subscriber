package org.sthapana.aggregation.utils

import org.sthapana.aggregation.dataobjects.MonthWiseConsolidatedEntity

class MonthWiseConsolidationUtils() {

  def updateMonthWiseConsolidated(me: MonthWiseConsolidatedEntity,
                                  currentMonth: String, currentYear: String,
                                  currentGrade: String): MonthWiseConsolidatedEntity = {
    getUpdatedMonthWiseConsolidatedEntity(
      me, currentMonth, currentYear, currentGrade)
  }

  private def getUpdatedMonthWiseConsolidatedEntity
  (mwce: MonthWiseConsolidatedEntity, currentMonth: String, currentYear: String,
   currentGrade: String): MonthWiseConsolidatedEntity = {
    val updatedMWCE = (currentMonth, currentGrade) match {
      case ("01", "1") => MonthWiseConsolidatedEntity("dashboard", mwce.code, CommonUtils.incrementByOne(mwce.jan), mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec, currentMonth, currentYear)
      case ("01", "2") => MonthWiseConsolidatedEntity("dashboard", mwce.code, CommonUtils.incrementByOne(mwce.jan), mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec, currentMonth, currentYear)
      case ("02", "1") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, CommonUtils.incrementByOne(mwce.feb), mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec, currentMonth, currentYear)
      case ("02", "2") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, CommonUtils.incrementByOne(mwce.feb), mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec, currentMonth, currentYear)
      case ("03", "1") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, CommonUtils.incrementByOne(mwce.mar), mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec, currentMonth, currentYear)
      case ("03", "2") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, CommonUtils.incrementByOne(mwce.mar), mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec, currentMonth, currentYear)
      case ("04", "1") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, CommonUtils.incrementByOne(mwce.apr), mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec, currentMonth, currentYear)
      case ("04", "2") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, CommonUtils.incrementByOne(mwce.apr), mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec, currentMonth, currentYear)
      case ("05", "1") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, CommonUtils.incrementByOne(mwce.may), mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec, currentMonth, currentYear)
      case ("05", "2") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, CommonUtils.incrementByOne(mwce.may), mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec, currentMonth, currentYear)
      case ("06", "1") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, CommonUtils.incrementByOne(mwce.jun), mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec, currentMonth, currentYear)
      case ("06", "2") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, CommonUtils.incrementByOne(mwce.jun), mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec, currentMonth, currentYear)
      case ("07", "1") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, CommonUtils.incrementByOne(mwce.jul), mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec, currentMonth, currentYear)
      case ("07", "2") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, CommonUtils.incrementByOne(mwce.jul), mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec, currentMonth, currentYear)
      case ("08", "1") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, CommonUtils.incrementByOne(mwce.aug), mwce.sep, mwce.oct, mwce.nov, mwce.dec, currentMonth, currentYear)
      case ("08", "2") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, CommonUtils.incrementByOne(mwce.aug), mwce.sep, mwce.oct, mwce.nov, mwce.dec, currentMonth, currentYear)
      case ("09", "1") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, CommonUtils.incrementByOne(mwce.sep), mwce.oct, mwce.nov, mwce.dec, currentMonth, currentYear)
      case ("09", "2") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, CommonUtils.incrementByOne(mwce.sep), mwce.oct, mwce.nov, mwce.dec, currentMonth, currentYear)
      case ("10", "1") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, CommonUtils.incrementByOne(mwce.oct), mwce.nov, mwce.dec, currentMonth, currentYear)
      case ("10", "2") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, CommonUtils.incrementByOne(mwce.oct), mwce.nov, mwce.dec, currentMonth, currentYear)
      case ("11", "1") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, CommonUtils.incrementByOne(mwce.nov), mwce.dec, currentMonth, currentYear)
      case ("11", "2") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, CommonUtils.incrementByOne(mwce.nov), mwce.dec, currentMonth, currentYear)
      case ("12", "1") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, CommonUtils.incrementByOne(mwce.dec), currentMonth, currentYear)
      case ("12", "2") => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, CommonUtils.incrementByOne(mwce.dec), currentMonth, currentYear)
      case (_, _) => MonthWiseConsolidatedEntity("dashboard", mwce.code, mwce.jan, mwce.feb, mwce.mar, mwce.apr, mwce.may, mwce.jun, mwce.jul, mwce.aug, mwce.sep, mwce.oct, mwce.nov, mwce.dec, currentMonth, currentYear)
    }
    updatedMWCE
  }

}
