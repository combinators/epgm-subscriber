package org.sthapana.epgmsubscriber

import org.junit.Test

class AzureDocumentDBTest {
  @Test
  def shouldInsertRecordToAzureDB(): Unit = {
    //given
    val az = AzureDocumentDB("https://epgm.documents.azure.com:443/",
      "JzmoDSMDC7BoTPzkA0QdeEyI4WJJSGDyaSH83n8yqckxkRRjHW8U8xJbJq7ivYEXaaGNzaIzvSUQg2tRZ06xfA==",
      "epgm-db","log_data")
    val data =
      List("aanganwadicode" -> "12345678911",
        "childcode" -> "021",
        "weight" -> "065650",
        "height" -> "0169",
        "bmi" -> "0023",
        "whounderweight" -> "0",
        "iap" -> "0",
        "day" -> "15",
        "month" -> "12",
        "year" -> "16",
        "wasting" -> "7",
        "stunting" -> "0",
        "minutes" -> "46",
        "hours" -> "14")
    //when

    print(az.insert(data))

    //then
  }
}
