package org.sthapana.aggregation.utils

object CommonUtils {

  def incrementByOne(count: String): String = {
    (count.toLong + 1).toString
  }

  def decrementByOne(count: String): String = {
    (if (count.toLong <= 0) 0 else count.toLong - 1).toString
  }

}
