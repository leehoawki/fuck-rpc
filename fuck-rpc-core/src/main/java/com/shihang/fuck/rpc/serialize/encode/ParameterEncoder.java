package com.shihang.fuck.rpc.serialize.encode;

import com.shihang.fuck.rpc.binding.InvokeException;
import com.shihang.fuck.rpc.serialize.Encode;
import com.shihang.fuck.rpc.utils.StringUtils;

import java.lang.reflect.Field;

public class ParameterEncoder implements Encode {
    @Override
    public String encode(Object source) {
        if (source == null) {
            return "";
        }
        String result = "?";
        try {
            for (Field field : source.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                result += field.getName() + "=" + field.get(source) + "&";
            }
        } catch (IllegalAccessException e) {
            throw new InvokeException(e);
        }
        return result;
    }
}
