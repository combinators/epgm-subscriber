package org.sthapana.epgmsubscriber

object Marshaller {
  def apply(record: Record): LogEntry = {
    val map = record.toMap
    LogEntry(
      map("aanganwadicode"),
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
      map("minutes"),
      map("hours")
    )
  }
}


case class LogEntry(
aanganwadicode:String,
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
minutes:String,
hours:String)
