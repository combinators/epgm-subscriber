package org.sthapana.aggregation.utils

import com.google.gson.Gson
import com.microsoft.azure.documentdb.Document
import org.sthapana.aggregation.dataobjects.AgeWiseConsolidatedEntity

/**
  * Created by chocoklate on 14/2/17.
  */
object CommonUtils {

  def incrementByOne(count: String):String = {
    (count.toLong + 1).toString
  }

  def decrementByOne(count: String):String = {
    (count.toLong - 1).toString
  }

}
