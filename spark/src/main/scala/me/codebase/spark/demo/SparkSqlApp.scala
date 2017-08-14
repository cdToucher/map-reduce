package me.codebase.spark.demo

import com.google.common.io.Resources
import org.apache.spark.sql
import org.apache.spark.sql.{Dataset, Row, SparkSession}

/**
  * Created by chendong on 2017/5/18.
  */
object SparkSqlApp {

  def main(args: Array[String]): Unit = {
    val path = Resources.getResource("abc").getPath

    val sparkSession = SparkSession.builder().master("local").appName("Simple Application").getOrCreate()

    val ds: Dataset[Row] = sparkSession.read.text(path)
    val ds2: sql.DataFrame = sparkSession.read.text(path)
    val dz: Dataset[String] = sparkSession.read.textFile(path)

//    sparkSession.sparkContext.

    ds.show()
    val alias = ds.alias("ttt")
    ds.createOrReplaceTempView("temps")

    //    sparkSession.sql("SET -v").show(numRows = 200, truncate = false)

    val df = sparkSession.sql("select * from temps")
    df.foreach(row => println(row))
  }

}
