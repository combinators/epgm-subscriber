package org.sthapana.epgmsubscriber

object Marshaller {
  def apply(record: Record): LogData = {
    val map = record.toMap
    LogData(
      map("anganwadicode"),
      map("childcode"),
      map("weight"),
      map("height"),
      map("bmi"),
      map("whounderweight"),
      map("iap"),
      map("day"),
      map("month"),
      map("year"),
      map("wasting"),
      map("stunting"),
      map("min"),
      map("hour")
    )
  }
}


case class LogData(
anganwadicode:String,
childcode:String,
weight:String,
height:String,
bmi:String,
whounderweight:String,
iap:String,
day:String,
month:String,
year:String,
wasting:String,
stunting:String,
min:String,
hour:String)
