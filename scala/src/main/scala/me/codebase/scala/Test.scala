package me.codebase.scala


/**
  * Created by chendong on 2017/8/14.
  */
object Test {


  def main(args: Array[String]): Unit = {
    val objA1 = ClassA("world")
    println(objA1)
    saveObject("testA.dat", objA1)

    val objA2 = loadObject("testA.dat").asInstanceOf[ClassA]
    println(objA2)

    val objB1 = ClassB("world")
    println(objB1)

    saveObject("testB.dat", objB1)
    val objB2 = loadObject("testB.dat").asInstanceOf[ClassB]
    println(objB2)


  }

  case class ClassA(name: String) {

    @transient private lazy val msg = {
      println("Good morning.")
      "Hello, " + name
    }

    override def toString: String = msg

  }

  case class ClassB(name: String) {

    private lazy val msg = {
      println("Good morning.")
      "Hello, " + name
    }

    override def toString: String = msg

  }

  import java.io.{FileInputStream, FileOutputStream, ObjectInputStream, ObjectOutputStream}

  def saveObject(fname: String, obj: AnyRef) {
    val fop = new FileOutputStream(fname)
    val oop = new ObjectOutputStream(fop)
    try {
      oop.writeObject(obj)
    } finally {
      oop.close()
    }
  }

  def loadObject(fname: String): AnyRef = {
    val fip = new FileInputStream(fname)
    val oip = new ObjectInputStream(fip)
    try {
      oip.readObject()
    } finally {
      oip.close()
    }
  }


  private def printlnHelp(): Unit = {
    val appHome: String = "test"

    println(
      s"""$appHome/bin/uem-compute-migrate-7th.sh [OPTION]
         | Usage: [OPTION]: [OPTION] add the para
         | all     : clean ALL tables
         | -range [fromId] [toId]
         | -id [id list]
         | -detail : ID list further details
         | -h      :	ALL HELP
         | --help  :	ALL HELP
         |
       | properties: '$appHome/conf/uem.properties' and '$appHome/conf/common.properties'
         |
       | e.g ($appHome run)
         | ./bin/uem-compute-migrate-7th.sh all
         | clean ALL tables
         | ./bin/uem-compute-migrate-7th.sh -range 0 3
         | clean category 0,1,2,3
         | ./bin/uem-compute-migrate-7th.sh -id 0 10
         | clean category 0 and 10
      """.stripMargin)
  }

}
