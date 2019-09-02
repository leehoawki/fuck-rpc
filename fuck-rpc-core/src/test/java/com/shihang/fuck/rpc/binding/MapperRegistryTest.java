package com.shihang.fuck.rpc.binding;


import org.junit.Test;

import java.util.Arrays;

public class MapperRegistryTest {
    @Test
    public void test1() {
        MapperRegistry mapperRegistry = new MapperRegistry();
        OrderInterface orderInterface1 = mapperRegistry.getMapper(OrderInterface.class);
        Order order = new Order();
        order.setIds(Arrays.asList(33665623));
        System.out.println(orderInterface1.select("sz", order));
    }

    @Test
    public void test2() {
        MapperRegistry mapperRegistry = new MapperRegistry();
        RBInterface1 rbInterface1 = mapperRegistry.getMapper(RBInterface1.class);
        System.out.println(rbInterface1.get("1ffqfn01"));
    }

    @Test
    public void test3() {
        MapperRegistry mapperRegistry = new MapperRegistry();
        RBInterface2 rbInterface2 = mapperRegistry.getMapper(RBInterface2.class);
        System.out.println(rbInterface2.get());
    }
}