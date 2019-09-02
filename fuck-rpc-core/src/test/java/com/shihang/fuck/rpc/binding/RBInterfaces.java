package com.shihang.fuck.rpc.binding;

import com.shihang.fuck.rpc.annotation.Command;
import com.shihang.fuck.rpc.annotation.Mapper;

@Mapper(service = "requestbin.net", path = "/r/${1}")
interface RBInterface1 {

    @Command
    String get(String code);
}

@Mapper(service = "requestbin.net", path = "/r/1ffqfn01")
interface RBInterface2 {

    @Command
    String get();
}