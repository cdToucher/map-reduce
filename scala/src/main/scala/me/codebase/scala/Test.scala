package me.codebase.scala


/**
  * Created by chendong on 2017/8/14.
  */
object Test {

  def *(n: Int): String = {
    val buf = new StringBuilder
    for (i <- 0 until n) buf append toString
    buf.toString
  }

  def main(args: Array[String]): Unit = {
    val base = "!@#"
    print(base * 2)
  }
}
