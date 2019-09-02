package com.shihang.fuck.rpc.binding;


import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.shihang.fuck.rpc.annotation.Command;
import com.shihang.fuck.rpc.annotation.HttpMethod;
import com.shihang.fuck.rpc.annotation.Mapper;
import com.shihang.fuck.rpc.handle.Handler;
import com.shihang.fuck.rpc.handle.HandlerFactory;
import com.shihang.fuck.rpc.utils.CollectionUtils;
import com.shihang.fuck.rpc.utils.ReflectUtils;
import com.shihang.fuck.rpc.utils.StringUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MethodDescriptor {
    private Class<?> mapperInterface;

    private Method method;

    private JavaType returnType;

    private JavaType type;

    private String schema = "http";

    private String host = "localhost";

    private int port = 80;

    private String path = "/";

    private List<Parameter> parameters;

    private MapperMethod mapperMethod;

    private int argumentsize;

    private boolean isNamespace;

    static TypeFactory typeFactory = TypeFactory.defaultInstance();

    private HttpMethod httpMethod;

    public MethodDescriptor(Class<?> mapperInterface, Method method) {
        this.mapperInterface = mapperInterface;
        this.method = method;
        resolveMethod();
        resolveService();
        resolveDefinitions();
        resolveRealPath();
        resolveReturnType();
    }

    private void resolveReturnType() {
        Command mapper = method.getAnnotation(Command.class);
        Handler handler = HandlerFactory.getHandler(mapper.handler());
        if (method.getGenericReturnType() instanceof ParameterizedType) {
            ParameterizedType type = (ParameterizedType) method.getGenericReturnType();
            this.returnType = handler.resolve(TypeFactory.defaultInstance().constructType(type));
        } else {
            this.returnType = handler.resolve(TypeFactory.defaultInstance().constructType(method.getGenericReturnType()));
        }
    }

    private void resolveService() {
        Mapper mapper = mapperInterface.getAnnotation(Mapper.class);
        String service = mapper.service();
        if (StringUtils.isEmpty(service)) {
            return;
        }
        String[] segments = StringUtils.split(service, ":");
        if (segments.length == 2) {
            this.host = segments[0];
            this.port = Integer.valueOf(segments[1]);
        } else {
            this.host = segments[0];
        }
    }

    private void resolveDefinitions() {
        this.parameters = Arrays.asList(this.method.getParameters());
    }

    private void resolveRealPath() {
        String namespace = resolveNamespace();
        String path = resolvePath();
        if (StringUtils.isNotEmpty(namespace)) {
            this.isNamespace = true;
        }
        if (StringUtils.isNotEmpty(namespace) && StringUtils.isNotEmpty(path)) {
            throw new BindingException("");
        }
        if (StringUtils.isEmpty(namespace) && StringUtils.isEmpty(path)) {
            throw new BindingException("");
        }
        String method = resolveMethodName();
        String model = resolveModel();
        if (StringUtils.isNotEmpty(namespace)) {
            // resolve using namespace field
            this.path = "/${1}/api/" + namespace + "/" + model + "/" + method;
            this.argumentsize = 1;
        }
        if (StringUtils.isNotEmpty(path)) {
            // resolve using path field
            this.path = path;
            this.argumentsize = StringUtils.count(this.path, '$');
        }
    }

    private String resolveMethodName() {
        return this.method.getName().toLowerCase();
    }

    private String resolveModel() {
        Command command = this.method.getAnnotation(Command.class);
        Type[] types = ReflectUtils.resolveParamTypes(method, mapperInterface);
        if (isNamespace && CollectionUtils.isEmptyArray(types)) {
            throw new BindingException("method[" + method.getName() + "] pamameter size must not be zero");
        }
        Class<?> modelClazz = null;
        for (Parameter parameter : parameters) {
            if (parameter.getType().isPrimitive() || String.class.equals(parameter.getType())) {
                continue;
            }
            if (modelClazz == null) {
                modelClazz = parameter.getType();
            } else {
                throw new BindingException("");
            }
        }
        if (isNamespace && modelClazz == null) {
            throw new BindingException("target missing");
        }
        if (modelClazz != null) {
            this.type = typeFactory.constructType(modelClazz);
            return modelClazz.getSimpleName().toLowerCase();
        }
        return null;
    }

    private void resolveMethod() {
        Command command = this.method.getAnnotation(Command.class);
        this.httpMethod = command.method();
    }

    private String resolvePath() {
        Mapper mapper = mapperInterface.getAnnotation(Mapper.class);
        String path = mapper.path();
        if (StringUtils.isEmpty(path)) {
            return null;
        }
        return path.toLowerCase();
    }

    private String resolveNamespace() {
        Mapper mapper = mapperInterface.getAnnotation(Mapper.class);
        String namespace = mapper.namespace();
        if (StringUtils.isEmpty(namespace)) {
            return null;
        }
        return namespace.toLowerCase();
    }

    public String getPath() {
        return path;
    }

    public JavaType getReturnType() {
        return returnType;
    }

    MapperMethod getMapperMethod() {
        if (mapperMethod != null) {
            return mapperMethod;
        }
        mapperMethod = new MapperMethod();
        mapperMethod.setHost(this.host);
        mapperMethod.setPort(this.port);
        mapperMethod.setPath(this.path);
        mapperMethod.setMethod(this.httpMethod);
        mapperMethod.setReturnType(this.returnType);
        mapperMethod.setArgumentsize(argumentsize);
        return mapperMethod;
    }

    public Object invoke(Object[] args) {
        MapperMethod mapperMethod = getMapperMethod();
        Map<String, Object> params = convertArguments(args);
        return mapperMethod.invoke(params);
    }

    private Map<String, Object> convertArguments(Object[] args) {
        Map<String, Object> map = new HashMap<>();
        int index = 0;
        for (Parameter parameter : parameters) {
            if (parameter.getType().isPrimitive() || String.class.equals(parameter.getType())) {
                map.put(String.valueOf(index + 1), args[index]);
                index += 1;
            } else {
                map.put(args.getClass().getSimpleName(), args[index]);
            }
        }
        return map;
    }
}