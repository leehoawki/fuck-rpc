package com.shihang.fuck.rpc.handle;

import com.fasterxml.jackson.databind.JavaType;

public interface Handler {
    JavaType resolve(JavaType type);

    boolean supported(Class<?> clazz);
}
