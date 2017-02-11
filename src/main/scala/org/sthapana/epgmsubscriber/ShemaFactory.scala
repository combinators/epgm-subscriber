package org.sthapana.epgmsubscriber

object SchemaFactory {
  def type1:RecordSchema = RecordSchema(List(
    "aanganwadicode" -> 11,
    "childcode" -> 3,
    "weight" -> 6,
    "height" -> 4,
    "bmi" -> 4,
    "whounderweight" -> 1,
    "iap" -> 1,
    "day" -> 2,
    "month" -> 2,
    "year" -> 2,
    "wasting" -> 1,
    "stunting" -> 1,
    "minutes" -> 2,
    "hours" -> 2
  ))
}
