package org.sthapana.epgmsubscriber



import com.rabbitmq.client.{AMQP, DefaultConsumer, Envelope}

object SubscriberApp {
  def main(args: Array[String]): Unit = {

      if(args.isEmpty) {
        println("Please provide IP in docker run e.g. docker run <image> 0.0.0.0")
        System.exit(0)
      }

      val QUEUE_NAME = "epgm_logdata"
      val channel = ChannelFactory(args(0), QUEUE_NAME)
      val az = AzureDocumentDB("https://epgm.documents.azure.com:443/", "0r8CYYVlo87KvsDjCipWlZtEBXWa2u2qEQWjTtd1ab0B2psDKHO6sceXsFgxKWiTZ1nUObIBknN3u2WnrWE4ig==", "thewall", "tyrion")

      val consumer = new DefaultConsumer(channel) {
        override def handleDelivery(consumerTag: String, envelope: Envelope, properties: AMQP.BasicProperties, body: Array[Byte]): Unit = {
          val rawData = new String(body, "UTF-8")
          SchemaFactory.type1.apply(rawData) match {
            case Some(log) => insertAndAggregate(log)
            case _ => print(rawData)
          }
        }


        def toWeightVal(rawWeight: String):String = (rawWeight.toDouble/1000).toString


        def updateRecord(record: Record):Record =
          record.map(x => if(x._1.equals("weight")) (x._1, toWeightVal(x._2)) else x)

        def insertAndAggregate(record: Record): Unit = {
          try {
            val updRecord = updateRecord(record)
            az.getPreviousRecord(updRecord)
          } catch {
//            case e: Exception => println("Error occured while inserting into database" + e.getMessage)
            case e: Exception => println(e)
          }
        }
      }
          channel.basicConsume(QUEUE_NAME, true, consumer)

      }


}

