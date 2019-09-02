package com.shihang.fuck.rpc.binding;


import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.shihang.fuck.rpc.annotation.HttpMethod;
import com.shihang.fuck.rpc.handle.RpcResponse;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapperMethodTest {

    static JavaType type;

    static {
        TypeFactory typeFactory = TypeFactory.defaultInstance();
        type = typeFactory.constructParametricType(RpcResponse.class, typeFactory.constructParametricType(List.class, Order.class));
    }

    @Test
    public void testGet1() {
        MapperMethod mapperMethod = new MapperMethod();
        mapperMethod.setHost("orderdata.dev.svc.cluster.local");
        mapperMethod.setPort(8080);
        mapperMethod.setPath("/sz/api/orders/order/select");
        mapperMethod.setMethod(HttpMethod.GET);
        mapperMethod.setReturnType(type);
        mapperMethod.setArgumentsize(-1);
        Map<String, Object> param = new HashMap<>();
        Order order = new Order();
        order.setIds(Arrays.asList(33665623));
        param.put("order", order);
        System.out.println(mapperMethod.invoke(param));
    }

    @Test
    public void testGet2() {
        MapperMethod mapperMethod = new MapperMethod();
        mapperMethod.setHost("orderdata.dev.svc.cluster.local");
        mapperMethod.setPort(8080);
        mapperMethod.setPath("/${1}/api/orders/order/select");
        mapperMethod.setMethod(HttpMethod.GET);
        mapperMethod.setReturnType(type);
        mapperMethod.setArgumentsize(-1);
        Map<String, Object> param = new HashMap<>();
        param.put("1", "sz");
        Order order = new Order();
        order.setIds(Arrays.asList(33665623));
        param.put("order", order);
        System.out.println(mapperMethod.invoke(param));
    }

    @Test
    public void testPost1() {
        MapperMethod mapperMethod = new MapperMethod();
        mapperMethod.setHost("orderdata.dev.svc.cluster.local");
        mapperMethod.setPort(8080);
        mapperMethod.setPath("/sz/api/orders/order/select");
        mapperMethod.setMethod(HttpMethod.POST);
        mapperMethod.setReturnType(type);
        mapperMethod.setArgumentsize(-1);
        Map<String, Object> param = new HashMap<>();
        Order order = new Order();
        order.setIds(Arrays.asList(33665623));
        param.put("order", order);
        System.out.println(mapperMethod.invoke(param));
    }

    @Test
    public void testPost2() {
        MapperMethod mapperMethod = new MapperMethod();
        mapperMethod.setHost("orderdata.dev.svc.cluster.local");
        mapperMethod.setPort(8080);
        mapperMethod.setPath("/${1}/api/orders/order/select");
        mapperMethod.setMethod(HttpMethod.POST);
        mapperMethod.setReturnType(type);
        mapperMethod.setArgumentsize(-1);
        Map<String, Object> param = new HashMap<>();
        param.put("1", "sz");
        Order order = new Order();
        order.setIds(Arrays.asList(33665623));
        param.put("order", order);
        System.out.println(mapperMethod.invoke(param));
    }
}

