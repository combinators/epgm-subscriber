package org.sthapana.epgmsubscriber


import java.time.LocalDate
import java.time.temporal.ChronoUnit


import com.google.gson.Gson
import com.microsoft.azure.documentdb._

class AzureDocumentDB(host: String, password: String,db:String,collection:String) {
  val documentClient = new DocumentClient(host, password, ConnectionPolicy.GetDefault(), ConsistencyLevel.Session)

  def insert(record: Record): Document = documentClient.createDocument(
    "dbs/" + db + "/colls/" + collection, new Document(new Gson().toJson(Marshaller(record))), null, false)
    .getResource

  def getPreviousRecord(record: Record) = {

    val recordAsMap = record.toMap
    val aanganwadiCode = recordAsMap("aanganwadicode")
    val childCode = recordAsMap("childcode")

    println("aanganwadicode is :" + aanganwadiCode)
    val results = documentClient.queryDocuments("dbs/" + db + "/colls/" + "log_data",
      //      "SELECT * FROM myCollection",
      "SELECT * FROM myCollection where myCollection.aanganwadicode=\"" + aanganwadiCode + "\" and myCollection.childcode=\"" + childCode + "\" order by myCollection.recordcount DESC",
      null).getQueryIterable().toList;

    if (results.size() == 0) {
      insertFirstRecord(record, recordAsMap)
    }
    else {
      insertNewRecord(record, results.get(0))
    }

    for (i <- 0 to results.size() - 1) {
      println("record count " + results.get(i).get("recordcount"))

    }
    println("size" + results.size())


  }

  private def insertNewRecord(record: Record, results: Document) = {
    val rcordCount: Int = Integer.parseInt(results.get("recordcount").toString)
    val currentAge=getAgeInMonths(Integer.parseInt(results.get("yearofbirth").toString),Integer.parseInt(results.get("monthofbirth").toString),Integer.parseInt(results.get("dayofbirth").toString))
    val sampleRecord=List(("age",currentAge.toString),("recordcount", (rcordCount + 1).toString),("dayofbirth", results.get("dayofbirth").toString), ("monthofbirth", results.get("monthofbirth").toString), ("yearofbirth", results.get("yearofbirth").toString), ("address", results.get("address").toString), ("sex", results.get("sex").toString), ("name", results.get("namme").toString), ("fathername", results.get("fathername").toString),("category",results.get("category").toString))

    insert(record ++ sampleRecord)



  }




  private def insertFirstRecord(record: Record, recordAsMap: Map[String, String]) = {

    def getRecordFromMasterData(): Record = {

      val rec : List[(String, String)]=List()
      try {
        val results = documentClient.queryDocuments("dbs/" + db + "/colls/" + "log_data",
                  "SELECT * FROM myCollection where myCollection.aanganwadicode=\""+recordAsMap("aanganwadicode")+"\" and myCollection.childcode=\""+recordAsMap("childcode")+"\"",
          null).getQueryIterable().toList;
        if(results.size()>0) {
          val currentDoc = results.get(0)
          val date: Array[String] = currentDoc.get("dateofbirth").toString.split("/")
          val age=getAgeInMonths(Integer.parseInt(date(2)),Integer.parseInt(date(0)),Integer.parseInt(date(1)))
          rec ++ List(("age",age.toString),("dayofbirth", date(1)), ("monthofbirth", date(0)), ("yearofbirth", date(2)), ("recordcount", "1"), ("address", currentDoc.get("address").toString),("category",currentDoc.get("category").toString) ,("sex", currentDoc.get("sex").toString), ("name", currentDoc.get("name").toString), ("fathername", currentDoc.get("fathername").toString))
        }
      } catch {
        case e: DocumentClientException => {
          println("Error occured while fetching master data for child code:"+recordAsMap("childcode").toString+" and aanganwadi:"+recordAsMap("aanganwadicode")+" = "+e.getMessage)
        }
        case e:NoSuchElementException=>{
          println("Error while searching for element:"+e.getMessage)
        }
      }
      rec
    }


    val recordFromMaster=getRecordFromMasterData()

    insert(record++recordFromMaster)





  }



  private def getAgeInMonths(year:Int,month:Int,day:Int) : Long={
    val start = LocalDate.of(year, month, day)
    val end = LocalDate.now()
    ChronoUnit.MONTHS.between(start, end)
  }


}

object AzureDocumentDB {
  def apply(host: String, password: String,db:String,collection:String): AzureDocumentDB =
    new AzureDocumentDB(host,password,db,collection)
}
