package com.shihang.fuck.rpc.serialize.decode;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shihang.fuck.rpc.binding.InvokeException;
import com.shihang.fuck.rpc.serialize.Decode;

import java.util.TimeZone;

public class JsonDecoder implements Decode {

    private ObjectMapper objectMapper;

    public JsonDecoder() {
        objectMapper = new ObjectMapper().setTimeZone(TimeZone.getTimeZone("GMT+8"));
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    }

    @Override
    public Object decode(byte[] source, JavaType type) {
        try {
            if (String.class.equals(type.getRawClass())) {
                return new String(source, "UTF-8");
            }
            return objectMapper.readerFor(type).readValue(new String(source, "UTF-8"));
        } catch (Exception ex) {
            throw new InvokeException(ex);
        }
    }
}
