package com.shihang.fuck.rpc.handle;

import java.util.HashMap;
import java.util.Map;

public class HandlerFactory {
    static Map<Class<?>, Handler> HANDLERS = new HashMap<>();

    static {
        HANDLERS.put(DefaultHandler.class, new DefaultHandler());
    }

    public static Handler getHandler(Class<?> clazz) {
        Handler handler = HANDLERS.get(clazz);
        if (handler == null) {
            throw new IllegalArgumentException("decode not exist, clazz=" + clazz);
        } else {
            return handler;
        }
    }
}
