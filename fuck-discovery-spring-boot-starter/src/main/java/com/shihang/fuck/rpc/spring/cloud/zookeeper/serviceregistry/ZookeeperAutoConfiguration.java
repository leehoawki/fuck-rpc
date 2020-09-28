package com.shihang.fuck.rpc.spring.cloud.zookeeper.serviceregistry;

import org.apache.curator.RetryPolicy;
import org.apache.curator.retry.RetryForever;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.zookeeper.ConditionalOnZookeeperEnabled;
import org.springframework.cloud.zookeeper.ZookeeperProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ConditionalOnZookeeperEnabled
@EnableConfigurationProperties
@AutoConfigureBefore(org.springframework.cloud.zookeeper.ZookeeperAutoConfiguration.class)
public class ZookeeperAutoConfiguration {

    @Bean
    @Primary
    public ZookeeperProperties zookeeperProperties() {
        return new ZookeeperProperties();
    }

    @Primary
    @Bean
    public RetryPolicy retryForever(ZookeeperProperties zookeeperProperties) {
        int sleepTimes = zookeeperProperties.getMaxSleepMs();

        // sleep times less than 1s
        if (sleepTimes < 1_000) {
            sleepTimes = 1_000;
        }

        return new RetryForever(sleepTimes);
    }
}
