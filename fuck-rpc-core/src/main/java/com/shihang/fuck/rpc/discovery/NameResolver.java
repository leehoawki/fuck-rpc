package com.shihang.fuck.rpc.discovery;

import org.apache.curator.x.discovery.ServiceInstance;

import java.util.Collection;


public interface NameResolver {

    Collection<ServiceInstance> resolve(String service);

    MethodMeta resolveMethod(String service);
}