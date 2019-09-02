package com.shihang.fuck.rpc.example;

import com.shihang.fuck.rpc.annotation.Command;
import com.shihang.fuck.rpc.annotation.Mapper;

@Mapper(service ="requestbin.net", path = "/r/1ffqfn01")
public interface ExampleInterface {

    @Command
    String example();
}
