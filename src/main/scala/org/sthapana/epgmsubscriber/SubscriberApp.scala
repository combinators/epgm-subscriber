package org.sthapana.epgmsubscriber

import com.rabbitmq.client.{AMQP, DefaultConsumer, Envelope}

object SubscriberApp {
  def main(args: Array[String]): Unit = {

    val QUEUE_NAME = "hello"
    val channel = ChannelFactory("localhost",QUEUE_NAME)
    val az = AzureDocumentDB("", "", "","")

    val consumer = new DefaultConsumer(channel) {
      override def handleDelivery(consumerTag:String, envelope:Envelope, properties:AMQP.BasicProperties, body:Array[Byte]): Unit = {
        val rawData = new String(body, "UTF-8")
        SchemaFactory.type1.apply(rawData) match {
          case Some(log) => insert(log)
          case _ => print(rawData)
        }
      }

      def insert(record: Record): Unit = {
        try{
          az.insert(record)
        }catch {
          case e: Exception => e.printStackTrace()
        }

      }
    }

    channel.basicConsume(QUEUE_NAME, true, consumer)
  }
}
