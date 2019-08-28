package com.shihang.fuck.rpc.serialize;

import com.shihang.fuck.rpc.serialize.json.JsonProtocol;

import java.util.HashMap;
import java.util.Map;

public class ProtocolFactory {
    static Map<Class<?>, IProtocol> PROTOCOLS = new HashMap<>();

    static {
        PROTOCOLS.put(JsonProtocol.class, new JsonProtocol());
    }

    public static IProtocol getProtocol(Class<?> clazz) {
        IProtocol iProtocol = PROTOCOLS.get(clazz);
        if (iProtocol == null) {
            throw new IllegalArgumentException("protocol not exist, clazz=" + clazz);
        } else {
            return iProtocol;
        }
    }
}
