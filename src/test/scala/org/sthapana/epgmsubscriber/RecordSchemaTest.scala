package org.sthapana.epgmsubscriber

import org.junit.Assert._
import org.junit.Test
import org.scalatest.Matchers._

class RecordSchemaTest {

  @Test
  def shouldValidateRecordStringWithGivenSchema(): Unit = {
    //given
    val sampleRecord = "123456789110210656500169002300151216704614"
    val schema = RecordSchema(List(
      "anganwadicode" -> 11,
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
      "min" -> 2,
      "hour" -> 2
    ))

    //when

    //then
    assertTrue(schema.test(sampleRecord))
  }

  @Test
  def shouldExtractRecordApplyingTheGivenSchema(): Unit = {
    //given
    val sampleRecord = "123456789110210656500169002300151216704614"
    val schema = RecordSchema(List(
      "anganwadicode" -> 11,
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
      "min" -> 2,
      "hour" -> 2
    ))

    //when
    val record:Record = schema.apply(sampleRecord).get

    //then
    List("anganwadicode" -> "12345678911",
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
      "min" -> "46",
      "hour" -> "14") should contain theSameElementsAs(record)
  }

}
