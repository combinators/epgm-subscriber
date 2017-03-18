package org.sthapana.aggregation.dataobjects

/**
  * Created by chocoklate on 14/2/17.
  */
case class UpdateEntity(stateCode:String,districtCode:String,projectCode:String,
                        sectorCode:String,aanganwadiCode:String,currentGrade:String,
                        gender:String,currentAge:String,currentMonth:String,currentYear:String)

case class GradeWiseConsolidatedEntity(doctype:String, code:String,suw:String,muw:String,normal:String,total:String)

case class GenderWiseConsolidatedEntity(doctype:String, code:String,male:String,female:String)

case class AgeWiseConsolidatedEntity(doctype:String, code:String,zeroToOne:String,oneToTwo:String,twoToThree:String,
                                     threeToFour:String,fourToFive:String,fiveToSix:String)

case class MonthWiseConsolidatedEntity(doctype:String, code:String,jan:String,feb:String,mar:String,
                                       apr:String,may:String,jun:String,
                                       jul:String,aug:String,sep:String,
                                       oct:String,nov:String,dec:String,
                                       currmonth:String,curryear:String)

case class TyrionEntity(doctype:String, code:String, suwcount:String,
                        muwcount:String, normalcount:String, totalcount:String,
                        malecount:String, femalecount:String, zerotoonecount:String,
                        onetotwocount:String, twotothreecount:String, threetofourcount:String,
                        fourtofivecount:String, fivetosixcount:String, januarycount:String,
                        februarycount:String, marchcount:String, aprilcount:String,
                        maycount:String, junecount:String, julycount:String, augustcount:String,
                        septembercount:String, octobercount:String, novembercount:String,
                        decembercount:String, currentmonth:String, currentyear:String)

case class MasterChildDataEntity(doctype:String, dayofbirth:String,monthofbirth:String,yearofbirth:String,
                                 address:String,sex:String,recordnumber:String,childcode:String,
                                 category:String,name:String,fathername:String,
                                 aanganwadicode:String)

case class MasterAanganwadiDataEntity(doctype:String, aanganwadicode:String,
                                      aanganwadiname:String)

case class MasterSectorDataEntity(doctype:String, sectorcode:String,
                                  sectorname:String)

case class MasterProjectDataEntity(doctype:String, projectcode:String,
                                   projectname:String)

case class MasterDistrictDataEntity(doctype:String, districtcode:String,
                                    districtname:String)

case class MasterStateDataEntity(doctype:String, statecode:String,
                                 statename:String)