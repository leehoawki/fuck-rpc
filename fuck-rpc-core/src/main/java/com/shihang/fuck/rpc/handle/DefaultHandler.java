package com.shihang.fuck.rpc.handle;

import com.fasterxml.jackson.databind.JavaType;

public class DefaultHandler implements Handler {
    @Override
    public JavaType resolve(JavaType type) {
        return type;
    }

    @Override
    public boolean supported(Class<?> clazz) {
        return true;
    }
}
