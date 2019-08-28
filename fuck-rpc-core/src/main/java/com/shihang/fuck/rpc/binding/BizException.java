package com.shihang.fuck.rpc.binding;


import com.shihang.fuck.rpc.FuckException;

public class BizException extends FuckException {

    public BizException() {
        super();
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(Throwable throwable) {
        super(throwable);
    }

    public BizException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
