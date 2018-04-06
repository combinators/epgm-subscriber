package org.sthapana.epgmsubscriber



import com.rabbitmq.client.{AMQP, DefaultConsumer, Envelope}
import org.sthapana._
import java.util.regex.{Matcher, Pattern}

object SubscriberApp {
  def main(args: Array[String]): Unit = {

      if(args.isEmpty) {
        println("Please provide IP in docker run e.g. docker run <image> 0.0.0.0")
        System.exit(0)
      }
      val channel = ChannelFactory(args(0), queueName)
      val az = AzureDocumentDB(dbHost, dbPassword, dbName, collectionName)

      val consumer = new DefaultConsumer(channel) {
        override def handleDelivery(consumerTag: String, envelope: Envelope, properties: AMQP.BasicProperties, body: Array[Byte]): Unit = {
          val rawData = removeEscapeCharacters(new String(body, "UTF-8"))
          SchemaFactory.type1.apply(rawData) match {
            case Some(log) => insertAndAggregate(log)
            case _ => println("Raw FTP data schema validation failed:  ",rawData)
          }
        }

        def removeEscapeCharacters(rawData: String): String = {
          val m = Pattern.compile("[0-9]+").matcher(rawData)
          if(m.find()) m.group() else rawData

        }


        def insertAndAggregate(record: Record): Unit = {
          try {
            az.insertRecordInDatabase(record)
          } catch {
            case e: Exception => {
              println("Error occured while inserting into database "+ e)
              e.printStackTrace()
            }
          }
        }
      }
          channel.basicConsume(queueName, true, consumer)

      }


}

