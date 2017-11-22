package me.codebase.scala.migrate

import com.datastax.driver.core.{Cluster, Session}

/**
  * Created by chendong on 2017/10/26.
  */

object Cassandra {

  lazy val cluster: Cluster = CassandraCluster(DefaultConfig.cassandra)

  lazy val session: Session = this.cluster.connect(DefaultConfig.cassandra.keyspace)

  private object CassandraCluster {
    def apply(config: CassandraConfig): Cluster = Cluster
      .builder()
      .addContactPoint(config.host)
      .withClusterName(config.clusterName).build()
  }

}
