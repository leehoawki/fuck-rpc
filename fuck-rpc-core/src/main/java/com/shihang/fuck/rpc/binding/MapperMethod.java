package com.shihang.fuck.rpc.binding;


import com.fasterxml.jackson.databind.JavaType;
import com.shihang.fuck.rpc.FuckException;
import com.shihang.fuck.rpc.annotation.HttpMethod;
import com.shihang.fuck.rpc.handle.Handler;
import com.shihang.fuck.rpc.serialize.Decode;
import com.shihang.fuck.rpc.serialize.Encode;
import com.shihang.fuck.rpc.serialize.ProtocolFactory;
import com.shihang.fuck.rpc.serialize.decode.JsonDecoder;
import com.shihang.fuck.rpc.serialize.encode.JsonEncoder;
import com.shihang.fuck.rpc.serialize.encode.ParameterEncoder;
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

    private Encode encode;

    private HttpMethod method;

    private Decode decode;

    private JavaType returnType;

    private Handler handler;

    private int argumentsize;

    public MapperMethod() {

    }

    public Object invoke(Map<String, Object> params) throws FuckException {
        if (HttpMethod.POST.equals(method)) {
            return doPost(params);
        } else if (HttpMethod.GET.equals(method)) {
            return doGet(params);
        } else {
            throw new IllegalArgumentException("no such httpmethod, method=" + method);
        }
    }

    private Object doGet(Map<String, Object> params) throws FuckException {
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

        String encoded = encode.encode(target);

        try {
            byte[] response = Request.Get(url + encoded).execute().returnContent().asBytes();
            return decode.decode(response, returnType);
        } catch (IOException e) {
            LOG.error("http-get failed, url=" + url + ", params=" + params, e);
            throw new InvokeException(e);
        }
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
            String body = encode.encode(target);
            byte[] response = Request.Post(url).bodyString(body, ContentType.APPLICATION_JSON).execute().returnContent().asBytes();
            return decode.decode(response, returnType);
        } catch (IOException e) {
            LOG.error("http-post failed, url=" + url + ", params=" + params, e);
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

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        if (HttpMethod.GET.equals(method)) {
            this.method = method;
            this.encode = ProtocolFactory.getEncoder(ParameterEncoder.class);
            this.decode = ProtocolFactory.getDecoder(JsonDecoder.class);
        } else if (HttpMethod.POST.equals(method)) {
            this.method = method;
            this.encode = ProtocolFactory.getEncoder(JsonEncoder.class);
            this.decode = ProtocolFactory.getDecoder(JsonDecoder.class);
        } else {
            throw new IllegalArgumentException("no such httpmethod, method=" + method);
        }
    }

    public Encode getEncode() {
        return encode;
    }

    public void setEncode(Encode encode) {
        this.encode = encode;
    }

    public Decode getDecode() {
        return decode;
    }

    public void setDecode(Decode decode) {
        this.decode = decode;
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

    public String getProtocal() {
        return protocal;
    }

    public void setProtocal(String protocal) {
        this.protocal = protocal;
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
