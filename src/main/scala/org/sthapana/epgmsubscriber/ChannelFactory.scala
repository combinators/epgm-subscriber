package org.sthapana.epgmsubscriber

import com.rabbitmq.client.{Channel, ConnectionFactory}

object ChannelFactory {
  def apply(host: String, queueName: String): Channel = {
    val factory = new ConnectionFactory()
    factory.setUsername("epgm")
    factory.setPassword("epgm@monitor")
    factory.setHost(host)
    val connection = factory.newConnection()
    val channel = connection.createChannel()
    channel.queueDeclare(queueName, true, false, false, null)
    channel
  }
}
