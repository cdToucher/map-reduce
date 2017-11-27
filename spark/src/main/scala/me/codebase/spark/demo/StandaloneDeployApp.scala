package me.codebase.spark.demo


import java.io.File
import java.util.Properties

import me.codebase.spark.Logging
import me.codebase.spark.constant.SysProperty
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

import scala.io.Source

/**
  * Created by chendong on 2017/11/22.
  *
  * standalone client mode demo
  */
private[demo] object StandaloneDeployApp extends SysProperty with Logging {

  def main(args: Array[String]): Unit = {
    if (args.length > 0)
      System.setProperty(APP_HOME, args(0))

    var dfr = getSparkSession.read.format("org.apache.spark.sql.cassandra")
      .options(Map("keyspace" -> "uem_octopus", "table" -> "trend"))
      .load()
    val count = dfr.select("appid").count()
    logInfo(s"table trend counts : $count")
    logInfo(s"table trend counts : $count")
    logInfo(s"table trend counts : $count")
    //    session.close()
  }

  def getSparkSession: SparkSession = {
    val conf = getSparkConfig()
    SparkSession.builder().config(conf).getOrCreate()
  }


  def getConfigProperties = {
    val stream = getClass.getClassLoader.getResourceAsStream("env.properties")
    var bufferedSource = Source fromInputStream(stream, "utf-8")
    var properties = new Properties
    properties load bufferedSource.reader
    properties
  }

  def getSparkConfig(): SparkConf = {
    var conf: SparkConf = new SparkConf()
    import scala.collection.JavaConverters._
    conf.setAll(getConfigProperties.asScala)
    if (System.getProperty("app.home") != null) {
      val jarsPath = System.getProperty("app.home") + "/lib/"
      conf.setJars(new File(jarsPath).listFiles().map(_.getAbsolutePath).filter(_.endsWith(".jar")))
    } else conf.setMaster("local[1]")

    conf
  }

}
