package org.sthapana.aggregation.engine

import com.redis.RedisClient
import org.junit.Test
import org.sthapana.aggregation.dataobjects.UpdateEntity

/**
  * Created by chocoklate on 14/2/17.
  */
class ProcessingEngineTest {

  @Test
  def itShouldUpdateValues() = {
    //given
    val updateEntity = UpdateEntity("27","27001","2700102","270010203","27001020304","0","-1","M","12","11","2","1")
    //when
    new ProcessingEngine().updateDB(updateEntity)
    //then
    val rc = new RedisClient("localhost",6379)
    println("---------->then : "+rc.hmget[String,String]("gradewise:27", "suwcount","muwcount", "normalcount","totalcount").toString)
  }

}
