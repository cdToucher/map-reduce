package me.codebase.scala.migrate

/**
  * Created by chendong on 2017/10/26.
  */

trait Config {
  val host: String = "localhost"
  val port: Int = 0
  val clusterName: String = "Test CLuster"
  val keyspace: String = "uem_octopus"
}

case class CassandraConfig(override val host: String = "localhost"
                           , override val port: Int = 9042
                           , override val keyspace: String = "system") extends Config

object DefaultConfig {
  val cassandra = CassandraConfig("10.1.51.236", 9042, "uem_octopus_r11")
}
