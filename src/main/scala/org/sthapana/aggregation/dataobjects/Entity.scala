package org.sthapana.aggregation.dataobjects

/**
  * Created by chocoklate on 14/2/17.
  */
case class UpdateEntity(stateCode:String,districtCode:String,projectCode:String,
                        sectorCode:String,aanganwadiCode:String,currentGrade:String,
                        previousGrade:String,gender:String,currentAge:String,
                        previousAge:String,currentMonth:String,currentYear:String)

case class GradeWiseConsolidatedEntity(code:String,suw:String,muw:String,normal:String,total:String)

case class GenderWiseConsolidatedEntity(code:String,male:String,female:String)

case class AgeWiseConsolidatedEntity(code:String,zeroToOne:String,oneToTwo:String,twoToThree:String,
                                     threeToFour:String,fourToFive:String,fiveToSix:String)

case class MonthWiseConsolidatedEntity(code:String,jan:String,feb:String,mar:String,
                                       apr:String,may:String,jun:String,
                                       jul:String,aug:String,sep:String,
                                       oct:String,nov:String,dec:String,
                                       currmonth:String,curryear:String)