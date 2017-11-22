package me.codebase.scala.migrate

/**
  * Created by chendong on 2017/10/26.
  */
object MigrateData {

  val tableName :String = ""

  def main(args: Array[String]): Unit = {
    val session = Cassandra.session
    session.execute("").one()

  }

  val sql: String = s"select * from $tableName".stringPrefix

  val sql1:String =
    s"""
      |dsdsd
      |sdsd
      |sd
      |sds
      |ds
      |d
      |sd
      |d
      |sd
 |      |
    """.stripMargin
}
