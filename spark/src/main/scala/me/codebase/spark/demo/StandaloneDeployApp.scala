package me.codebase.spark.demo


import java.util.Properties

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.spark_project.guava.io.Resources

import scala.io.Source

/**
  * Created by chendong on 2017/11/22.
  *
  * standalone client mode demo
  */
private[demo] object StandaloneDeployApp {

  def main(args: Array[String]): Unit = {
    var session = SparkSession.builder().config(getSparkConfig()).getOrCreate()
    var dfr = session.read.format("org.apache.spark.sql.cassandra")
      .option("", "").load()
    session.sql("")
  }


  def getConfigProperties = {
    var bufferedSource = Source.fromFile(Resources.getResource("env.properties").getPath, "utf-8")
    var properties = new Properties
    properties.load(bufferedSource.reader())
    properties
  }

  def getSparkConfig(): SparkConf = {

    var config: SparkConf = new SparkConf()
    //    getConfigProperties.forEach((key, value) => config.set(key, value))

    config.setJars(Seq())
      .setAppName("TEST")
      .setMaster("")
    config
  }

}
