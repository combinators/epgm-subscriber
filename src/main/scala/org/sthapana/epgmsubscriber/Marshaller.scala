package org.sthapana.epgmsubscriber

import org.sthapana.child.ChildRecord

object Marshaller {
  def apply(childRecord: ChildRecord, docType: String, recordNumber: String): LogEntry = {
    LogEntry(
      docType,
      childRecord.statecode,
      childRecord.districtcode,
      childRecord.projectcode,
      childRecord.sectorcode,
      childRecord.aanganwadicode,
      childRecord.childcode,
      childRecord.weight,
      childRecord.height,
      childRecord.bmi,
      childRecord.whounderweight,
      childRecord.iap,
      childRecord.day,
      childRecord.month,
      childRecord.year,
      childRecord.wasting,
      childRecord.stunting,
      childRecord.minutes,
      childRecord.hours,
      recordNumber,
      childRecord.dayofbirth,
      childRecord.monthofbirth,
      childRecord.yearofbirth,
      childRecord.address,
      childRecord.sex,
      childRecord.category,
      childRecord.fathername,
      childRecord.name,
      childRecord.age
    )
  }
}


case class LogEntry(
doctype:String,
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

