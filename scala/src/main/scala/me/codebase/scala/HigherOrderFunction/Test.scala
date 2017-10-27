package me.codebase.scala.HigherOrderFunction

/**
  * Created by chendong on 2017/9/14.
  */
object Test {

  def main(args: Array[String]): Unit = {
    func1(func2, "23")
  }

  def func1(f: Int => String, name: String): Unit = {
    println(f(1) + name)
  }

  def func2(int: Int): String = {
    int.toString
  }


  def func2323(f: (Int, String) => String, name: String): Unit = {
    println(f(1, "@") + name)
  }
}
