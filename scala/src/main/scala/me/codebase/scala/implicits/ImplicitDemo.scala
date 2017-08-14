package me.codebase.scala.implicits

import ch.qos.logback.classic.Level

/**
  * Created by chendong on 2017/5/5.
  *
  * 隐式转换 可以作为方便使用的 装饰器
  */
object ImplicitDemo {

  trait Log {
    def warning(message: String)

    def error(message: String)
  }

  case class Logger() {
    def log(level: Level, message: String) {
      println(s"$level $message")
    }
  }

  implicit final class LogAdapter(logger: Logger) extends Log { //对 class 做隐式转换 （装饰器）
    def warning(message: String) {
      logger.log(Level.WARN, message)
    }

    def error(message: String) {
      logger.log(Level.ERROR, message)
    }
  }

//    implicit def LoggerToLogAdapter(logger: Logger): LogAdapter = new LogAdapter(logger)

  def main(args: Array[String]): Unit = {
    val log: Log = Logger()

    log.error("wewewewe")

    val log2 = Logger() // 类型 没声名，没有触发隐式转化

    log2.log(Level.ERROR, "")
  }
}
