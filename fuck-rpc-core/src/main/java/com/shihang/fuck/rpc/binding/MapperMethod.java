package com.shihang.fuck.rpc.binding;


import com.fasterxml.jackson.databind.JavaType;
import com.shihang.fuck.rpc.FuckException;
import com.shihang.fuck.rpc.serialize.IProtocol;
import com.shihang.fuck.rpc.serialize.handler.Handler;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

public class MapperMethod {

    private final Logger LOG = LoggerFactory.getLogger(MapperMethod.class);

    private String protocal = "http";

    private String host = "localhost";

    private int port = 80;

    private String path = "/";

    private IProtocol iProtocol;

    private JavaType returnType;

    private Handler handler;

    private int argumentsize;

    public MapperMethod() {

    }

    public Object invoke(Map<String, Object> params) throws FuckException {
        return doPost(params);
    }

    private Object doPost(Map<String, Object> params) throws FuckException {
        String url = protocal + "://" + host + ":" + port + path;
        Object target = null;
        int targets = 0;
        int arguments = 0;
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String key = entry.getKey();
            Object val = entry.getValue();
            if (val instanceof String) {
                url = url.replace("${" + key + "}", (String) val);
                arguments += 1;
            } else {
                target = val;
                targets += 1;
            }
        }

        if (targets > 1) {
            throw new InvokeException("too many targets to post, targets=" + targets + ", params=" + params);
        }

        if (argumentsize >= 0 && argumentsize != arguments) {
            throw new InvokeException("params mismatch, argumentsize=" + argumentsize + ", params=" + params);
        }

        try {
            String body = new String(iProtocol.encode(target), "utf-8");
            byte[] response = Request.Post(url).bodyString(body, ContentType.APPLICATION_JSON).execute().returnContent().asBytes();
            return iProtocol.decode(response, returnType);
        } catch (IOException e) {
            LOG.error("http-get failed, url=" + url, e);
            throw new InvokeException(e);
        }
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setIProtocol(IProtocol iProtocol) {
        this.iProtocol = iProtocol;
    }

    public JavaType getReturnType() {
        return returnType;
    }

    public void setReturnType(JavaType returnType) {
        this.returnType = returnType;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public int getArgumentsize() {
        return argumentsize;
    }

    public void setArgumentsize(int argumentsize) {
        this.argumentsize = argumentsize;
    }
}
