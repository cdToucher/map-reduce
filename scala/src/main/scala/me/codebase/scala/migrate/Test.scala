package me.codebase.scala.migrate

/**
  * Created by chendong on 2017/10/26.
  */
object Test {

  val appHome:String = "test"

  def main(args: Array[String]): Unit = {
    printlnHelp
  }

  private def printlnHelp(): Unit = {
    println(
      s"""$appHome/bin/uem-compute-migrate-7th.sh [OPTION]
         | Usage: [OPTION]: [OPTION] add the para
         | all     : clean ALL tables
         | -range [fromId] [toId]
         | -id [id list]
         | -detail : ID list further details
         | -h      :	ALL HELP
         | --help  :	ALL HELP
         |
       | properties: '$appHome/conf/uem.properties' and '$appHome/conf/common.properties'
         |
       | e.g ($appHome run)
         | ./bin/uem-compute-migrate-7th.sh all
         | clean ALL tables
         | ./bin/uem-compute-migrate-7th.sh -range 0 3
         | clean category 0,1,2,3
         | ./bin/uem-compute-migrate-7th.sh -id 0 10
         | clean category 0 and 10
      """.stripMargin)
  }

}