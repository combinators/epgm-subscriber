package org.sthapana.epgmsubscriber

import scala.annotation.tailrec

case class RecordSchema(schema: Schema) {


  def apply(rawRecord: String):Option[Record] = {
    if(test(rawRecord))
      Some(applySchema(rawRecord,schema,List()))
    else Option.empty
  }

  @tailrec
  private [this] def applySchema(rawRecord: String,s:Schema,acc:Record):Record =
    if(rawRecord.isEmpty) acc
    else {
      val (l,r) = rawRecord.splitAt(s.head._2)
      applySchema(r,s.tail, (s.head._1,l) :: acc )
    }

  def test(record: String):Boolean = schema.foldLeft(0)((acc,b) => acc + b._2) == record.length
}
