package com.shihang.fuck.rpc.example;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    @Autowired
    Example1 example1;

    @Autowired
    Example2 example2;

    @GetMapping("/example1")
    public String fuck1() {
        return example1.f1();
    }

    @GetMapping("/example2")
    public String fuck2() {
        return example2.f2();
    }
}
