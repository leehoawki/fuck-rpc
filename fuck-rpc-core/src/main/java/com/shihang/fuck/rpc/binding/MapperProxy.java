package com.shihang.fuck.rpc.binding;

import com.shihang.fuck.rpc.utils.ExceptionUtils;
import com.shihang.fuck.rpc.utils.ReflectUtils;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class MapperProxy<T> implements InvocationHandler {

    private final Class<T> mapperInterface;

    private final Map<String, MethodDescriptor> methods = new HashMap<>();

    public MapperProxy(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
        for (Method method : this.mapperInterface.getMethods()) {
            if (method.isDefault()) {
                continue;
            }
            if (methods.containsKey(method.getName())) {
                throw new BindingException("");
            }
            MethodDescriptor methodDescriptor = new MethodDescriptor(this.mapperInterface, method);
            methods.put(method.getName(), methodDescriptor);
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            if (method.isDefault()) {
                return invokeDefaultMethod(proxy, method, args);
            }
            return methods.get(method.getName()).invoke(args);
        } catch (Throwable t) {
            throw ExceptionUtils.unwrapThrowable(t);
        }
    }

    private Object invokeDefaultMethod(Object proxy, Method method, Object[] args) throws Throwable {
        final Constructor<MethodHandles.Lookup> constructor = MethodHandles.Lookup.class.getDeclaredConstructor(Class.class, int.class);
        if (!constructor.isAccessible()) {
            constructor.setAccessible(true);
        }
        final Class<?> declaringClazz = method.getDeclaringClass();
        return constructor
                .newInstance(declaringClazz,
                        MethodHandles.Lookup.PRIVATE | MethodHandles.Lookup.PROTECTED
                                | MethodHandles.Lookup.PACKAGE | MethodHandles.Lookup.PUBLIC)
                .unreflectSpecial(method, declaringClazz).bindTo(proxy).invokeWithArguments(args);
    }
}
