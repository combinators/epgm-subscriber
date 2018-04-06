package org.sthapana.epgmsubscriber

object SchemaFactory {
  def type1:RecordSchema = RecordSchema(List(
    "statecode"->2,
    "districtcode"->3,
    "projectcode"->2,
    "sectorcode"->2,
    "aanganwadicode" -> 2,
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

//27 511 01 05 07 005 059200 0157 0024 7 7 23 03 18 7 7 52 13

//0020592000157002417030418775213
//005 003640 0050001477040418774717
//003 012530 0111 0010 1 0 05 04 18 0 0 25 13
