package com.shihang.fuck.rpc.discovery;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shihang.fuck.rpc.utils.StringUtils;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.ServiceType;
import org.apache.curator.x.discovery.details.InstanceSerializer;

public class NodeSerializer implements InstanceSerializer<Node> {
    private final ObjectMapper mapper;

    private final JavaType type;

    public NodeSerializer(Class<Node> payloadClass) {
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        type = mapper.getTypeFactory().constructType(payloadClass);
    }

    @Override
    public ServiceInstance<Node> deserialize(byte[] bytes) throws Exception {
        Node node = mapper.readValue(bytes, type);
        String[] address = StringUtils.split(node.getHost(), ":");
        return new ServiceInstance<>("", "", address[0], Integer.valueOf(address[1]), 443, null, 0, ServiceType.DYNAMIC, null, false);
    }

    @Override
    public byte[] serialize(ServiceInstance<Node> instance) throws Exception {
        Node node = new Node();
        node.setHost(instance.getAddress() + ":" + instance.getPort());
        return mapper.writeValueAsBytes(node);
    }
}
