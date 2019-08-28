package com.shihang.fuck.rpc.serialize;

import com.fasterxml.jackson.databind.JavaType;

import java.lang.reflect.Type;

public interface IProtocol {

    byte[] encode(Object source);

    Object decode(byte[] source, JavaType clazz);
}
