package org.sthapana.epgmsubscriber

import scala.annotation.tailrec

case class RecordSchema(schema: Schema) {


  def apply(rawRecord: String):Option[Record] = {
    if(validateRecord(rawRecord))
      Some(applySchemaNew(rawRecord,schema,List(),("",0)))
    else Option.empty
  }

  @tailrec
  private [this] def applySchema(rawRecord: String,s:Schema,acc:Record):Record =
    if(rawRecord.isEmpty) acc
    else {
      val (l,r) = rawRecord.splitAt(s.head._2)
      applySchema(r,s.tail, (s.head._1,l) :: acc )
    }

  private [this] def applySchemaNew(rawRecord: String,s:Schema,acc:Record,previous:(String,Int)):Record =
    if(rawRecord.isEmpty) acc
    else {
      val (l,r) = rawRecord.splitAt(s.head._2)
      if(previous._2<11)
      applySchemaNew(r,s.tail, (s.head._1,previous._1 ++l) :: acc ,(previous._1 ++l,previous._2+s.head._2))
      else applySchemaNew(r,s.tail, (s.head._1,l) :: acc ,("",previous._2+s.head._2))
    }

  def validateRecord(record: String):Boolean = schema.foldLeft(0)((acc, b) => acc + b._2) == record.length
}
