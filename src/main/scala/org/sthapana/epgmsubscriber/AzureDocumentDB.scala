package org.sthapana.epgmsubscriber

import com.google.gson.Gson
import com.microsoft.azure.documentdb.{ConnectionPolicy, ConsistencyLevel, Document, DocumentClient}

class AzureDocumentDB(host: String, password: String,db:String,collection:String) {
  val documentClient = new DocumentClient(host,password,ConnectionPolicy.GetDefault(),ConsistencyLevel.Session)

  def insert(record: Record):Document = documentClient.createDocument(
    "dbs/" + db + "/colls/" + collection, new Document(new Gson().toJson(Marshaller(record))) , null, false)
    .getResource()


}
object AzureDocumentDB {
  def apply(host: String, password: String,db:String,collection:String): AzureDocumentDB = new AzureDocumentDB(host,password,db,collection)
}
