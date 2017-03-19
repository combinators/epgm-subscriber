package com.ikaee.convertors

import com.microsoft.azure.documentdb.Document
import org.sthapana.child.ChildRecord
import org.sthapana.epgmsubscriber.Record

object conversions {

  implicit def mapToChildRecord(record: Record): ChildRecord = {
    ChildRecord(
      record("statecode"),
      record("districtcode"),
      record("projectcode"),
      record("sectorcode"),
      record("aanganwadicode"),
      record("childcode"),
      record("weight"),
      record("height"),
      record("bmi"),
      record("whounderweight"),
      record("iap"),
      record("day"),
      record("month"),
      record("year"),
      record("wasting"),
      record("stunting"),
      record("minutes"),
      record("hours"),
      record("dayofbirth"),
      record("monthofbirth"),
      record("yearofbirth"),
      record("address"),
      record("sex"),
      record("category"),
      record("fathername"),
      record("name"),
      record("age"))
  }

  implicit def docToChildRecord(doc: Document): ChildRecord = {
    ChildRecord(
      doc.get("statecode").toString,
      doc.get("districtcode").toString,
      doc.get("projectcode").toString,
      doc.get("sectorcode").toString,
      doc.get("aanganwadicode").toString,
      doc.get("childcode").toString,
      doc.get("weight").toString,
      doc.get("height").toString,
      doc.get("bmi").toString,
      doc.get("whounderweight").toString,
      doc.get("iap").toString,
      doc.get("day").toString,
      doc.get("month").toString,
      doc.get("year").toString,
      doc.get("wasting").toString,
      doc.get("stunting").toString,
      doc.get("minutes").toString,
      doc.get("hours").toString,
      doc.get("dayofbirth").toString,
      doc.get("monthofbirth").toString,
      doc.get("yearofbirth").toString,
      doc.get("address").toString,
      doc.get("sex").toString,
      doc.get("category").toString,
      doc.get("fathername").toString,
      doc.get("name").toString,
      doc.get("age").toString
    )
  }

  implicit def childRecordToMap(childRecord: ChildRecord): Record = {
    Map(
    "statecode" -> childRecord.statecode,
      "districtcode" -> childRecord.districtcode,
      "projectcode" -> childRecord.projectcode,
      "sectorcode" -> childRecord.sectorcode,
      "aanganwadicode" -> childRecord.aanganwadicode,
      "childcode" -> childRecord.childcode,
      "weight" -> childRecord.weight,
      "height" -> childRecord.height,
      "bmi" -> childRecord.bmi,
      "whounderweight" -> childRecord.whounderweight,
      "iap" -> childRecord.iap,
      "day" -> childRecord.day,
      "month" -> childRecord.month,
      "year" -> childRecord.year,
      "wasting" -> childRecord.wasting,
      "stunting" -> childRecord.stunting,
      "minutes" -> childRecord.minutes,
      "hours" -> childRecord.hours,
      "dayofbirth" -> childRecord.dayofbirth,
      "monthofbirth" -> childRecord.monthofbirth,
      "yearofbirth" -> childRecord.yearofbirth,
      "address" -> childRecord.address,
      "sex" -> childRecord.sex,
      "category" -> childRecord.category,
      "fathername" -> childRecord.fathername,
      "name" -> childRecord.name,
      "age" -> childRecord.age
    )
  }

}