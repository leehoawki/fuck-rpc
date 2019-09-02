package com.shihang.fuck.rpc.serialize;

import com.fasterxml.jackson.databind.JavaType;

public interface Decode {
    Object decode(byte[] source, JavaType clazz);
}
