package me.codebase.scala.javaside;

import com.datastax.driver.core.Cluster;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * Created by chendong on 2017/10/27.
 */
@Configuration
public class ApplicationConf {

    @Bean(name = "cassandraDev", autowire = Autowire.BY_NAME)
    @DependsOn("")
    public static Cluster getCluster(@Value("cassandra.hosts") String hosts, @Value("cassandra.port") int port) {
        return Cluster.builder().addContactPoints(hosts.split(","))
                .withPort(port)
                .withClusterName("Test")
                .build();
    }

}
