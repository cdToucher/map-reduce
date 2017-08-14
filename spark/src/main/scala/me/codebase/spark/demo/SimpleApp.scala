package me.codebase.spark.demo

import com.google.common.io.Resources
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by chendong on 2017/4/17.
  */
object SimpleApp {

  def main(args: Array[String]): Unit = {
    val path = Resources.getResource("abc.txt").getPath

    val sparkSession = SparkSession.builder().appName("Simple Application").master("local").getOrCreate()
    val sparkContext = sparkSession.sparkContext
    val ds = sparkSession.read.textFile(path)

  }

  private def oldType = {
    val path = Resources.getResource("abc.txt").getPath
    System.setProperty("hadoop.home.dir", "F:\\Program Files\\hadoop")

    // Should be some file on your system
    //spark://10.1.51.238:7077
    val conf = new SparkConf().setMaster("local").setAppName("Simple Application")
    val sc = new SparkContext(conf)

    sc.parallelize(Seq(1, 2, 3, 4, 5, 6, 7, 8), 2)
    val data = sc.textFile(path, 2)
    val logData = data.cache() // Put small and hot data into cache to save the usage of memory.
    val numAs = logData.filter(line => line.contains("a")).count()
    val numBs = logData.filter(line => line.contains("b")).count()
    val countA = logData.map(_.length).reduce(_ + _)
    //    val countA2 = logData.flatMap(_.chars())
    println(s"Lines with a: $numAs, Lines with b: $numBs ,numbers a : $countA")
    sc.stop()
  }
}

