
lazy val root = (project in file(".")).
  settings(
    name := "epgm-subscriber",
    version := "1.0",
    scalaVersion := "2.11.8",
    mainClass in Compile := Some("org.sthapana.epgmsubscriber.SubscriberApp")
  )



resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
resolvers += "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"


libraryDependencies += "com.rabbitmq" % "amqp-client" % "4.0.2"
libraryDependencies += "com.microsoft.azure" % "azure-documentdb" % "1.9.1"
libraryDependencies += "junit" % "junit" % "4.12"
libraryDependencies += "org.scalatest" % "scalatest_2.11" % "3.2.0-SNAP3"
libraryDependencies += "com.google.code.gson" % "gson" % "2.8.0"
libraryDependencies += "net.debasishg" %% "redisclient" % "3.3"

// META-INF discarding
assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}
