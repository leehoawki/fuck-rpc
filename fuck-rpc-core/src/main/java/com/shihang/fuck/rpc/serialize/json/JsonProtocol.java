package com.shihang.fuck.rpc.serialize.json;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.shihang.fuck.rpc.FuckException;
import com.shihang.fuck.rpc.binding.InvokeException;
import com.shihang.fuck.rpc.serialize.IProtocol;
import com.shihang.fuck.rpc.serialize.handler.RpcResponse;
import javafx.beans.binding.ObjectBinding;

import java.util.TimeZone;

public class JsonProtocol implements IProtocol {

    private ObjectMapper objectMapper;

    public JsonProtocol() {
        objectMapper = new ObjectMapper().setTimeZone(TimeZone.getTimeZone("GMT+8"));
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    }

    @Override
    public byte[] encode(Object source) throws FuckException {
        if (source == null) {
            return new byte[0];
        }

        try {
            return objectMapper.writerFor(source.getClass()).writeValueAsString(source).getBytes("UTF-8");
        } catch (Exception e) {
            throw new InvokeException(e);
        }
    }

    @Override
    public Object decode(byte[] source, JavaType type) throws FuckException {
        try {
            if (String.class.equals(type.getRawClass())){
                return new String(source, "UTF-8");
            }
            return objectMapper.readerFor(type).readValue(new String(source, "UTF-8"));
        } catch (Exception ex) {
            throw new InvokeException(ex);
        }
    }
}
