package com.shihang.fuck.rpc.example;

import com.shihang.fuck.rpc.annotation.Command;
import com.shihang.fuck.rpc.annotation.Mapper;

@Mapper(service ="requestbin.net", path = "/r/18urwps1")
public interface Example2 {

    @Command
    String f2();
}
