package com.shihang.fuck.rpc.spring.cloud.zookeeper.serializer;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.details.InstanceSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.nio.charset.Charset;

public class FuckInstanceSerializer<T> implements InstanceSerializer<T> {

    private final ObjectMapper objectMapper;

    private final Class<T> payloadClass;

    private final JavaType type;

    // default charset utf-8
    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    // .net maybe start with 'http://' or '//'， we only find last '/'
    private static final char DEFAULT_PROTOCOL_SEPARATOR = '/';

    private static final Logger logger = LoggerFactory.getLogger(FuckInstanceSerializer.class);

    public FuckInstanceSerializer(Class<T> payloadClass) {
        this.payloadClass = payloadClass;
        this.objectMapper = new ObjectMapper();
        this.type = objectMapper.getTypeFactory().constructType(ServiceInstance.class);
    }

    @Override
    public byte[] serialize(ServiceInstance<T> serviceInstance) throws Exception {
        if (serviceInstance == null) {
            throw new IllegalArgumentException("ServiceInstance");
        }

        return String.format("%s:%d", serviceInstance.getAddress(), serviceInstance.getPort())
                .getBytes(DEFAULT_CHARSET);
    }

    @Override
    public ServiceInstance<T> deserialize(byte[] bytes) throws Exception {
        if (bytes == null) {
            throw new IllegalArgumentException("ServiceInstance bytes");
        }

        String host = new String(bytes, DEFAULT_CHARSET);
        if (logger.isInfoEnabled()) {
            logger.info("host from zookeeper: {}", host);
        }

        if (!StringUtils.hasText(host)) {
            throw new IllegalArgumentException("Host");
        }

        final int separatorIdx = host.lastIndexOf(DEFAULT_PROTOCOL_SEPARATOR);
        if (separatorIdx >= 0) {
            host = host.substring(separatorIdx + 1);
        }
        final String[] hostVals = StringUtils.split(host, ":");
        ServiceInstance rawServiceInstance = ServiceInstance.builder().address(hostVals[0]).port(hostVals.length > 1 ? Integer.valueOf(hostVals[1]) : 8080).build();
        payloadClass.cast(rawServiceInstance.getPayload());

        return (ServiceInstance<T>) rawServiceInstance;
    }
}
