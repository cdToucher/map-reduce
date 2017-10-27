package me.codebase.scala.migrate

/**
  * Created by chendong on 2017/10/26.
  */
object MigrateData {

  def main(args: Array[String]): Unit = {
    val session = Cassandra.session
    session.execute("").one()

  }

  val sql: String = "select * from tableName"


}
