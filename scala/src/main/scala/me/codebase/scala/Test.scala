package me.codebase.scala

import java.net.InetAddress


/**
  * Created by chendong on 2017/8/14.
  */
object Test {

  def main(args: Array[String]): Unit = {
    val ip = InetAddress.getLocalHost.getHostAddress
    print(ip)
  }
}
