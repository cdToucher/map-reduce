package me.codebase.spark.demo

import com.google.common.io.Resources
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
  * Created by chendong on 2017/10/12.
  */
object ClusterSimpleApp {

  private var conf: SparkConf = _
  private val appName = "Cluster Simple Application"
  private val master = "spark://10.1.51.237:7077"

  def  main(args: Array[String]): Unit = {
    val path = Resources.getResource("abc").getPath
    conf = new SparkConf().setJars(Seq())
    val ss = SparkSession.builder().appName(appName).master("local[1]").config(conf).getOrCreate()
    val ds = ss.read.textFile(path)
    // ds.foreach(a => print(a))
    println(ds.columns)
    println(ds.filter(_.contains("a")).count())
    ds.foreach(print(_))
  }
}
