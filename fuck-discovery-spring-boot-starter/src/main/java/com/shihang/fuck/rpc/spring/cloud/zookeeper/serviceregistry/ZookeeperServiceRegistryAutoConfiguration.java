package com.shihang.fuck.rpc.spring.cloud.zookeeper.serviceregistry;

import com.shihang.fuck.rpc.spring.cloud.zookeeper.serializer.FuckInstanceSerializer;
import org.apache.curator.x.discovery.details.InstanceSerializer;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.cloud.client.serviceregistry.AutoServiceRegistrationProperties;
import org.springframework.cloud.zookeeper.ConditionalOnZookeeperEnabled;
import org.springframework.cloud.zookeeper.discovery.ZookeeperInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@Configuration
@ConditionalOnZookeeperEnabled
@AutoConfigureBefore(org.springframework.cloud.zookeeper.serviceregistry.ZookeeperServiceRegistryAutoConfiguration.class)
public class ZookeeperServiceRegistryAutoConfiguration {

    @Primary
    @Bean
    public InstanceSerializer<ZookeeperInstance> instanceSerializer() {
        return new FuckInstanceSerializer<>(ZookeeperInstance.class);
    }

    @Primary
    @Bean
    public AutoServiceRegistrationProperties autoServiceRegistrationProperties() {
        return new AutoServiceRegistrationProperties();
    }
}
