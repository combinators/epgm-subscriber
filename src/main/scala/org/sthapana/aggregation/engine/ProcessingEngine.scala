package org.sthapana.aggregation.engine

import org.sthapana.aggregation.dataobjects._
import org.sthapana.aggregation.utils.{AgeWiseConsolidationUtils, GenderWiseConsolidationUtils, GradeWiseConsolidationUtils, MonthWiseConsolidationUtils}

/**
  * Created by chocoklate on 14/2/17.
  */
class ProcessingEngine {

  def updateDB(updateEntity: UpdateEntity): Unit = {
    val redisClient = RedisConnector("localhost",6379)

    new GradeWiseConsolidationUtils().updateGradeWiseConsolidated(redisClient,updateEntity.stateCode,
      updateEntity.currentGrade,updateEntity.previousGrade)

    new GenderWiseConsolidationUtils().updateGenderWiseConsolidated(redisClient,updateEntity.stateCode,
      updateEntity.gender,updateEntity.currentGrade,updateEntity.previousGrade)

    new AgeWiseConsolidationUtils().updateAgeWiseConsolidated(redisClient,updateEntity.stateCode,
      updateEntity.currentAge,updateEntity.previousAge,
      updateEntity.currentGrade,updateEntity.previousGrade)

    new MonthWiseConsolidationUtils().updateMonthWiseConsolidated(redisClient,updateEntity.stateCode,
      updateEntity.currentMonth,updateEntity.currentYear,
      updateEntity.currentGrade,updateEntity.previousGrade)
  }

}
