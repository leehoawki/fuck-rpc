package com.shihang.fuck.rpc.serialize;

import com.shihang.fuck.rpc.serialize.decode.JsonDecoder;
import com.shihang.fuck.rpc.serialize.encode.JsonEncoder;
import com.shihang.fuck.rpc.serialize.encode.ParameterEncoder;

import java.util.HashMap;
import java.util.Map;

public class ProtocolFactory {
    static Map<Class<?>, Encode> ENCODERS = new HashMap<>();
    static Map<Class<?>, Decode> DECODERS = new HashMap<>();

    static {
        ENCODERS.put(JsonEncoder.class, new JsonEncoder());
        ENCODERS.put(ParameterEncoder.class, new ParameterEncoder());

        DECODERS.put(JsonDecoder.class, new JsonDecoder());
    }

    public static Encode getEncoder(Class<?> clazz) {
        Encode encoder = ENCODERS.get(clazz);
        if (encoder == null) {
            throw new IllegalArgumentException("encoder not exist, clazz=" + clazz);
        } else {
            return encoder;
        }
    }

    public static Decode getDecoder(Class<?> clazz) {
        Decode decoder = DECODERS.get(clazz);
        if (decoder == null) {
            throw new IllegalArgumentException("decoder not exist, clazz=" + clazz);
        } else {
            return decoder;
        }
    }
}
