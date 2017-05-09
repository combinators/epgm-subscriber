package org.sthapana.datainsert.masterdata

import com.google.gson.Gson
import com.microsoft.azure.documentdb.{ConnectionPolicy, ConsistencyLevel, Document, DocumentClient}
import org.sthapana.aggregation.dataobjects._
import org.sthapana._

/**
  * Created by kirankumarbs on 21/2/17.
  */

object DataInsert{

  val documentClient = new DocumentClient(dbHost,
    dbPassword, ConnectionPolicy.GetDefault(),
    ConsistencyLevel.Session)

  def insertMasterChildData(masterChildEntity: MasterChildDataEntity): Unit = {
    val entityJson = new Gson().toJson(masterChildEntity)
    val entityDocument = new Document(entityJson)
    val dResource = documentClient.createDocument(s"dbs/$dbName/colls/$collectionName",
      entityDocument, null,false).getResource()
    println(dResource)
  }

    def insertMasterAanganwadiData(masterAanganwadiEntity: MasterAanganwadiDataEntity): Unit = {
        val entityJson = new Gson().toJson(masterAanganwadiEntity)
        val entityDocument = new Document(entityJson)
        val dResource = documentClient.createDocument(s"dbs/$dbName/colls/$collectionName",
            entityDocument, null,false).getResource()
        println(dResource)
    }

    def insertMasterSectorData(masterSectorEntity: MasterSectorDataEntity): Unit = {
        val entityJson = new Gson().toJson(masterSectorEntity)
        val entityDocument = new Document(entityJson)
        val dResource = documentClient.createDocument(s"dbs/$dbName/colls/$collectionName",
            entityDocument, null,false).getResource()
        println(dResource)
    }

    def insertMasterProjectData(masterProjectEntity: MasterProjectDataEntity): Unit = {
        val entityJson = new Gson().toJson(masterProjectEntity)
        val entityDocument = new Document(entityJson)
        val dResource = documentClient.createDocument(s"dbs/$dbName/colls/$collectionName",
            entityDocument, null,false).getResource()
        println(dResource)
    }

    def insertMasterDistrictData(masterDistrictEntity: MasterDistrictDataEntity): Unit = {
        val entityJson = new Gson().toJson(masterDistrictEntity)
        val entityDocument = new Document(entityJson)
        val dResource = documentClient.createDocument(s"dbs/$dbName/colls/$collectionName",
            entityDocument, null,false).getResource()
        println(dResource)
    }

    def insertMasterStateData(masterStateEntity: MasterStateDataEntity): Unit = {
        val entityJson = new Gson().toJson(masterStateEntity)
        val entityDocument = new Document(entityJson)
        val dResource = documentClient.createDocument(s"dbs/$dbName/colls/$collectionName",
            entityDocument, null,false).getResource()
        println(dResource)
    }
}

object MasterAanganwadiInsert extends App {

    import DataInsert._
    insertMasterAanganwadiData(MasterAanganwadiDataEntity("aanganwadi","27511010507","PIMPALGAO 2"))
    insertMasterAanganwadiData(MasterAanganwadiDataEntity("aanganwadi","27511010509","PIMPALGAO 4"))
    insertMasterAanganwadiData(MasterAanganwadiDataEntity("aanganwadi","27511010209","YELEGOAN (1)"))
    insertMasterAanganwadiData(MasterAanganwadiDataEntity("aanganwadi","27511010211","YELEGOAN (3)"))
    insertMasterAanganwadiData(MasterAanganwadiDataEntity("aanganwadi","27511010406","KONDHA 3"))
    insertMasterAanganwadiData(MasterAanganwadiDataEntity("aanganwadi","27511010407","KONDHA 4"))
    insertMasterAanganwadiData(MasterAanganwadiDataEntity("aanganwadi","27511010109","LAHAN 4"))
    insertMasterAanganwadiData(MasterAanganwadiDataEntity("aanganwadi","27511010111","LAHAN 6"))
    insertMasterAanganwadiData(MasterAanganwadiDataEntity("aanganwadi","27511010305","MALEGAON-3"))
    insertMasterAanganwadiData(MasterAanganwadiDataEntity("aanganwadi","27511010306","MALEGAON-4"))

}

object MasterDataInsert extends App {


    import DataInsert._
    insertMasterChildData(MasterChildDataEntity("child","05","05","2009","","M","0","001","","MAHESH","M KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","04","06","2009","","M","0","002","","VITTAL","D KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","30","06","2009","","F","0","003","","RAGINI","D KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","01","08","2009","","F","0","004","","JANHAVI","K KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","04","08","2009","","F","0","005","","SANIKA","D KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","16","08","2009","","F","0","006","","SAKSHI","S KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","22","08","2009","","M","0","007","","OMPRAKASH","D KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","29","09","2009","","F","0","008","","SHRUSHTI","G KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","24","09","2009","","M","0","009","","SHANKAR","M KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","07","10","2009","","M","0","010","","ARYAN","D KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","20","10","2009","","M","0","011","","MAYUR","M KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","29","11","2009","","M","0","012","","DIGVIJAY","S KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","25","12","2009","","M","0","013","","KANHAIYA","B KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","03","01","2010","","F","0","014","","PRITI","G KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","08","02","2010","","M","0","015","","MAHESH","S KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","21","02","2010","","M","0","016","","VITTAL","A KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","09","03","2010","","F","0","017","","JANHAVI","N KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","03","08","2010","","M","0","018","","MADHAV","G KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","13","08","2010","","M","0","019","","VALLABH","S KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","08","09","2010","","M","0","020","","SANSKAR","S KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","06","10","2010","","M","0","021","","SUMIT","P KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","09","10","2010","","M","0","022","","SHUBHAM","K KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","23","12","2010","","F","0","023","","GAYATRI","P KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","30","12","2010","","F","0","024","","SAKSHI","G KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","12","01","2011","","M","0","025","","VINIT","S KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","28","02","2011","","M","0","026","","KANAN","K KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","30","04","2011","","M","0","027","","SRIPAD","D KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","11","05","2011","","M","0","028","","SAMARTH","S KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","13","05","2011","","M","0","029","","KRISHNA","R KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","30","06","2011","","M","0","030","","PRATHMESH","P KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","12","08","2011","","M","0","031","","RUPESH","R KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","12","08","2011","","F","0","032","","RAKHI","R KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","22","08","2011","","M","0","033","","VEDANT","G KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","23","08","2011","","F","0","034","","SANIKA","S KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","25","09","2011","","M","0","035","","KISHAN","B KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","03","10","2011","","M","0","036","","TUKARAM","D KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","10","10","2011","","M","0","037","","RUDRAKSH","D KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","01","05","2011","","M","0","038","","MOHAN","S KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","11","11","2011","","M","0","039","","VAIBHAV","D KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","20","01","2012","","M","0","040","","PRATHVIRAJ","R KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","02","02","2012","","M","0","041","","ATHARV","M KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","05","02","2012","","F","0","042","","PURVI","S KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","10","03","2012","","M","0","043","","LAXMIKANT","M KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","06","04","2012","","F","0","044","","BHAKTI","C PATANGE","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","14","05","2012","","F","0","045","","ARADHYA","R KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","25","07","2012","","F","0","046","","POOJA","S KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","11","08","2012","","F","0","047","","KISHOR","D KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","24","08","2012","","M","0","048","","OMKAR","S KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","12","10","2012","","M","0","049","","ISHWAR","G KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","30","10","2012","","F","0","050","","SAMRUDDHI","V KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","11","12","2012","","F","0","051","","RADHIKA","R KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","26","01","2013","","M","0","052","","KRISHNA","R KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","04","03","2013","","M","0","053","","UMESH","M KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","03","05","2013","","F","0","054","","ARADHYA","T KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","08","05","2013","","M","0","055","","SANJAY","D KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","01","07","2013","","F","0","056","","RAGINI","R KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","24","04","2013","","F","0","057","","KIMAYA","S KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","28","09","2013","","F","0","058","","KALYANI","K KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","21","09","2013","","M","0","059","","DISHANT","S KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","03","11","2013","","F","0","060","","SHREYA","S KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","20","11","2013","","F","0","061","","MONIKA","K KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","26","11","2013","","M","0","062","","SOMNATH","S KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","08","01","2014","","M","0","063","","MAHESH","G KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","10","03","2014","","M","0","064","","OMKAR","R KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","18","03","2014","","M","0","065","","YASHRAJ","S KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","03","04","2014","","M","0","066","","SHEKHAR","A KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","05","05","2014","","F","0","067","","ARATI","S KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","23","05","2014","","F","0","068","","SHREYA","V KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","23","05","2014","","M","0","069","","GANRAJ","R KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","24","05","2014","","F","0","070","","SHREYA","V KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","18","06","2014","","M","0","071","","SARTHAK","V KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","10","07","2014","","M","0","072","","VIVEKANAND","S KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","19","08","2014","","F","0","073","","ANJALI","S KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","11","09","2014","","F","0","074","","TANHAMA","P KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","07","09","2014","","M","0","075","","RUDRA ","B KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","05","10","2014","","M","0","076","","SHREYASH","V KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","16","10","2014","","F","0","077","","SHEJAL","L KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","19","10","2014","","F","0","078","","AKSHRA","K KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","21","01","2015","","F","0","079","","SONU","G KALYANKAR","27511010507"))

    insertMasterChildData(MasterChildDataEntity("child","09","06","2009","","F","0","001","","GAURI","N KALYANKAR","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","15","07","2009","","M","0","002","","SHUBHAM","B KALYANKAR","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","13","09","2009","","M","0","003","","KRISHNA","J WAHULKAR","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","21","08","2009","","M","0","004","","SAMBHAJI","M KALYANKAR","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","21","09","2009","","M","0","005","","SHIVRAJ","N BAMBARULE","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","26","09","2009","","M","0","006","","SHIVRAJ","M BODAKE","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","31","10","2009","","F","0","007","","KAJAL","U DUDHMAL","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","19","11","2009","","M","0","008","","MAROTI","N WAGHMARE","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","30","01","2010","","F","0","009","","GAURI","V IBITWAR","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","22","12","2009","","F","0","010","","SHIVKANYA","P BODAGE","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","02","02","2010","","M","0","011","","BAJIRAO","G SHIRADE","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","28","02","2010","","F","0","012","","AKSHRA","A BODAKE","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","19","04","2010","","M","0","013","","DIPAK","N SAWANT","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","01","05","2010","","F","0","014","","SAYALI","S KALYANKAR","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","16","07","2010","","F","0","015","","SAKSHI","S DUDHMAL","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","14","08","2010","","M","0","016","","AYUSH","D WAGMARE","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","10","10","2010","","M","0","017","","HARSHDIP","H DUDHMAL","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","06","11","2010","","M","0","018","","KUNAL","G BAMBARULE","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","02","03","2011","","F","0","019","","GAYATRI","M HIWARE","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","11","07","2011","","F","0","020","","SANA","I ATAR","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","22","09","2011","","M","0","021","","SHIVAM","B JIKULE","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","25","09","2011","","F","0","022","","KALYANI","G ROMAM","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","29","09","2011","","F","0","023","","DURGA","K BAMBARULE","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","21","10","2011","","F","0","024","","RUTUJA","N BAMBARULE","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","08","01","2012","","M","0","025","","SANGHARSH","A DUDHMAL","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","16","01","2012","","F","0","026","","DIPIKA","N SAWANE","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","21","01","2012","","M","0","027","","ARYAN","K  DUDHMAL","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","25","05","2012","","M","0","028","","AMIT","U DUDHMAL","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","23","05","2012","","F","0","029","","VAISHNAVI","S KHANDAGALE","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","02","08","2012","","M","0","030","","ADITYA","U DUDHMAL","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","08","10","2012","","M","0","031","","RITESH","J DUDHMAL","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","15","10","2012","","M","0","032","","ARJUN","B TUDAME","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","19","10","2012","","M","0","033","","SHIVAM","N KALYANKAR","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","19","10","2012","","F","0","034","","KHUSHI","N KALYANKAR","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","22","11","2012","","M","0","035","","VIVEK","S DUDHMAL","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","23","11","2012","","F","0","036","","SANGITA","T PALASE","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","25","11","2012","","F","0","037","","SHWETA","M HIWARE","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","14","01","2013","","M","0","038","","SANJAY","G SHIRADE","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","07","04","2013","","F","0","039","","HINDAVI","S ROMAN","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","15","04","2013","","M","0","040","","KUNAL","G KHANDAGALE","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","26","04","2013","","F","0","041","","POONAM","V KALYANKAR","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","21","05","2013","","M","0","042","","RUDRA","V IBITWAR","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","12","06","2013","","M","0","043","","NAITIK","S KALYANKAR","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","02","07","2013","","F","0","044","","PAYAL","G K ALYANKAR","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","21","07","2013","","F","0","045","","VAISHNAVI","R KALYANKAR","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","22","07","2013","","F","0","046","","VAIBHAVI","S GAJALWAD","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","29","07","2013","","M","0","047","","PRANIT","P PIMPALE","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","29","07","2013","","F","0","048","","GAURI","K BAMBARULE","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","27","09","2013","","M","0","049","","HUJEFH","F MANIYAR","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","07","11","2013","","M","0","050","","YASHODIP","H DUDHMAL","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","04","12","2013","","M","0","051","","SAINATH","J BAMBARULE","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","29","12","2013","","M","0","052","","NIRAJ","G BAMBARULE","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","22","01","2014","","M","0","053","","OMKAR","G ROMAN","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","10","05","2014","","M","0","054","","BHAGAWAT","N DIDHMAL","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","27","08","2014","","F","0","055","","ANJALI","B DUDHMAL","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","15","09","2014","","F","0","056","","RUPALI"," S DUDHMAL","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","16","09","2014","","F","0","057","","SNEHAL","A DUDHMAL","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","11","11","2014","","M","0","058","","PUNIT","V  KALYANKAR","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","20","11","2014","","M","0","059","","KAUSTUBH","U DUDHMAL","27511010509"))

    insertMasterChildData(MasterChildDataEntity("child","11","06","2009","","M","0","001","","SANGHARSH","D KAPATE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","19","07","2009","","M","0","002","","HRUSHIKESH","R KAPATE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","30","10","2009","","M","0","003","","KRUSHNA","S JADHAV","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","21","12","2009","","F","0","004","","MADHURA","R KAPATE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","26","03","2010","","F","0","005","","VEDIKA","S KAPATE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","25","04","2010","","F","0","006","","GAYATRI","G KAPATE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","25","05","2010","","F","0","007","","SHIVPRAPTI","B GODRE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","04","06","2010","","M","0","008","","GANGADHAR","S BARSE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","19","07","2010","","M","0","009","","BALAJI","R KAPATE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","28","07","2010","","M","0","010","","SHRIGANESH","M KAPATE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","01","08","2010","","F","0","011","","SAMIKSHA","S KAPATE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","14","08","2010","","M","0","012","","VYANKATESH","D KAPATE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","30","09","2010","","M","0","013","","BRAMHAJI","V KAPATE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","05","11","2010","","M","0","014","","PRATHMESH","P KAPATE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","13","12","2010","","M","0","015","","NAVNATH","B KAPATE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","16","02","2011","","M","0","016","","PRATHMESH","P KAPATE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","24","02","2011","","M","0","017","","SAMARTH","R KAPATE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","13","03","2011","","F","0","018","","TEJASWINI","P BARSE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","28","03","2011","","M","0","019","","GANESH","G KAPATE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","09","04","2011","","F","0","020","","PRATIKSHA","B BARSE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","20","06","2011","","F","0","021","","AKSHARA","G BARSE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","21","06","2011","","F","0","022","","MAYURI","M KAPATE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","03","08","2011","","M","0","023","","PREM","C KAPATE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","05","08","2011","","F","0","024","","PRITI","M KAPATE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","26","10","2011","","F","0","025","","GOURI","B KAPATE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","03","10","2011","","F","0","026","","VAISHNAVI","R BARSE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","14","11","2011","","F","0","027","","YOGESHWARI","R KAPATE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","12","11","2011","","M","0","028","","SATISH","K KAPATE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","07","02","2012","","M","0","029","","KRUSHNA","D KAPATE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","26","02","2012","","F","0","030","","PRITI","P KAPATE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","02","03","2012","","M","0","031","","KARAN","S JADHAV","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","17","09","2009","","M","0","032","","SHUBHAM","B KAPATE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","23","10","2009","","M","0","033","","PRATHMESH","U KAPATE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","17","10","2011","","F","0","034","","SHRADHA","G JADHAV","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","17","05","2012","","F","0","035","","SHRIKANT","M KAPATE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","22","07","2012","","F","0","036","","KHUSHI","V JADHAV","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","20","09","2012","","M","0","037","","GAJANAN","B KAPATE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","14","03","2012","","F","0","038","","DURGA","B SOLANKE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","23","09","2012","","M","0","039","","RUPESH","P BARSE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","16","10","2012","","M","0","040","","GAURAV","G KAPATE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","17","10","2010","","F","0","041","","PRASIDHI","M KAPATE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","28","10","2012","","F","0","042","","PUNAM","G JADHAV","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","31","10","2012","","F","0","043","","RUTUJA","S BARSE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","15","11","2012","","M","0","044","","DASHRATH","N KAPATE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","16","11","2012","","M","0","045","","INDHRAJEET","D KAPATE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","20","12","2012","","F","0","046","","PRATIKSHA","P KAPATE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","28","12","2012","","M","0","047","","VYANKATESH","S KAPATE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","11","01","2013","","M","0","048","","MANMATH","B GODRE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","19","02","2013","","M","0","049","","VISHNUKANT","G KAPATE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","05","03","2013","","F","0","050","","VEDIKA","D KAPATE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","02","05","2013","","M","0","051","","PRAMOD","C KAPATE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","16","05","2013","","M","0","052","","PRUTHVIRAJ","M KAPATE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","13","06","2013","","M","0","053","","RUDRA","G KAPATE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","13","07","2013","","F","0","054","","VARSHA","P BARSE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","28","07","2013","","F","0","055","","ARTI","M KAPATE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","03","07","2013","","F","0","056","","VAIBHAVI","R BARSE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","29","09","2013","","F","0","057","","PRITI","G BARSE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","08","10","2013","","M","0","058","","SHIVRAJ","D JADHAV","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","01","01","2014","","F","0","059","","RIDHI","M KAPATE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","01","01","2014","","F","0","060","","SIDHI","M KAPATE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","17","03","2014","","M","0","061","","SAIRAJ","G JADHAV","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","12","04","2014","","F","0","062","","SANKRUTI  ","R KAPATE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","01","04","2014","","F","0","063","","SAMIKSHA","R KAPATE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","12","05","2014","","M","0","064","","KUNAL","A KAPATE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","02","08","2014","","M","0","065","","DHANANJAY","N KAPATE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","07","08","2014","","F","0","066","","YOGITA","B BARSE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","07","08","2014","","M","0","067","","VISHWAJEET","B JADHAV","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","07","08","2014","","F","0","068","","BHAGYALAXMI","B KAPATE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","30","11","2014","","M","0","069","","OMKAR","B KAPATE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","11","11","2014","","F","0","070","","VAISHNAVI","G KAPATE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","31","12","2014","","M","0","071","","BANTY","P BARSE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","01","02","2015","","M","0","072","","GANGADHAR","B JADHAV","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","07","08","2014","","F","0","073","","YOGITA","K KAPATE","27511010209"))

    insertMasterChildData(MasterChildDataEntity("child","17","07","2009","","M","0","001","","VAIBHAV","V RATHOD","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","15","07","2009","","F","0","002","","GAYATRI","P KAPATE","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","19","09","2009","","M","0","003","","PREM","S GODBOLE","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","19","10","2009","","M","0","004","","ROHAN","B SAWTE","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","06","02","2010","","M","0","005","","SAINATH","V BARSHE","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","30","03","2010","","F","0","006","","SHITAL","B SAWTE","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","30","05","2010","","M","0","007","","VEDANT","V RAJWADE","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","06","02","2010","","F","0","008","","VANITA","B BHAYMORE","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","25","05","2010","","F","0","009","","ROSHNI","R SAWTE","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","12","06","2010","","F","0","010","","ASHWINI","J SAWTE","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","28","07","2010","","F","0","011","","RADHIKA","R SAWTE","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","02","09","2010","","M","0","012","","KRUSHNA","A TANDULKAR","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","30","09","2010","","M","0","013","","VAIBHAV","S SAWTE","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","15","06","2010","","F","0","014","","ASHWINI","D KHANDAGALE","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","26","09","2010","","M","0","015","","YOGESH","A PACHANGE","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","01","08","2010","","F","0","016","","RATNESHWARI","S SAWTE","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","08","09","2010","","M","0","017","","SUBHAM","P SAWTE","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","24","10","2010","","M","0","018","","GANESH","D KHADAGALE","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","07","12","2010","","M","0","019","","RAJRATNA","S SAWTE","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","16","01","2011","","F","0","020","","RINKU","R SAWTE","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","15","02","2011","","F","0","021","","KAJAL","R SAWTE","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","26","07","2011","","F","0","022","","SAPNA","S SAWTE","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","21","08","2011","","F","0","023","","GOKARNA","P GAVHANE","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","25","08","2011","","F","0","024","","POOJA","S TANDULKAR","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","01","09","2011","","M","0","025","","GAJANAN","M KAPATE","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","05","12","2011","","M","0","026","","KUNAL","A SAWTE","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","02","10","2011","","M","0","027","","SHRIKANT","R SAWTE","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","31","10","2011","","M","0","028","","PRATIK","D ADHAVALE","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","31","10","2011","","F","0","029","","PINKY","D KHANDAGALE","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","23","12","2011","","F","0","030","","SHRINIDHI","S PANCHAL","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","12","01","2012","","F","0","031","","BUSHRA","S HAJI","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","03","02","2012","","M","0","032","","SAMRAT","D SAWTE","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","20","02","2012","","M","0","033","","ATUL","K PANDIT","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","13","04","2012","","F","0","034","","SANJIWANI","J SAWTE","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","14","04","2012","","F","0","035","","RUTUJA","G SAWTE","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","28","06","2012","","F","0","036","","YASHODA","P BHUTAK","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","18","08","2012","","M","0","037","","ROHAN","S SAWTE","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","10","03","2012","","M","0","038","","KRUSHNA","P KAPATE","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","29","05","2012","","M","0","039","","SIDHANT","P SAWTE","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","29","07","2012","","M","0","040","","OMKAR","B PANCHAL","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","06","07","2012","","F","0","041","","RAMESHWARI","S SAWTE","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","05","08","2012","","M","0","042","","RAVI","R SAWTE","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","29","08","2012","","M","0","043","","NITIN","R SAWTE","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","25","09","2012","","M","0","044","","SOHAM","B SAWTE","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","01","03","2013","","M","0","045","","PRASHANT","V RAJWADE","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","17","03","2013","","M","0","046","","BRAMHANAND","S TANDULKAR","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","19","05","2013","","M","0","047","","AVINASH","V SAWTE","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","11","12","2012","","F","0","048","","ANAJLI","D KHANDAGALE","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","15","09","2013","","M","0","049","","PRASHIK","G SAWTE","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","17","08","2013","","M","0","050","","PRATHMESH","D ATHWALE","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","28","10","2013","","M","0","051","","PINTU","A TANDULKAR","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","14","11","2013","","F","0","052","","PINKY","D KHANDAGALE","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","21","04","2014","","M","0","053","","PINTU","S HAJI","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","05","04","2014","","F","0","054","","GOURI","M KAPATE","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","05","04","2014","","M","0","055","","DADARAO","V KAPATE","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","04","05","2014","","M","0","056","","SHUBHAM","B BHAYMORE","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","04","05","2014","","M","0","057","","PINTU","R SAWTE","27511010211"))

    insertMasterChildData(MasterChildDataEntity("child","26","06","2012","","F","0","001","","SOJAL","G KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","01","08","2012","","F","0","002","","PRATIKSH","R PANCHAL","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","30","09","2012","","F","0","003","","SAMIKSHA","Y PANCHAL","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","06","10","2012","","F","0","004","","DHANASHRI","B KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","05","12","2012","","F","0","005","","ISHWARI","G KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","02","01","2013","","F","0","006","","SHOJAL","M KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","22","03","2013","","F","0","007","","SNEHAL","R PANCHAL","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","10","05","2013","","F","0","008","","PRIYA","P KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","26","06","2013","","F","0","009","","SHVARUPA","D KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","11","07","2013","","F","0","010","","AKSDA","S KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","29","08","2013","","F","0","011","","DURGA","S RAJGHORE","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","21","10","2013","","F","0","012","","SHONDRYA","N KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","24","01","2014","","F","0","013","","DURGA","G GHORE","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","13","03","2014","","F","0","014","","ASAVARI","K KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","26","03","2014","","F","0","015","","SAKSHI","R KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","01","04","2014","","F","0","016","","SANSKRUTI","B KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","13","06","2014","","F","0","017","","PINKI","V KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","08","06","2014","","F","0","018","","VAISHNAVI","B KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","03","06","2014","","F","0","019","","DURGA","D KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","11","10","2012","","F","0","020","","SANDYA","R KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","24","08","2014","","F","0","021","","SHRUTI","S KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","17","08","2014","","F","0","022","","PINKI","G KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","20","09","2014","","F","0","023","","PINKI","T KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","18","11","2014","","F","0","024","","PINKI","K KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","21","11","2014","","F","0","025","","PINKI","T KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","12","02","2014","","F","0","026","","PINKI","B KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","01","02","2015","","F","0","027","","PINKI","D KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","28","02","2015","","F","0","028","","PINKI","S KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","01","03","2015","","F","0","029","","SUPRIYA","R PANCHAL","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","03","05","2012","","M","0","030","","SUMIT","R KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","04","07","2012","","M","0","031","","VEDANT","DUPARE","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","24","08","2012","","M","0","032","","RUTURAJ","B KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","28","10","2012","","M","0","033","","PRATHMESH","N KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","01","11","2012","","M","0","034","","DATTRAMJI","K KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","05","12","2012","","M","0","035","","SAMARTH","S KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","19","12","2012","","M","0","036","","SAMARTH","K KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","11","01","2013","","M","0","037","","SAINATH","R KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","02","02","2013","","M","0","038","","SOHAM","G KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","02","02","2013","","M","0","039","","BALAJI","MJADAV","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","30","07","2013","","M","0","040","","PINTU","B KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","19","08","2013","","M","0","041","","MAHESH","N PANCHAL","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","05","09","2013","","M","0","042","","YURAJ","N KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","08","10","2013","","M","0","043","","PARMESHWAR","P THENGALE","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","16","10","2013","","M","0","044","","SAIPRSAD","R PUYAD","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","25","11","2013","","M","0","045","","KRISHNA","U KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","06","12","2013","","M","0","046","","SWARAJ","M KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","16","01","2014","","M","0","047","","KRISHNA","S KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","02","02","2014","","M","0","048","","PRADUM","S PANCHAL","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","09","03","2014","","M","0","049","","SARVESH","S KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","30","03","2014","","M","0","050","","KRISHNA","D KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","17","04","2014","","M","0","051","","SHHAJI","S KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","13","02","2013","","M","0","052","","PINTU","M KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","25","08","2013","","M","0","053","","KRISHNA","B KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","17","04","2012","","F","0","054","","SANDYA","R PUYAD","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","03","03","2012","","F","0","055","","CHANCHL","K THENGALE","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","21","11","2011","","F","0","056","","SHRUTI","S KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","01","02","2012","","F","0","057","","SWETA","B KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","13","11","2011","","F","0","058","","SAMIKSHA","S KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","27","10","2011","","F","0","059","","DIPALI","P THENGALE","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","01","09","2011","","F","0","060","","DURGA","S RAJGHORE","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","16","08","2011","","F","0","061","","JYOTI","S KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","29","01","2011","","F","0","062","","YOGITA","D KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","20","12","2010","","F","0","063","","GAYATRI","G KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","17","08","2010","","F","0","064","","GANGASAGAR","M KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","28","05","2010","","F","0","065","","DIVYA","N KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","19","04","2010","","F","0","066","","MAYURI","M KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","03","04","2009","","F","0","067","","VAISHNAVI","D UPARE","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","26","12","2009","","F","0","068","","CHETANYA","V KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","24","06","2009","","F","0","069","","ANKITA","S RAJGHORE","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","07","05","2009","","F","0","070","","SAMIKSHA","S KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","02","04","2010","","F","0","071","","GAYATRI","G KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","26","04","2012","","M","0","072","","OMKAR","V KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","04","03","2012","","M","0","073","","ADITYA","D KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","09","02","2012","","M","0","074","","MANGESH","T THENGALE","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","21","11","2011","","M","0","075","","SASHIKANTH","MANIKARAO","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","11","09","2011","","M","0","076","","ABHIJIT","PANJAB","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","23","11","2010","","M","0","077","","SHIVAM","G KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","13","10","2011","","M","0","078","","SADANANDA","S KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","20","10","2011","","M","0","079","","VEADANT","A KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","12","09","2011","","M","0","080","","SAIPRASHD","V GHODARE","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","11","05","2011","","M","0","081","","LKSHIMAN","R PANCHAL","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","01","04","2011","","M","0","082","","RANGANATH","M KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","20","12","2010","","M","0","083","","PRATIK","P PANCHAL","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","02","10","2010","","M","0","084","","NEHAL","R KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","02","10","2010","","M","0","085","","NAVNATH","M KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","15","09","2010","","M","0","086","","GOURAV","G KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","21","07","2010","","M","0","087","","RAJESH","G KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","01","04","2010","","M","0","088","","SHREYASH","G JOGDAND","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","01","10","2009","","M","0","089","","SURAJ","R KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","08","10","2009","","M","0","090","","RAJESH","S KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","24","08","2009","","M","0","091","","OMKAR","B KADAM","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","14","06","2009","","M","0","092","","MAHESH","N JADAV","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","30","06","2009","","M","0","093","","PURBHAJI","G GHORE","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","30","05","2009","","M","0","094","","ISHWARA ","P THENGALE","27511010406"))

    insertMasterChildData(MasterChildDataEntity("child","20","06","2012","","F","0","001","","ANANYAYA","P RUPARKHE","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","19","07","2012","","F","0","002","","NIKITA","K GAVLI","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","06","08","2013","","F","0","003","","UNNATI","B KADAM","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","11","09","2013","","F","0","004","","PINKI","R KADAM","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","17","09","2013","","F","0","005","","JOYA","M SHAIKH","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","13","10","2013","","F","0","006","","ANUJA","K KADAM","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","04","10","2013","","F","0","007","","VAISHNAVI","B KADAM","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","07","03","2013","","F","0","008","","ROHINI","K GAVLI","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","22","03","2014","","F","0","009","","SAMRADHI","G WANKHEDE","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","25","12","2014","","F","0","010","","SHONI","N KADAM","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","25","12","2014","","F","0","011","","VIDHYA","M KADAM","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","10","08","2012","","M","0","012","","KRUSHNA","N KADAM","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","22","01","2013","","M","0","013","","BHUSHAN","B THORAT","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","09","07","2013","","M","0","014","","SAMARTH","M KADAM","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","11","09","2013","","M","0","015","","SAMARTH","G KADAM","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","04","10","2014","","M","0","016","","SAI","P KADAM","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","11","10","2013","","M","0","017","","SAI","D KADAM","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","13","03","2014","","M","0","018","","SATYM","B KADAM","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","07","03","2014","","M","0","019","","BHUJANG","K GAVLI","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","17","05","2014","","M","0","020","","VINAYK","C KADAM","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","02","07","2014","","M","0","021","","PANDIT","V KADAM","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","03","08","2014","","M","0","022","","PRATHAVIRAJ","G KADAM","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","11","08","2014","","M","0","023","","SUNIL","B THORAT","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","28","11","2014","","M","0","024","","SOMYA","P RANPARKHE","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","09","05","2009","","M","0","025","","KESHAV","G KADAM","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","30","08","2009","","F","0","026","","BEBY","M KADAM","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","20","07","2009","","F","0","027","","PALVI","R GAVLI","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","02","09","2009","","F","0","028","","DURGA","P WANKHEDE","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","20","09","2009","","F","0","029","","ARTI","A RANPARKHE","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","14","03","2014","","M","0","030","","VISHAVJIT","B THORAT","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","15","01","2010","","M","0","031","","KRASHNA","N KADAM","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","04","12","2009","","F","0","032","","AYSHA","R SHAIKH","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","28","01","2010","","F","0","033","","SAMRADHI","R SONTAKE","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","07","12","2009","","F","0","034","","DIVYA","C KADAM","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","10","12","2009","","F","0","035","","ADITI","B THORAT","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","04","03","2010","","M","0","036","","ROHAN","R KADAM","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","17","12","2009","","F","0","037","","KOMAL","S THORAT","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","10","03","2010","","M","0","038","","SUMIT","N KADAM","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","10","08","2010","","F","0","039","","AMBIKA","M RENGE","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","07","08","2010","","F","0","040","","SHRAVNI","S SALUNKHE","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","28","10","2010","","F","0","041","","NIKITA","G GAVLI","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","10","07","2010","","M","0","042","","ANKAR","G KADAM","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","15","07","2010","","M","0","043","","ADINATH","G KADAM","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","07","12","2010","","M","0","044","","ARTHV","B KADAM","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","26","01","2010","","M","0","045","","TUKARAM","S KADAM","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","08","02","2011","","M","0","046","","VAIBHAV","K KADAM","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","04","03","2011","","M","0","047","","ANKAR","S KADAM","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","07","05","2011","","M","0","048","","BHIMRAO","G KADAM","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","25","06","2011","","M","0","049","","BALU","G WAGHMARE","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","08","07","2011","","M","0","050","","VITHAL","S KADAM","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","04","08","2011","","F","0","051","","GUARI","G KADAM","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","27","10","2011","","F","0","052","","DIPALI","P WANKHEDE","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","16","01","2012","","F","0","053","","AKSHDA","B KADAM","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","05","04","2012","","M","0","054","","AVINASH","G KADAM","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","25","04","2012","","M","0","055","","SAMSODIN","M SHAIKH","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","03","03","2012","","F","0","056","","ARTI","B THORAT","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","03","04","2012","","F","0","057","","VIDHYA","M WAGHMARE","27511010407"))

    insertMasterChildData(MasterChildDataEntity("child","01","06","2009","","M","0","001","","VISHVJEET","S KURHADE","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","31","07","2009","","M","0","002","","BALAJI","J KUNDEVAR","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","24","11","2009","","M","0","003","","VIVEK","K PURI","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","03","01","2010","","M","0","004","","ARJUN","H RANEWAD","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","05","05","2010","","M","0","005","","KARTIK","R SOLANKE","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","06","05","2010","","M","0","006","","SWAPNIL","G ENGLE","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","28","07","2010","","M","0","007","","PARMESHWAR","D ENGLE","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","30","08","2010","","M","0","008","","VINAYAK","A KUNDEWAR","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","03","09","2010","","M","0","009","","SHYAM","P HARKARI","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","21","09","2010","","M","0","010","","GANESH","B SHIVNAKR","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","05","10","2010","","M","0","011","","KRASHAN","A JILHEKAR","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","01","11","2010","","M","0","012","","SHIVRAJ","M RAVLE","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","02","11","2010","","M","0","013","","GAURAV","S RAVALE","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","10","04","2011","","M","0","014","","KRASHANA","R KAMBLE","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","17","09","2011","","M","0","015","","ANURUDHA ","B PANCHAL","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","17","09","2011","","M","0","016","","CHANDORAO","T DHUMALE`","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","20","10","2011","","M","0","017","","PAWAN","P NARWADE","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","04","02","2012","","M","0","018","","VIJAY","A KUNDEWAR","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","10","02","2012","","M","0","019","","SANKET","S ENGLE","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","23","03","2012","","M","0","020","","ATHARV","K HARKARI","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","31","03","2012","","M","0","021","","SHARD","M RANEWAD","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","15","07","2012","","M","0","022","","SHARVIL","S KALYANKAR","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","28","08","2012","","M","0","023","","ADITYA","A KALYANKAR","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","25","11","2012","","M","0","024","","SANKET","R PURI","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","19","01","2013","","M","0","025","","ROHAN","S SHIVNKAR","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","08","04","2013","","M","0","026","","VIRENDRA","P KALAYNKAR","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","31","05","2013","","M","0","027","","SHASHIKANT","A PATANGE","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","23","07","2013","","M","0","028","","PRATHMESH","A PURI","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","01","11","2013","","M","0","029","","SAMRTH","S ENGLE","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","04","11","2013","","M","0","030","","SHRESH","P HARKARI","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","01","01","2014","","M","0","031","","PARDMESHWR","B ENGALE","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","07","03","2014","","M","0","032","","SHRIKANT","S ENGLE","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","24","03","2014","","M","0","033","","SHIVARAJ","V HARKARI","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","15","04","2014","","M","0","034","","SHRIKANT","D PAPULWAD","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","21","06","2014","","M","0","035","","ADITYA","M KURHADE","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","21","06","2014","","M","0","036","","RUDRA","T JADHAV","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","06","09","2014","","M","0","037","","SHIVAJI","S ENGLE","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","09","09","2014","","M","0","038","","RAMJI","P ENGLE","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","10","10","2014","","M","0","039","","YOGESH","B BOLE","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","06","04","2009","","F","0","040","","SAMIKSHA","S ENGLE","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","13","05","2009","","F","0","041","","POONAM","R ENGLE","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","14","10","2009","","F","0","042","","SAIJAL","S RANKHAM","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","02","02","2009","","F","0","043","","GANGASAGAR","T DHUMALE`","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","11","09","2009","","F","0","044","","GEETA","V PRAVAT","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","25","07","2010","","F","0","045","","POOJA","P NARWADE","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","01","09","2010","","F","0","046","","JANVI","S RANKHAMB","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","10","08","2010","","F","0","047","","AKSHRA","V HARKARI","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","01","09","2010","","F","0","048","","SRUPATI","D DHAGE","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","01","09","2010","","F","0","049","","GAURI","B HARKARI","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","01","12","2010","","F","0","050","","MADHURI","B ENGLE","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","01","12","2010","","F","0","051","","NEHA","N CHUHAN","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","19","12","2010","","F","0","052","","SHITAL","R PURI","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","08","02","2011","","F","0","053","","BHAKTI","P JINTURKAR","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","23","03","2011","","F","0","054","","SIDHIKA","J VAKHARE","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","05","03","2011","","F","0","055","","ANUSHKA","A MORE","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","04","05","2011","","F","0","056","","GAURI","S KALYANKAR","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","24","08","2011","","F","0","057","","ARUSHI","P HARKARI","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","24","10","2011","","F","0","058","","SHRUPATI","S ENGLE","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","26","02","2012","","F","0","059","","PRATIKSHA","S MORE","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","22","02","2012","","F","0","060","","KHUSHI","M KURHADE","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","01","04","2012","","F","0","061","","MANSI","M ENGLE","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","28","08","2012","","F","0","062","","RUDRARANI","D ENGLE","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","02","09","2012","","F","0","063","","AKSHRA","D DHAGE","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","15","09","2012","","F","0","064","","KALYANI","B BARATE","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","29","10","2012","","F","0","065","","SANPADA","D MORE","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","30","10","2012","","F","0","066","","SHRUTI","D PAPULWAD","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","12","12","2012","","F","0","067","","RADHIKA","V HARKARI","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","28","12","2012","","F","0","068","","SRAVANI ","S RANKHAMB","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","01","02","2013","","F","0","069","","ANJALI","A MORE","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","30","04","2013","","F","0","070","","ESHWARI","B KALYANKAR","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","15","08","2013","","F","0","071","","ANANYA ","P ENGLE","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","04","11","2013","","F","0","072","","APEKSHA","P HARKARI","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","02","02","2014","","F","0","073","","PADMAVATI","Y HARKARI","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","08","02","2014","","F","0","074","","SAMIKSHA","K HARKARI","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","02","03","2014","","F","0","075","","PRANJALI","S KOLSHIKAR","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","05","03","2014","","F","0","076","","KHUSHI","K HARKARI","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","27","09","2014","","F","0","077","","PINKI","P ENGLE","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","21","12","2014","","M","0","078","","DHANJAY","MORE","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","08","02","2015","","M","0","079","","BALI ","B BORATE","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","10","04","2015","","M","0","080","","BALU","S KALYANKAR","27511010109"))

    insertMasterChildData(MasterChildDataEntity("child","03","07","2009","","F","0","001","","PRATIBHA","D ENGOLE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","13","09","2009","","F","0","002","","DURGA","P LONE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","02","12","2009","","F","0","003","","SANCHI","S VAHEVAL","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","10","06","2010","","F","0","004","","ANCHAL","S SAWANT","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","30","12","2010","","F","0","005","","AKSHARA","R VAHEVAL","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","09","02","2010","","F","0","006","","PRATIKSHA","M GOLE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","25","03","2011","","F","0","007","","PRATIKSHA","D ENGOLE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","06","03","2011","","F","0","008","","ROSHANI","P LONE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","31","03","2011","","F","0","009","","TARNUMA","S EMRAN","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","18","07","2011","","F","0","010","","GUATAMI","J LONE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","05","09","2011","","F","0","011","","SHUBHANGI","C SAVALE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","14","09","2011","","F","0","012","","VAISHNVAI","D ADERAGHO","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","26","10","2011","","F","0","013","","RAJSHRI","R LONE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","21","12","2012","","F","0","014","","SAYOJEETA","B VAHE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","02","06","2012","","F","0","015","","SAKSHISHA","S SAWANT","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","13","07","2012","","F","0","016","","KANIKSHA","B NARVADE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","12","09","2012","","F","0","017","","PRAMITA","K VAHIVALE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","05","10","2012","","F","0","018","","NIRNJANA","S LONE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","01","11","2012","","F","0","019","","HARSHDA","P SAWANT","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","29","01","2013","","F","0","020","","ROSHANI","A KHURESHI","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","28","02","2013","","F","0","021","","ASMITA","S ADERAGHO","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","28","02","2013","","F","0","022","","SHUBHANGI","P VAHIVALE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","22","06","2013","","F","0","023","","JOYA","A SAYYYED","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","08","07","2013","","F","0","024","","SHUBHRLAI","B VAHE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","07","08","2013","","F","0","025","","SNEHA","S LONE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","17","09","2013","","F","0","026","","SANDHYA","D BUKTARE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","17","09","2013","","F","0","027","","TEJSWANI","S SAWANT","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","26","09","2013","","F","0","028","","NAVYA","S SAHEVAL","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","26","09","2013","","F","0","029","","PRANALI","G GOLE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","23","11","2013","","F","0","030","","SHREYA","R VAHEVAL","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","25","11","2013","","F","0","031","","SONAL","S SAWANT","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","17","04","2014","","F","0","032","","MANEJAFIDOS","EMRAN","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","17","04","2014","","F","0","033","","TANVI","R VAHEVAL","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","06","05","2014","","F","0","034","","PRAGATI","A SHEVALE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","30","05","2014","","F","0","035","","CHANCHAL","V LONE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","02","07","2014","","F","0","036","","AMMU","S SAWANT","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","24","08","2014","","F","0","037","","SAWARI","P SAWANT","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","01","11","2014","","F","0","038","","SAROJA","A SAYYED","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","25","03","2015","","F","0","039","","BABLI","S NARVADE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","26","12","2009","","M","0","040","","SAHIL","S NITNAVARE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","21","08","2010","","M","0","041","","GUNAJI","T VANELE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","21","08","2010","","M","0","042","","PRASHIK","P LONE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","21","05","2009","","M","0","043","","ALABHKSH","A KHURESHI","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","21","09","2009","","M","0","044","","SOHAN","D VAHEVAL","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","09","09","2009","","M","0","045","","ATISH","N VAHEVAL","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","01","02","2010","","M","0","046","","RADHESHYAM","S JADHAV","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","18","09","2010","","M","0","047","","AYUSH","A SAWANT","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","27","02","2011","","M","0","048","","NAGESH","G GOLE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","26","03","2011","","M","0","049","","RIHAN","A KHURESHI","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","16","04","2011","","M","0","050","","KUNAL","K VAHEVAL","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","16","04","2011","","M","0","051","","TUSHAR","A SHEVALE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","27","05","2011","","M","0","052","","SHUBHAM","P VAHIVALE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","06","06","2011","","M","0","053","","SURAJ","D BUKTARE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","08","08","2011","","M","0","054","","YURAJ","B SAWANT","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","08","09","2011","","M","0","055","","PAVAN","J BUKATARE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","05","10","2011","","M","0","056","","VANSHAM","S SAWANT","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","27","11","2011","","M","0","057","","NISHANT","S VAHEVAL","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","13","02","2012","","M","0","058","","SOMESH","S JADHAV","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","13","02","2012","","M","0","059","","VANSH ","R LONE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","21","04","2012","","M","0","060","","YASH","M LONE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","16","06","2012","","M","0","061","","ABHIJEET","B SURYWANSI","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","16","06","2012","","M","0","062","","VISHWAJEET","B SURYWANSI","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","18","08","2012","","M","0","063","","RUMAN","Y SHEKHA","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","23","11","2012","","M","0","064","","PRATIKSHA","J BUKTARE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","13","01","2013","","M","0","065","","PIYUSH","A SAWANT","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","31","03","2013","","M","0","066","","ALMASH","A SAYYED","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","27","05","2013","","M","0","067","","YASH","A LONE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","27","08","2013","","M","0","068","","PRANAV","C SAVALE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","28","12","2013","","M","0","069","","LAVKESH","M GOLE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","13","01","2014","","M","0","070","","PRANAV","B SAWANT","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","28","02","2014","","M","0","071","","SHIVAM","B NARVADE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","05","05","2014","","M","0","072","","BHAIYA"," S GOLE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","16","06","2014","","M","0","073","","SAKSHAM","S VAHEVALE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","23","07","2014","","M","0","074","","RIHAN","A SHAIKH","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","05","09","2014","","M","0","075","","SAMRTHA","K VAHIVALE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","02","10","2014","","M","0","076","","ASAD","A SAYYED","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","14","10","2014","","M","0","077","","BUDHARATN","S SAWANT","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","06","01","2015","","M","0","078","","BABALU","D KHOKLE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","18","03","2015","","M","0","079","","BHIAYA","D ENGOLE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","21","05","2009","","M","0","080","","ALBKSHA","A KHURESHI","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","21","09","2009","","M","0","081","","SOHAN","D VAHIVALE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","09","09","2009","","M","0","082","","ATISH","N VAHEVAL","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","01","02","2010","","M","0","083","","RADHESYAM","S JAHDAV","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","21","08","2010","","M","0","084","","GUNAJI","`T VANELE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","21","08","2010","","M","0","085","","PRASHIK","P LONE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","18","09","2010","","M","0","086","","AYUSH","P SAWANT","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","27","02","2011","","M","0","087","","NAGESH","G GOLE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","26","03","2011","","M","0","088","","RIHAN","A KHURESHI","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","16","04","2011","","M","0","089","","KUNAL","K VAHEVAL","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","23","05","2011","","M","0","090","","TUSHAR","A SHEVALE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","27","05","2011","","M","0","091","","SHUBHAM","P VAHIVALE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","06","06","2011","","M","0","092","","SURAJ","D BUKTARE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","08","08","2011","","M","0","093","","YURAJ","B SAWANT","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","08","09","2011","","M","0","094","","PAVAN","J BUKTARE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","05","10","2011","","M","0","095","","VASHAM","S SAWANT","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","27","11","2011","","M","0","096","","NISHANT","S VAHEVALE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","03","07","2009","","F","0","097","","PRATIBHA","D ENGOLE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","13","09","2009","","F","0","098","","DURGA","P LONE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","02","12","2009","","F","0","099","","SANCHI","S VAHEVALE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","02","12","2009","","F","0","100","","ANCHAL","S SAWANT","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","10","06","2010","","F","0","101","","AKSHRA","R VAHEVAL","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","09","02","2011","","F","0","102","","PRATIKSHA"," M GOLE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","25","03","2011","","F","0","103","","PRATIKSHA","D ENGOLE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","06","03","2011","","F","0","104","","ROSHNI","P LONE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","31","03","2011","","F","0","105","","TARNUMA","S SHAIKH","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","18","07","2011","","F","0","106","","GUATAMI","J LONE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","05","09","2011","","F","0","107","","SHUBHANGI","C SAVALE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","14","09","2011","","F","0","108","","VAISHNVAI","D ADERAGHO","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","14","10","2011","","F","0","109","","RAJSHRI","D LONE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","11","11","2011","","F","0","110","","AKSHRA","R LONE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","21","12","2011","","F","0","111","","SANYOJEETA","B VAHEVALE","27511010111"))

    insertMasterChildData(MasterChildDataEntity("child","19","08","2009","","M","0","001","","HARSHAD","D INGOLE","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","07","09","2009","","M","0","002","","SHAILESH","H INGOLE","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","17","12","2009","","M","0","003","","SANKET","G INGOLE","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","23","01","2010","","M","0","004","","SUMIT","U INGOLE","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","29","01","2010","","M","0","005","","MAHAVIR","G DHUMAL","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","26","03","2010","","M","0","006","","GAJENDRA","M SAVANT","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","14","04","2010","","M","0","007","","DHRAMARAJ","S INGOLE","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","14","05","2010","","M","0","008","","DEVESH","M INGOLE","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","11","01","2011","","M","0","009","","PURUSHOTAM","P INGOLE","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","09","01","2011","","M","0","010","","RUDRA","P KOLE","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","01","01","2011","","M","0","011","","SAMARTH","D INGOLE","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","19","02","2011","","M","0","012","","SATISH","G JADHAV","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","20","02","2011","","M","0","013","","VEDANT","G INGOLE","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","07","05","2011","","M","0","014","","MAHARUDRA","D SONTAKE","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","26","05","2011","","M","0","015","","RUDRA","D PATIL","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","01","09","2011","","M","0","016","","RUSHIKESH","M INGOLE","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","05","09","2011","","M","0","017","","VEDANT","D INGOLE","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","16","09","2011","","M","0","018","","KARAN","M SAVANT","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","25","09","2011","","M","0","019","","HARSHVARDHAN","R INGOLE","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","27","10","2011","","M","0","020","","PRANAV","A JOSHI","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","02","11","2011","","M","0","021","","SANDESH","N INGOLE","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","05","03","2012","","M","0","022","","ADARSH","P INGOLE","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","26","06","2012","","M","0","023","","KRUSHNA","K PHUTANKAR","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","24","07","2012","","M","0","024","","KAPIL","G BACHEWAR","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","29","07","2012","","M","0","025","","TEJAS","T PATIL","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","18","10","2012","","M","0","026","","AKASH","U INGOLE","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","23","10","2012","","M","0","027","","DINESH","M INGOLE","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","01","11","2012","","M","0","028","","ABHIJEET","R INGOLE","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","18","12","2012","","M","0","029","","SAINATH","S THERDE","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","14","04","2013","","M","0","030","","SAINATH","N CHOTMAL","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","24","05","2013","","M","0","031","","DHANAY","G INGOLE","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","23","07","2013","","M","0","032","","HARSH","G KADAM","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","27","08","2013","","M","0","033","","RITESH","M INGOLE","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","29","08","2013","","M","0","034","","NAROBA","R INGOLE","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","28","11","2013","","M","0","035","","SHRIDHAR","S SAVANT","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","06","02","2014","","M","0","036","","VINAYAK","H PATIL","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","10","05","2014","","M","0","037","","RITESH","V THERADE","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","11","05","2014","","M","0","038","","ARAV","M INGOLE","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","29","10","2014","","M","0","039","","SAMARTH","M INGOLE","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","06","02","2015","","M","0","040","","SAMARTH","P INGOLE","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","24","03","2015","","M","0","041","","SAMARTH","D INGOLE","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","23","03","2015","","M","0","042","","SAMARTH","V INGOLE","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","01","01","2006","","M","0","043","","SAMARTH","R BACHEWAR","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","07","08","2009","","F","0","044","","PRATIKSHA","R JADHAV","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","20","08","2009","","F","0","045","","NIHA","M SAVANT","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","21","09","2009","","F","0","046","","ADITI","P INGOLE","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","12","09","2009","","F","0","047","","PRAGATI","R INGOLE","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","08","10","2009","","F","0","048","","BHAKTI","K INGOLE","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","09","12","2009","","F","0","049","","PURVAJA","A JOSHI","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","27","03","2010","","F","0","050","","SHRAVANI","S INGOLE","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","08","11","2011","","F","0","051","","RUTUJA","R INGOLE","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","28","12","2010","","F","0","052","","SHRADHA","S INGOLE","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","06","11","2011","","F","0","053","","SRUSHTI","B INGOLE","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","17","11","2011","","F","0","054","","MANASI","M INGOLE","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","07","06","2011","","F","0","055","","SANJIVANI","G INGOLE","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","25","07","2011","","F","0","056","","PRITI","R INGOLE","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","13","05","2012","","F","0","057","","NANDINI","D INGOLE","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","28","09","2012","","F","0","058","","SHRADHA","S SAVANT","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","29","09","2012","","F","0","059","","ARADHYA","G MALEGAVKAR","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","16","01","2013","","F","0","060","","BHAKTI","D INGOLE","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","16","03","2013","","F","0","061","","PRITI","K INGOLE","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","05","08","2013","","F","0","062","","ARADHYA","H INGOLE","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","30","08","2013","","F","0","063","","MAYURI","G DHUMAL","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","30","08","2013","","F","0","064","","RADHIKA","R INGOLE","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","23","10","2014","","F","0","065","","SHRUTI","K INGOLE","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","20","11","2014","","F","0","066","","SHRUTI","V INGOLE","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","30","11","2014","","F","0","067","","SHRADHA","N CHOTMAL","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","13","12","2014","","F","0","068","","ASHLESHA","G INGOLE","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","01","06","2014","","F","0","069","","ASHLESHA","G AMBHORE","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","24","09","2014","","F","0","070","","PRACHI","G INGOLE","27511010305"))

    insertMasterChildData(MasterChildDataEntity("child","09","06","2009","","F","0","001","","SHAILESH","G GADE","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","20","06","2009","","M","0","002","","SHREYA","S KULKARNI","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","09","06","2009","","M","0","003","","SHIVAM","S JADHAV","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","30","07","2009","","F","0","004","","LAXMAN","S CHAVHAN","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","12","07","2009","","M","0","005","","NILAM","S KUSHVAH","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","25","08","2009","","M","0","006","","PRATHMESH","P RAJEWAR","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","13","09","2009","","F","0","007","","GOURI","G HUNDE","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","15","10","2009","","M","0","008","","RAM","M BURTE","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","10","11","2009","","F","0","009","","SHWETA","B JATALE","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","15","01","2010","","F","0","010","","SONAL","N CHANDRAVANSHI","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","16","02","2010","","F","0","011","","RESHMA","D DHOTRE","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","10","03","2010","","F","0","012","","SOFIYANA","S JAVED","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","26","03","2010","","F","0","013","","SAMRUDHI","G GORE","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","19","06","2010","","M","0","014","","GANESH","R DHOTARE","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","31","07","2010","","M","0","015","","AJINKYA","B KAMBALE","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","04","09","2010","","M","0","016","","NARESH","G SOLANKE","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","30","08","2010","","M","0","017","","OMSAI","C KHANDAGALE","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","21","09","2010","","F","0","018","","SAKSHI","K PAWAR","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","29","09","2010","","M","0","019","","KRUSHNA","V BARATE","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","19","09","2010","","F","0","020","","VAISHNAVI","A GALANDE","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","01","09","2010","","F","0","021","","SHRAVANI","D CHORE","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","06","12","2010","","F","0","022","","SANDHYA","B PARMAR","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","24","12","2010","","M","0","023","","SHIVAM","R SIRALE","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","21","01","2011","","M","0","024","","OMKAR","G GALANDE","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","05","01","2011","","F","0","025","","SHRADHA","C KHANDAGALE","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","13","02","2011","","F","0","026","","NIKITA","S GOLKODE","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","01","02","2011","","F","0","027","","IFRA","F KHATIK","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","21","03","2011","","F","0","028","","NAJIM","S IJAJ","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","26","04","2011","","M","0","029","","SHIVAM","U KADAM","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","15","04","2011","","M","0","030","","SHREYAS","S KULKARNI","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","12","06","2011","","F","0","031","","SIMRAN","S SHABIR","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","23","07","2011","","F","0","032","","JOSHANA","S ANNAPURNE","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","12","07","2011","","F","0","033","","PINKI","G GALANDE","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","05","07","2011","","M","0","034","","DEEPAK","D DHOTRE","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","18","08","2011","","F","0","035","","RIDHI","V INGOLE","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","25","11","2011","","M","0","036","","PINTU","G GAYAKWAD","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","01","11","2011","","F","0","037","","PINKI","S KUSHVAH","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","14","11","2011","","M","0","038","","SHRIPAD","M GALNDE","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","13","12","2011","","F","0","039","","AKSHRA","S CHAVHAN","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","10","01","2012","","F","0","040","","SAKSHI","C KAMBALE","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","20","02","2012","","M","0","041","","ISHAN","S NAVED","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","12","04","2012","","F","0","042","","PRAJAL","B KESHEWAR","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","23","04","2012","","F","0","043","","NIYATI","D KESHEWAR","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","23","04","2012","","F","0","044","","PINKI","P MYAKALWAD","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","27","05","2012","","F","0","045","","RUTUJA","R AVARDE","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","15","07","2012","","M","0","046","","SRAVAN","B JAYALE","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","17","07","2012","","M","0","047","","JIGAR","B PARMAR","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","24","07","2012","","M","0","048","","AFNAN","F KHATIK","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","28","08","2012","","M","0","049","","ARYAN","H KHURDAMOJE","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","06","09","2012","","M","0","050","","YUVRAJ","H WAGHMARE","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","24","09","2012","","M","0","051","","SOHAM","M KUNTE","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","24","09","2012","","M","0","052","","KRUSHNA","S KADAM","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","26","10","2012","","M","0","053","","SOHAM","M DHOTARE","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","09","11","2012","","M","0","054","","SWARAJ","S CHAVHAN","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","07","12","2012","","M","0","055","","SANDEEP","R DHOTARE","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","25","12","2012","","M","0","056","","SWARAJ","G HUNDE","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","07","03","2013","","M","0","057","","MITHILESH","M BUTTE","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","21","06","2013","","F","0","058","","SHRADHA","D CHORE","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","15","06","2013","","M","0","059","","PINTU","G GAYAKWAD","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","25","06","2013","","M","0","060","","ATHARV","A SWAMI","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","29","11","2013","","F","0","061","","ALIYA","S SAMSHIR","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","03","08","2013","","F","0","062","","JOYA","S JAVED","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","16","10","2013","","F","0","063","","SHAIJA","M BUTTE","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","21","10","2013","","M","0","064","","KARAN","V BODKE","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","14","11","2013","","M","0","065","","ARUSH","N WAGHMARE","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","27","01","2014","","F","0","066","","SHEJAL","A PARMAR","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","24","02","2014","","M","0","067","","MANMATH","B KESHEWAR","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","06","01","2014","","M","0","068","","SAINATH","B KAMBALE","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","15","04","2014","","M","0","069","","SOFIYANA","F KHATIK","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","14","04","2014","","F","0","070","","TANAVI","G CHANEWAR","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","11","04","2014","","M","0","071","","PINTU","D PUND","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","28","05","2014","","F","0","072","","PINKI","S CHAVHAN","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","10","06","2014","","F","0","073","","PINKI","P PARMAR","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","08","08","2014","","F","0","074","","PINKI","C KAMBALE","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","15","08","2014","","F","0","075","","PRAGATI","M GALANDE","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","13","08","2014","","M","0","076","","PINTU","M DHOTARE","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","06","02","2015","","F","0","077","","PINKI","D KESHEWAR","27511010306"))

    insertMasterChildData(MasterChildDataEntity("child","26","02","2015","","M","0","078","","PAVAN","G WAGHMARE","27511010306"))



/*              val results  = documentClient
                .queryDocuments(
                  "dbs/" + DATABASE_ID + "/colls/" + COLLECTION_ID,
                  "SELECT * FROM myCollection",
                  null).getQueryIterable().toList;
        println("----------> "+documentClient.getDatabaseAccount.getCollection("log_data").toString)*/
}
