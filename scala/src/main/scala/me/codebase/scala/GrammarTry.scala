package me.codebase.scala

/**
  * Created by chendong on 2016/10/28
  */
object GrammarTry {

  def main(args: Array[String]): Unit = {

    type tesdsds = scala.collection.mutable.StringBuilder
    var sb: tesdsds = new StringBuilder

    print(sb.mkString("stest").contains("st"))


  }

  private def test1 = {
    Range.apply(1, 3).foreach(System.out.print)
    val r1 = Range.apply(1, 9)
    for (i <- r1) println(i)
    for (i <- 1 until 2) print(i)
  }

  private def tryTest = {
    try {
      // do something
    } catch {
      case e: Exception => e
    }
  }

  def listUseage(): Unit = {
    val list1 = List(1, 2, 3, 4, 5, 6)
    val fiter = list1.filter(i => i > 1)
    val fiter2 = list1.filter { case i => i > 1 }
    println(fiter)
    println(fiter2)
  }

  def baisc1(): ((String, Int, Int, Int), Map[String, Int], Map[Int, Map[String, Int]]) = {
    val tuple1 = ("1", 1)
    val tuple2 = ("1", 1, 2)
    val tuple3 = Tuple4("1", 1, 2, 3)

    val map1: Map[String, Int] = Map(tuple1)
    val map2: Map[String, Int] = Map(tuple1)
    val map3 = Map(1.->(map1))
    val map4 = Map(1 -> map1)

    var a = Array("1")
    var b = Array()
    var c = new Array(0)
    //    var d: Array[String] = _
    (tuple3, map1, map3) //
  }

  def useBaisc1(): Unit = {
    val (tuple3, map1, map3) = baisc1() //返回yuanzu
    println(tuple3)
    println(map1)
    println(map3)
    val aa = baisc1()
    println(aa._1)
    println(aa._2)
    println(aa._3)
  }

  def javaConverter(): Unit = {

    val map = new java.util.HashMap[String, String]()
    import scala.collection.JavaConverters._
    val map2: scala.collection.mutable.Map[String, String] = map.asScala
    val map3 = map.asScala // 推荐使用类型推断，节省代码量
    map2("a") = "a"
    map2("1")
    println(map.getClass)
    println(map)
    println(map2)

    //这里的with完成了对DatabaseRepository依赖的注入
    //        new UserService with DatabaseRepository
  }

  case class Car(id: Int, name: String)


  //匿名函数写法
  var createNewColumn1 = (name: String) => s"""{"newColumnName":"$name"}"""

  def createNewColumn(name: String, age: Int = 12): String = {
    s"""{"newColumnName":"$name"}"""
  }

  def createNewColumn2: (String) => String = (name) => {
    s"""{"newColumnName":"$name"}"""
  }

  def sdss = {

  }


}
