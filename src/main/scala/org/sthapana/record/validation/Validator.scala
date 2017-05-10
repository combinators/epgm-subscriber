package org.sthapana.record.validation

import org.joda.time.{DateTime, Days, Months}
import org.sthapana.child.ChildRecord

object validation {

  object Validator {

    trait Malformed
    val stateWiseValidations: Map[StateCode, List[Validator]] = {
      Map(
        "27" -> List(
          sameMonth,
          dayGap(20),
          ageLimit(72)
        ),
        "52" -> List(
          sameMonth,
          dayGap(22)
        ),
        "98" -> List(
          sameMonth,
          dayGap(22)
        )
      )
    }

    object Malformed {

      case object SameMonth extends Malformed

      case object Age extends Malformed

      case object DayGap extends Malformed

    }

    type StateCode = String
    type Validator = (ChildRecord, ChildRecord) => Either[ChildRecord, (ChildRecord, Malformed)]

    def sameMonth: Validator = (c1, c2) => {
      val c1LogDate: DateTime = new DateTime(c1.year.toInt, c1.month.toInt, c1.day.toInt, c1.hours.toInt, c1.minutes.toInt)
      val c2LogDate: DateTime = new DateTime(c2.year.toInt, c2.month.toInt, c2.day.toInt, c2.hours.toInt, c2.minutes.toInt)
      if (isMonthYearSame(c1LogDate, c2LogDate))
        Right((c1, Malformed.SameMonth))
      else Left(c1)
    }

    def dayGap(gap: Int): Validator = (c1, c2) => {
      val c1LogDate: DateTime = new DateTime(c1.year.toInt, c1.month.toInt, c1.day.toInt, c1.hours.toInt, c1.minutes.toInt)
      val c2LogDate: DateTime = new DateTime(c2.year.toInt, c2.month.toInt, c2.day.toInt, c2.hours.toInt, c2.minutes.toInt)
      if (isDaysGapValid(c1LogDate, c2LogDate, gap))
        Right((c1, Malformed.DayGap))
      else Left(c1)
    }

    def isDaysGapValid(d1: DateTime, d2: DateTime, gap: Int): Boolean =
      Days.daysBetween(d1, d2).getDays >= gap

    def ageLimit(age: Int): Validator = (c1, c2) => {
      val c1LogDate: DateTime = new DateTime(c1.year.toInt, c1.month.toInt, c1.day.toInt, c1.hours.toInt, c1.minutes.toInt)
      val c2LogDate: DateTime = new DateTime(c2.year.toInt, c2.month.toInt, c2.day.toInt, c2.hours.toInt, c2.minutes.toInt)
      if (isAgeValid(c1LogDate, c2LogDate, age))
        Right((c1, Malformed.DayGap))
      else Left(c1)
    }

    def isAgeValid(d1: DateTime, d2: DateTime, age: Int): Boolean =
      Months.monthsBetween(d1, d2).getMonths >= age

    def isMonthYearSame(d1: DateTime, d2: DateTime): Boolean =
      d1.getYear == d2.getYear && d1.getMonthOfYear == d2.getMonthOfYear

    def apply(stateCode: StateCode, c1: ChildRecord, c2: ChildRecord):
    Either[ChildRecord, (ChildRecord, Malformed)] = {
      def getResult(validators: List[Validator], c1: ChildRecord, c2: ChildRecord): Either[ChildRecord, (ChildRecord, Malformed)] = validators match {
        case List() => Left(c1)
        case x :: xs => x(c1, c2) match {
          case Left(c) => getResult(xs, c1, c2)
          case r@Right(_) => r
        }
      }
      getResult(stateWiseValidations.get(stateCode).get, c1, c2)
    }
  }

}
