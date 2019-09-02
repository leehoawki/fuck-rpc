package com.shihang.fuck.rpc.example;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    @Autowired
    ExampleInterface exampleInterface;

    @GetMapping("/example")
    public String fuck1() {
        return exampleInterface.example();
    }
}
