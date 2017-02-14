package org.sthapana.aggregation.utils

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
