package org.sthapana.epgmsubscriber

import org.junit.Test
import org.scalatest.Matchers._

class RecordSchemaTest {

  @Test
  def shouldExtractRecordApplyingTheGivenSchemaNew(): Unit = {
    //given
    val sampleRecord = "123456789110210656500169002300151216704614"
    val schema = SchemaFactory.type1

    //when
    val record: Record = schema.apply(sampleRecord).get

    //then
    List(
      "statecode" -> "12",
      "districtcode" -> "12345",
      "projectcode" -> "1234567",
      "sectorcode" -> "123456789",
      "aanganwadicode" -> "12345678911",
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
      "hours" -> "14").toMap should contain theSameElementsAs (record)
  }

}









