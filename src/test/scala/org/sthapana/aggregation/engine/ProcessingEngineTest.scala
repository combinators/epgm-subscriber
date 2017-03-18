package org.sthapana.aggregation.engine

import com.google.gson.Gson
import com.microsoft.azure.documentdb.{ConnectionPolicy, ConsistencyLevel, Document, DocumentClient}
import org.junit.{Assert, Test}
import org.sthapana.aggregation.dataobjects._
import org.sthapana.aggregation.utils.{AgeWiseConsolidationUtils, GenderWiseConsolidationUtils, GradeWiseConsolidationUtils, MonthWiseConsolidationUtils}

import scala.collection.JavaConverters._

class ProcessingEngineTest {

  val HOST = "https://epgm.documents.azure.com:443/"
  val MASTER_KEY = "SlhyMCNEuU55HklqqibVpNAqi58tN5ZcBjYznR2SLUxNOsjNaEH7JT3kLsaB6K9mRFMtTrl10bx3oJYm9DfsAA=="
  val DATABASE_ID = "thewall"
  val COLLECTION_ID = "tyrion"

  val documentClient = new DocumentClient(HOST,
    MASTER_KEY, ConnectionPolicy.GetDefault(),
    ConsistencyLevel.Session)

  @Test
  def itShouldGetMasterChildData(): Unit = {
    val results1 = documentClient.queryDocuments(
      "dbs/" + DATABASE_ID + "/colls/" + COLLECTION_ID,
      "SELECT * FROM tyrion where tyrion.doctype=\"dashboard\" ",
      //      "SELECT * FROM tyrion ",
      null).getQueryIterable().toList()

    println("dashboard ===>" + results1)

    val results2 = documentClient.queryDocuments(
      "dbs/" + DATABASE_ID + "/colls/" + COLLECTION_ID,
      "SELECT * FROM tyrion where tyrion.doctype=\"log\" ",
      //      "SELECT * FROM tyrion ",
      null).getQueryIterable().toList()

    println("log ===>" + results2)

    val results3 = documentClient.queryDocuments(
      "dbs/" + DATABASE_ID + "/colls/" + COLLECTION_ID,
      "SELECT * FROM tyrion where tyrion.doctype=\"child\" ",
      //      "SELECT * FROM tyrion ",
      null).getQueryIterable().toList()

    println("child ===>" + results3)
  }

  @Test
  def itShouldInserMasterChildRecord(): Unit = {
    //given
    val expectedMUWCount = "21"
    val expectedTotalCount = "101"
    val expectedMaleCount = "65"
    val expectedAgeCount = "15"
    val expectedMonthCount = "21"

    //when
    val masterChildEntity = MasterChildDataEntity("child", "27", "11", "2017", "", "M", "2", "041", "", "N PANT",
      "SHRIKANT", "27511010507")
    val entityJson = new Gson().toJson(masterChildEntity)
    val entityDocument = new Document(entityJson)

    documentClient.createDocument(s"dbs/$DATABASE_ID/colls/$COLLECTION_ID",
      entityDocument, null, false).getResource()
  }

  @Test
  def itShouldRemoveAllMasterData(): Unit = {

    val documents = documentClient.queryDocuments(
      "dbs/" + DATABASE_ID + "/colls/" + COLLECTION_ID,
      "SELECT * FROM tyrion where tyrion.doctype=\"child\" ",
      //      "SELECT * FROM tyrion ",
      null).getQueryIterable().asScala.toList

    documents.foreach(d => {
      documentClient.deleteDocument(d.getSelfLink(), null)
    })

  }

  @Test
  def itShouldRemoveAllLogData(): Unit = {

    val documents = documentClient.queryDocuments(
      "dbs/" + DATABASE_ID + "/colls/" + COLLECTION_ID,
      "SELECT * FROM tyrion where tyrion.doctype=\"log\" ",
      //      "SELECT * FROM tyrion ",
      null).getQueryIterable().asScala.toList

    documents.foreach(d => {
      documentClient.deleteDocument(d.getSelfLink(), null)
    })


  }

  @Test
  def itShouldRemoveAllDashboardData(): Unit = {

    val documents = documentClient.queryDocuments(
      "dbs/" + DATABASE_ID + "/colls/" + COLLECTION_ID,
      "SELECT * FROM tyrion where tyrion.doctype=\"dashboard\" ",
      //      "SELECT * FROM tyrion ",
      null).getQueryIterable().asScala.toList

    documents.foreach(d => {
      documentClient.deleteDocument(d.getSelfLink(), null)
    })
  }

  @Test
  def itShouldTestMethod_UpdateIfMonthIsNotSame_(): Unit = {
    val tyrionMap: Map[String, String] = Map(
      "twotothreecount" -> "8", "doctype" -> "tyrion",
      "threetofourcount" -> "8", "code" -> "27",
      "fourtofivecount" -> "0", "currentmonth" -> "03",
      "fivetosixcount" -> "0", "currentyear" -> "17",
      "januarycount" -> "30", "suwcount" -> "10",
      "februarycount" -> "30", "muwcount" -> "10",
      "marchcount" -> "30", "normalcount" -> "10",
      "aprilcount" -> "30", "totalcount" -> "30",
      "maycount" -> "30", "malecount" -> "15",
      "junecount" -> "30", "femalecount" -> "15",
      "julycount" -> "30", "zerotoonecount" -> "7",
      "augustcount" -> "30", "onetotwocount" -> "7",
      "septembercount" -> "30", "novembercount" -> "30",
      "octobercount" -> "30", "decembercount" -> "30"
    )

    val updateEntityWithOutMonthChange: UpdateEntity = UpdateEntity("27", "", "", "", "", "2", "M", "5", "03", "17")
    val updateEntityWithMonthChange: UpdateEntity = UpdateEntity("27", "", "", "", "", "2", "M", "5", "04", "17")
    val updateEntityWithYearChange: UpdateEntity = UpdateEntity("27", "", "", "", "", "2", "M", "5", "01", "18")
    val updateEntityWithMonthAndYearChange: UpdateEntity = UpdateEntity("27", "", "", "", "", "2", "M", "5", "04", "18")

    val mapWithOutMonthChange = new ProcessingEngine().updateIfMonthIsNotSame((Option(tyrionMap),Option(new Document())),
      updateEntityWithOutMonthChange.currentMonth, updateEntityWithOutMonthChange.currentYear)
    val mapWithMonthChange = new ProcessingEngine().updateIfMonthIsNotSame((Option(tyrionMap),Option(new Document())),
      updateEntityWithMonthChange.currentMonth, updateEntityWithMonthChange.currentYear)
    val mapWithYearChange = new ProcessingEngine().updateIfMonthIsNotSame((Option(tyrionMap),Option(new Document())),
      updateEntityWithYearChange.currentMonth, updateEntityWithYearChange.currentYear)
    val mapWithMonthAndYearChange = new ProcessingEngine().updateIfMonthIsNotSame((Option(tyrionMap),Option(new Document())),
      updateEntityWithMonthAndYearChange.currentMonth, updateEntityWithMonthAndYearChange.currentYear)

    Assert.assertEquals("10", mapWithOutMonthChange._1.get("suwcount"))
    Assert.assertEquals("0", mapWithMonthChange._1.get("suwcount"))
    Assert.assertEquals("0", mapWithYearChange._1.get("suwcount"))
    Assert.assertEquals("0", mapWithMonthAndYearChange._1.get("suwcount"))

  }

  @Test
  def itShouldTestUpdateGradeEntityMethod(): Unit = {
    val ONE: String = "1"
    val gwce = GradeWiseConsolidatedEntity("awce", "27", "0", "0", "0", "0")
    var upd_gwce = new GradeWiseConsolidationUtils().updateGradeWiseConsolidated(gwce, "0")
    Assert.assertEquals(ONE, upd_gwce.normal)
    Assert.assertEquals(ONE, upd_gwce.total)
    upd_gwce = new GradeWiseConsolidationUtils().updateGradeWiseConsolidated(gwce, "1")
    Assert.assertEquals(ONE, upd_gwce.muw)
    Assert.assertEquals(ONE, upd_gwce.total)
    upd_gwce = new GradeWiseConsolidationUtils().updateGradeWiseConsolidated(gwce, "2")
    Assert.assertEquals(ONE, upd_gwce.suw)
    Assert.assertEquals(ONE, upd_gwce.total)
  }

  @Test
  def itShouldTestUpdateMonthEntityMethod(): Unit = {
    val ZERO: String = "0"
    val ONE: String = "1"
    val mwce = MonthWiseConsolidatedEntity("awce", "27", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "03", "17")
    var upd_mwce = new MonthWiseConsolidationUtils().updateMonthWiseConsolidated(mwce, "01", "17", "0")
    Assert.assertEquals(ZERO, upd_mwce.jan)
    upd_mwce = new MonthWiseConsolidationUtils().updateMonthWiseConsolidated(mwce, "01", "17", "1")
    Assert.assertEquals(ONE, upd_mwce.jan)
    upd_mwce = new MonthWiseConsolidationUtils().updateMonthWiseConsolidated(mwce, "01", "17", "2")
    Assert.assertEquals(ONE, upd_mwce.jan)

    upd_mwce = new MonthWiseConsolidationUtils().updateMonthWiseConsolidated(mwce, "02", "17", "0")
    Assert.assertEquals(ZERO, upd_mwce.feb)
    upd_mwce = new MonthWiseConsolidationUtils().updateMonthWiseConsolidated(mwce, "02", "17", "1")
    Assert.assertEquals(ONE, upd_mwce.feb)
    upd_mwce = new MonthWiseConsolidationUtils().updateMonthWiseConsolidated(mwce, "02", "17", "2")
    Assert.assertEquals(ONE, upd_mwce.feb)

    upd_mwce = new MonthWiseConsolidationUtils().updateMonthWiseConsolidated(mwce, "03", "17", "0")
    Assert.assertEquals(ZERO, upd_mwce.mar)
    upd_mwce = new MonthWiseConsolidationUtils().updateMonthWiseConsolidated(mwce, "03", "17", "1")
    Assert.assertEquals(ONE, upd_mwce.mar)
    upd_mwce = new MonthWiseConsolidationUtils().updateMonthWiseConsolidated(mwce, "03", "17", "2")
    Assert.assertEquals(ONE, upd_mwce.mar)

    upd_mwce = new MonthWiseConsolidationUtils().updateMonthWiseConsolidated(mwce, "04", "17", "0")
    Assert.assertEquals(ZERO, upd_mwce.apr)
    upd_mwce = new MonthWiseConsolidationUtils().updateMonthWiseConsolidated(mwce, "04", "17", "1")
    Assert.assertEquals(ONE, upd_mwce.apr)
    upd_mwce = new MonthWiseConsolidationUtils().updateMonthWiseConsolidated(mwce, "04", "17", "2")
    Assert.assertEquals(ONE, upd_mwce.apr)

    upd_mwce = new MonthWiseConsolidationUtils().updateMonthWiseConsolidated(mwce, "05", "17", "0")
    Assert.assertEquals(ZERO, upd_mwce.may)
    upd_mwce = new MonthWiseConsolidationUtils().updateMonthWiseConsolidated(mwce, "05", "17", "1")
    Assert.assertEquals(ONE, upd_mwce.may)
    upd_mwce = new MonthWiseConsolidationUtils().updateMonthWiseConsolidated(mwce, "05", "17", "2")
    Assert.assertEquals(ONE, upd_mwce.may)

    upd_mwce = new MonthWiseConsolidationUtils().updateMonthWiseConsolidated(mwce, "06", "17", "0")
    Assert.assertEquals(ZERO, upd_mwce.jun)
    upd_mwce = new MonthWiseConsolidationUtils().updateMonthWiseConsolidated(mwce, "06", "17", "1")
    Assert.assertEquals(ONE, upd_mwce.jun)
    upd_mwce = new MonthWiseConsolidationUtils().updateMonthWiseConsolidated(mwce, "06", "17", "2")
    Assert.assertEquals(ONE, upd_mwce.jun)

    upd_mwce = new MonthWiseConsolidationUtils().updateMonthWiseConsolidated(mwce, "07", "17", "0")
    Assert.assertEquals(ZERO, upd_mwce.jul)
    upd_mwce = new MonthWiseConsolidationUtils().updateMonthWiseConsolidated(mwce, "07", "17", "1")
    Assert.assertEquals(ONE, upd_mwce.jul)
    upd_mwce = new MonthWiseConsolidationUtils().updateMonthWiseConsolidated(mwce, "07", "17", "2")
    Assert.assertEquals(ONE, upd_mwce.jul)

    upd_mwce = new MonthWiseConsolidationUtils().updateMonthWiseConsolidated(mwce, "08", "17", "0")
    Assert.assertEquals(ZERO, upd_mwce.aug)
    upd_mwce = new MonthWiseConsolidationUtils().updateMonthWiseConsolidated(mwce, "08", "17", "1")
    Assert.assertEquals(ONE, upd_mwce.aug)
    upd_mwce = new MonthWiseConsolidationUtils().updateMonthWiseConsolidated(mwce, "08", "17", "2")
    Assert.assertEquals(ONE, upd_mwce.aug)

    upd_mwce = new MonthWiseConsolidationUtils().updateMonthWiseConsolidated(mwce, "09", "17", "0")
    Assert.assertEquals(ZERO, upd_mwce.sep)
    upd_mwce = new MonthWiseConsolidationUtils().updateMonthWiseConsolidated(mwce, "09", "17", "1")
    Assert.assertEquals(ONE, upd_mwce.sep)
    upd_mwce = new MonthWiseConsolidationUtils().updateMonthWiseConsolidated(mwce, "09", "17", "2")
    Assert.assertEquals(ONE, upd_mwce.sep)

    upd_mwce = new MonthWiseConsolidationUtils().updateMonthWiseConsolidated(mwce, "10", "17", "0")
    Assert.assertEquals(ZERO, upd_mwce.oct)
    upd_mwce = new MonthWiseConsolidationUtils().updateMonthWiseConsolidated(mwce, "10", "17", "1")
    Assert.assertEquals(ONE, upd_mwce.oct)
    upd_mwce = new MonthWiseConsolidationUtils().updateMonthWiseConsolidated(mwce, "10", "17", "2")
    Assert.assertEquals(ONE, upd_mwce.oct)

    upd_mwce = new MonthWiseConsolidationUtils().updateMonthWiseConsolidated(mwce, "11", "17", "0")
    Assert.assertEquals(ZERO, upd_mwce.nov)
    upd_mwce = new MonthWiseConsolidationUtils().updateMonthWiseConsolidated(mwce, "11", "17", "1")
    Assert.assertEquals(ONE, upd_mwce.nov)
    upd_mwce = new MonthWiseConsolidationUtils().updateMonthWiseConsolidated(mwce, "11", "17", "2")
    Assert.assertEquals(ONE, upd_mwce.nov)

    upd_mwce = new MonthWiseConsolidationUtils().updateMonthWiseConsolidated(mwce, "12", "17", "0")
    Assert.assertEquals(ZERO, upd_mwce.dec)
    upd_mwce = new MonthWiseConsolidationUtils().updateMonthWiseConsolidated(mwce, "12", "17", "1")
    Assert.assertEquals(ONE, upd_mwce.dec)
    upd_mwce = new MonthWiseConsolidationUtils().updateMonthWiseConsolidated(mwce, "12", "17", "2")
    Assert.assertEquals(ONE, upd_mwce.dec)
  }

  @Test
  def itShouldTestUpdateGenderEntityMethod(): Unit = {
    val ZERO: String = "0"
    val ONE: String = "1"
    val gwce = GenderWiseConsolidatedEntity("awce", "27", "0", "0")
    var upd_gwce = new GenderWiseConsolidationUtils().updateGenderWiseConsolidated(gwce, "M", "0")
    Assert.assertEquals(ZERO, upd_gwce.male)
    upd_gwce = new GenderWiseConsolidationUtils().updateGenderWiseConsolidated(gwce, "M", "1")
    Assert.assertEquals(ONE, upd_gwce.male)
    upd_gwce = new GenderWiseConsolidationUtils().updateGenderWiseConsolidated(gwce, "M", "2")
    Assert.assertEquals(ONE, upd_gwce.male)
    upd_gwce = new GenderWiseConsolidationUtils().updateGenderWiseConsolidated(gwce, "F", "0")
    Assert.assertEquals(ZERO, upd_gwce.female)
    upd_gwce = new GenderWiseConsolidationUtils().updateGenderWiseConsolidated(gwce, "F", "1")
    Assert.assertEquals(ONE, upd_gwce.female)
    upd_gwce = new GenderWiseConsolidationUtils().updateGenderWiseConsolidated(gwce, "F", "2")
    Assert.assertEquals(ONE, upd_gwce.female)
  }

  @Test
  def itShouldTestUpdateAgeEntityMethod(): Unit = {
    val ZERO: String = "0"
    val ONE: String = "1"
    val awce = AgeWiseConsolidatedEntity("awce", "27", "0", "0", "0", "0", "0", "0")
    var upd_awce = new AgeWiseConsolidationUtils().updateAgeWiseConsolidated(awce, "1", "0")
    Assert.assertEquals(ZERO, upd_awce.zeroToOne)
    upd_awce = new AgeWiseConsolidationUtils().updateAgeWiseConsolidated(awce, "1", "1")
    Assert.assertEquals(ONE, upd_awce.zeroToOne)
    upd_awce = new AgeWiseConsolidationUtils().updateAgeWiseConsolidated(awce, "1", "2")
    Assert.assertEquals(ONE, upd_awce.zeroToOne)
    upd_awce = new AgeWiseConsolidationUtils().updateAgeWiseConsolidated(awce, "22", "0")
    Assert.assertEquals(ZERO, upd_awce.oneToTwo)
    upd_awce = new AgeWiseConsolidationUtils().updateAgeWiseConsolidated(awce, "22", "1")
    Assert.assertEquals(ONE, upd_awce.oneToTwo)
    upd_awce = new AgeWiseConsolidationUtils().updateAgeWiseConsolidated(awce, "22", "2")
    Assert.assertEquals(ONE, upd_awce.oneToTwo)
    upd_awce = new AgeWiseConsolidationUtils().updateAgeWiseConsolidated(awce, "32", "0")
    Assert.assertEquals(ZERO, upd_awce.twoToThree)
    upd_awce = new AgeWiseConsolidationUtils().updateAgeWiseConsolidated(awce, "32", "1")
    Assert.assertEquals(ONE, upd_awce.twoToThree)
    upd_awce = new AgeWiseConsolidationUtils().updateAgeWiseConsolidated(awce, "32", "2")
    Assert.assertEquals(ONE, upd_awce.twoToThree)
    upd_awce = new AgeWiseConsolidationUtils().updateAgeWiseConsolidated(awce, "45", "0")
    Assert.assertEquals(ZERO, upd_awce.threeToFour)
    upd_awce = new AgeWiseConsolidationUtils().updateAgeWiseConsolidated(awce, "45", "1")
    Assert.assertEquals(ONE, upd_awce.threeToFour)
    upd_awce = new AgeWiseConsolidationUtils().updateAgeWiseConsolidated(awce, "45", "2")
    Assert.assertEquals(ONE, upd_awce.threeToFour)
  }


}

