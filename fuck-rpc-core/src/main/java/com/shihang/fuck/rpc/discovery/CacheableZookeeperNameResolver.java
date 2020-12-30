package com.shihang.fuck.rpc.discovery;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import dw.securities.micro.MicroException;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.x.discovery.ServiceCache;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class CacheableZookeeperNameResolver implements NameResolver {
    private final ServiceDiscovery<?> serviceDiscovery;

    private static final ConcurrentMap<String, ServiceCache<?>> instancesMap = new ConcurrentHashMap();

    private CuratorFramework client;

    private ObjectMapper objectMapper = new ObjectMapper();

    public CacheableZookeeperNameResolver(String cs) {
        try {
            client = CuratorFrameworkFactory.newClient(cs, new ExponentialBackoffRetry(1000, 3));
            client.start();
            NodeSerializer serializer = new NodeSerializer(Node.class);
            ServiceDiscovery<Node> serviceDiscovery = ServiceDiscoveryBuilder.builder(Node.class).client(client).basePath("/services").serializer(serializer).build();
            serviceDiscovery.start();
            this.serviceDiscovery = serviceDiscovery;
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        } catch (Exception ex) {
            throw new MicroException("fetch services failed.", ex);
        }
    }

    @Override
    public Collection<ServiceInstance> resolve(String serviceId) {
        ServiceCache serviceCache = this.instancesMap.get(serviceId);

        try {
            if (serviceCache == null) {
                synchronized (this) {
                    if (serviceCache == null) {
                        serviceCache = this.buildServiceCache(this.serviceDiscovery, serviceId);
                        this.instancesMap.putIfAbsent(serviceId, serviceCache);
                    }
                }
            }
            return Collections.unmodifiableCollection(serviceCache.getInstances());
        } catch (Exception ex) {
            throw new MicroException("fetch services failed.", ex);
        }
    }

    @Override
    public MethodMeta resolveMethod(String service) {
        try {
            String path = "/services/" + service;
            byte[] source = null;
            if (client.checkExists().forPath(path) != null) {
                source = client.getData().forPath(path);
            }
            return objectMapper.readerFor(MethodMeta.class).readValue(new String(source, "UTF-8"));
        } catch (Exception ex) {
            throw new MicroException("resolveMethod failed.", ex);
        }
    }

    private ServiceCache<?> buildServiceCache(ServiceDiscovery<?> serviceDiscovery, String serviceId) throws Exception {
        ServiceCache<?> serviceCache = serviceDiscovery.serviceCacheBuilder().name(serviceId).build();
        serviceCache.start();
        return serviceCache;
    }
}