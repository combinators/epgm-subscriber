package org.sthapana.aggregation.engine

import com.redis._
import serialization._
import com.redis.serialization.Parse.Implicits._

/**
  * Created by chocoklate on 14/2/17.
  */
object TestingRedis {

  def main(args: Array[String]): Unit = {

    val rc = new RedisClient("localhost",6379)

    rc.hmset("gradewise:27",Map("suwcount" -> "20","muwcount" -> "20","normalcount" -> "60", "totalcount" -> "100"))
    rc.hmset("gradewise:27001",Map("suwcount" -> "2","muwcount" -> "2","normalcount" -> "6", "totalcount" -> "10"))
    rc.hmset("gradewise:52",Map("suwcount" -> "15","muwcount" -> "35","normalcount" -> "30", "totalcount" -> "80"))
    rc.hmset("gradewise:52001",Map("suwcount" -> "1","muwcount" -> "3","normalcount" -> "3", "totalcount" -> "7"))
    //    rc.del("gradewise:27")
    //    rc.del("gradewise:52001")
    println(rc.hmget[String,String]("gradewise:27", "suwcount","muwcount", "normalcount","totalcount").toString)
    println(rc.hmget[String,String]("gradewise:52", "suwcount","muwcount", "normalcount","totalcount").toString)
    println(rc.hmget[String,String]("gradewise:27001", "suwcount","muwcount", "normalcount","totalcount").toString)
    println(rc.hmget[String,String]("gradewise:52001", "suwcount","muwcount", "normalcount","totalcount").toString)

  }

}

git checkout .idea/libraries/SBT__com_fasterxml_jackson_core_jackson_annotations_2_8_0_jar.xml
git checkout .idea/libraries/SBT__com_fasterxml_jackson_core_jackson_core_2_8_3_jar.xml
git checkout .idea/libraries/SBT__com_fasterxml_jackson_core_jackson_databind_2_8_3_jar.xml
git checkout .idea/libraries/SBT__com_google_code_gson_gson_2_8_0_jar.xml
git checkout .idea/libraries/SBT__com_microsoft_azure_azure_documentdb_1_9_1_jar.xml
git checkout .idea/libraries/SBT__com_rabbitmq_amqp_client_4_0_2_jar.xml
git checkout .idea/libraries/SBT__commons_codec_commons_codec_1_9_jar.xml
git checkout .idea/libraries/SBT__commons_logging_commons_logging_1_2_jar.xml
git checkout .idea/libraries/SBT__junit_junit_4_12_jar.xml
git checkout .idea/libraries/SBT__org_apache_commons_commons_lang3_3_3_2_jar.xml
git checkout .idea/libraries/SBT__org_apache_httpcomponents_httpclient_4_5_2_jar.xml
git checkout .idea/libraries/SBT__org_apache_httpcomponents_httpcore_4_4_3_jar.xml
git checkout .idea/libraries/SBT__org_hamcrest_hamcrest_core_1_3_jar.xml
git checkout .idea/libraries/SBT__org_json_json_20140107_jar.xml
git checkout .idea/libraries/SBT__org_scala_lang_modules_scala_parser_combinators_2_11_1_0_4_jar.xml
git checkout .idea/libraries/SBT__org_scala_lang_modules_scala_xml_2_11_1_0_5_jar.xml
git checkout .idea/libraries/SBT__org_scala_lang_scala_library_2_11_8_jar.xml
git checkout .idea/libraries/SBT__org_scala_lang_scala_reflect_2_11_8_jar.xml
git checkout .idea/libraries/SBT__org_scalactic_scalactic_2_11_3_2_0_SNAP3_jar.xml
git checkout .idea/libraries/SBT__org_scalatest_scalatest_2_11_3_2_0_SNAP3_jar.xml
git checkout .idea/libraries/SBT__org_slf4j_slf4j_api_1_7_21_jar.xml
new file:   src/main/scala/org/sthapana/aggregation/engine/ProcessingEngine.scala


Changes not staged for commit:
(use "git add <file>..." to update what will be committed)
(use "git checkout -- <file>..." to discard changes in working directory)

git add build.sbt
git add src/main/scala/org/sthapana/aggregation/engine/ProcessingEngine.scala

Untracked files:
(use "git add <file>..." to include in what will be committed)

.idea/libraries/SBT__com_typesafe_akka_akka_actor_2_11_2_3_6_jar.xml
.idea/libraries/SBT__com_typesafe_config_1_2_1_jar.xml
.idea/libraries/SBT__commons_pool_commons_pool_1_6_jar.xml
.idea/libraries/SBT__net_debasishg_redisclient_2_11_3_3_jar.xml
.idea/misc.xml
.idea/modules.xml
.idea/modules/
.idea/sbt.xml
.idea/scala_compiler.xml
.idea/uiDesigner.xml
.idea/vcs.xml
.idea/workspace.xml
project/project/
project/target/
git add src/main/scala/org/sthapana/aggregation/dataobjects/
git add src/main/scala/org/sthapana/aggregation/engine/RedisConnector.scala
git add src/main/scala/org/sthapana/aggregation/engine/TestingRedis.scala
git add src/test/scala/org/sthapana/aggregation/
target/

