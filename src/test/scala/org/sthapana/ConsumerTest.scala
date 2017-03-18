package org.sthapana

import com.rabbitmq.client.{AMQP, DefaultConsumer, Envelope}
import org.junit.Test
import org.sthapana.epgmsubscriber.ChannelFactory

class ConsumerTest {
  @Test
  def subTest(): Unit ={
    //given

    val QUEUE_NAME = "epgm_logdata"
    val channel = ChannelFactory("192.168.0.108", QUEUE_NAME)
    //when
    val consumer = new DefaultConsumer(channel) {
      override def handleDelivery(consumerTag: String, envelope: Envelope, properties: AMQP.BasicProperties, body: Array[Byte]): Unit = {
        val rawData = new String(body, "UTF-8")
        println(rawData)
      }
    }

    channel.basicConsume(QUEUE_NAME, true, consumer)

    //then
  }
}
