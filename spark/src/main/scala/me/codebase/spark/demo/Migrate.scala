package me.codebase.spark.demo

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

/**
  * Created by chendong on 2017/5/25.
  */
object Migrate {

  val driver: String = "org.apache.spark.sql.cassandra"
  val keyspace = "test"

  def initSparkSession: SparkSession = {
    val conf = new SparkConf()
      .setMaster("local")
      .setAppName("migrate")
      .set("spark.cassandra.connection.host", "10.1.51.236")
    SparkSession.builder().config(conf).getOrCreate()
  }

  val spark = initSparkSession

  implicit def convert2DF(sql: String): DataFrame = {
    try {
      spark.sql(sql)
    } catch {
      case e: Exception =>
        null
    }
  }

  def main(args: Array[String]): Unit = {

    val keyspace = "test"
    val tableName = "kv"

    spark.read.format(driver)
      .options(Map("keyspace" -> keyspace, "table" -> tableName))
      .load().createOrReplaceTempView("kv")

    spark.udf.register("makeColumn", (name: String) => s"""{"newColumnName":"$name"}""")


  }


}

