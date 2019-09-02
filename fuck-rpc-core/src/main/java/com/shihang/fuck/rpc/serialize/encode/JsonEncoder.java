package com.shihang.fuck.rpc.serialize.encode;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shihang.fuck.rpc.FuckException;
import com.shihang.fuck.rpc.binding.InvokeException;
import com.shihang.fuck.rpc.serialize.Encode;

import java.util.TimeZone;

public class JsonEncoder implements Encode {

    private ObjectMapper objectMapper;

    public JsonEncoder() {
        objectMapper = new ObjectMapper().setTimeZone(TimeZone.getTimeZone("GMT+8"));
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    }

    @Override
    public String encode(Object source) throws FuckException {
        if (source == null) {
            return "";
        }
        try {
            return objectMapper.writerFor(source.getClass()).writeValueAsString(source);
        } catch (Exception e) {
            throw new InvokeException(e);
        }
    }
}
