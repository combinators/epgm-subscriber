package org.sthapana.epgmsubscriber

case class RecordSchema(schema: Schema) {


  def apply(rawRecord: String): Option[Record] = {
    if (validateRecord(rawRecord))
      Some(applySchemaNew(rawRecord, schema, Map(), ("", 0)))
    else Option.empty
  }

  def validateRecord(record: String): Boolean = {
    println("validating rawdata length : ", record.length);
    println("validating rawdata length : ", record.substring(42));
    schema.foldLeft(0)((acc, b) => acc + b._2) == record.length
  }

  private[this] def applySchemaNew(rawRecord: String, s: Schema, acc: Record, previous: (String, Int)): Record =
    if (rawRecord.isEmpty) acc
    else {
      val (l, r) = rawRecord.splitAt(s.head._2)
      if (previous._2 < 11) {
        applySchemaNew(r, s.tail, acc + (s.head._1 -> (previous._1 ++ l)), (previous._1 ++ l, previous._2 + s.head._2))
      }
      else {
        applySchemaNew(r, s.tail, acc + (s.head._1 -> l), ("", previous._2 + s.head._2))
      }

    }
}
