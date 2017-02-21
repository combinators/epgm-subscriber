def convertToGroup(age: String):Int = age.toInt % 12 match {
  case 0 => age.toInt/12
  case _ => age.toInt/12 + 1
}

convertToGroup("1")