package com.shihang.fuck.rpc.annotation;

import com.shihang.fuck.rpc.handle.DefaultHandler;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Command {

    Class<?> handler() default DefaultHandler.class;

    HttpMethod method() default HttpMethod.GET;
}
