package com.shihang.fuck.rpc.spring.boot.autoconfigure;


import com.shihang.fuck.rpc.spring.mapper.FuckMapperRegistrar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableConfigurationProperties(FuckProperties.class)
@Import(FuckMapperRegistrar.class)
public class FuckAutoConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(FuckAutoConfiguration.class);

    @Autowired
    private  FuckProperties rpcProperties;

}