package org.sthapana.epgmsubscriber

import com.rabbitmq.client.{AMQP, DefaultConsumer, Envelope}

object SubscriberApp {
  def main(args: Array[String]): Unit = {

    val QUEUE_NAME = "hello"
    val channel = Channel("localhost",QUEUE_NAME)
    val az = AzureDocumentDB("https://epgm.documents.azure.com:443/",
      "JzmoDSMDC7BoTPzkA0QdeEyI4WJJSGDyaSH83n8yqckxkRRjHW8U8xJbJq7ivYEXaaGNzaIzvSUQg2tRZ06xfA==",
      "epgm-db","log_data")

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

    val consumer = new DefaultConsumer(channel) {
      override def handleDelivery(consumerTag:String, envelope:Envelope, properties:AMQP.BasicProperties, body:Array[Byte]) = {
        val rawData = new String(body, "UTF-8")
        az.insert(schema.apply(rawData).get)
      }
    }

    channel.basicConsume(QUEUE_NAME, true, consumer);
  }
}
