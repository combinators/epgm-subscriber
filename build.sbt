name := "epgm-subscriber"

version := "1.0"

scalaVersion := "2.11.8"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
resolvers += "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"

libraryDependencies += "com.rabbitmq" % "amqp-client" % "4.0.2"
libraryDependencies += "com.microsoft.azure" % "azure-documentdb" % "1.9.1"
libraryDependencies += "junit" % "junit" % "4.12"
libraryDependencies += "org.scalatest" % "scalatest_2.11" % "3.2.0-SNAP3"
libraryDependencies += "com.google.code.gson" % "gson" % "2.8.0"


