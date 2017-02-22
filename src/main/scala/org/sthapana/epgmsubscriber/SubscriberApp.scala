package org.sthapana.epgmsubscriber



import com.rabbitmq.client.{AMQP, DefaultConsumer, Envelope}

object SubscriberApp {
  def main(args: Array[String]): Unit = {

      val QUEUE_NAME = "epgm_logdata"
      val channel = ChannelFactory("localhost", QUEUE_NAME)
      val az = AzureDocumentDB("https://epgm.documents.azure.com:443/", "SlhyMCNEuU55HklqqibVpNAqi58tN5ZcBjYznR2SLUxNOsjNaEH7JT3kLsaB6K9mRFMtTrl10bx3oJYm9DfsAA==", "thewall", "tyrion")

//    val record=SchemaFactory.type1.apply("275090101010340656500169002300150117704614").get
//    az.getPreviousRecord(record)


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

        //        def insert(record: Record): Unit = {
        //          try {
        //            az.insert(record)
        //          } catch {
        //            case e: Exception => e.printStackTrace()
        //          }
        //
        //        }

      }
          channel.basicConsume(QUEUE_NAME, true, consumer)

      }


}

