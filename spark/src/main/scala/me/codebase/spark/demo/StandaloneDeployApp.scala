package me.codebase.spark.demo


import java.io.File
import java.util.Properties

import me.codebase.spark.constant.SysProperty
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

import scala.io.Source

/**
  * Created by chendong on 2017/11/22.
  *
  * standalone client mode demo
  */
private[demo] object StandaloneDeployApp extends SysProperty {

  def main(args: Array[String]): Unit = {

    System.setProperty(APP_HOME, args(0))

    val conf = getSparkConfig()
    var session = SparkSession.builder().config(conf).getOrCreate()
    var dfr = session.read.format("org.apache.spark.sql.cassandra")
      .options(Map("keyspace" -> "uem_octopus", "table" -> "trend"))
      .load()
    println(dfr.count())
    session.close()
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
    val jarsPath = System.getProperty("app.home") + "/lib/"
    val jars = new File(jarsPath).listFiles().map(_.getAbsolutePath).filter(_.endsWith(".jar"))
    import scala.collection.JavaConverters._
    conf.setAll(getConfigProperties.asScala)
    conf.setJars(jars)
    //      .setMaster("local[1]")
    conf
  }

}
