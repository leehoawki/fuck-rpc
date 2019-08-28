package com.shihang.fuck.rpc.binding;

import java.lang.reflect.Proxy;

public class MapperRegistry {

    public MapperRegistry() {

    }

    public <T> T getMapper(Class<T> type) {
        MapperProxy<T> mapperProxy = new MapperProxy(type);
        return (T) Proxy.newProxyInstance(type.getClassLoader(), new Class[]{type}, mapperProxy);
    }
}
