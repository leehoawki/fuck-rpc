package com.shihang.fuck.rpc.annotation;


import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Mapper {
    String service() default "";

    String path() default "";
}
