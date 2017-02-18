package org.sthapana.epgmsubscriber

object Marshaller {
  def apply(record: Record): LogEntry = {
    val map = record.toMap
    LogEntry(
      map("statecode"),
      map("districtcode"),
      map("projectcode"),
      map("sectorcode"),
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
      map("hours"),
      map("recordnumber"),
      map("dayofbirth"),
      map("monthofbirth"),
      map("yearofbirth"),
      map("address"),
      map("sex"),
      map("category"),
      map("fathername"),
      map("name"),
      map("age")

    )
  }
}


case class LogEntry(
statecode:String,
districtcode:String,
projectcode:String,
sectorcode:String,
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
hours:String,
recordnumber:String,
dayofbirth:String,
monthofbirth:String,
yearofbirth:String,
address:String,
sex:String,
category:String,
fathername:String,
name:String,
age:String)

