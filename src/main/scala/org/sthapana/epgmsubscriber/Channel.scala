package org.sthapana.epgmsubscriber

import com.rabbitmq.client.{Channel, ConnectionFactory}

object Channel {
  def apply(host: String, queueName: String): Channel = {
    val factory = new ConnectionFactory()
    factory.setHost(host)
    val connection = factory.newConnection()
    val channel = connection.createChannel()
    channel.queueDeclare(queueName, false, false, false, null)
    channel
  }
}
