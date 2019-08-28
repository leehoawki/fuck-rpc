package com.shihang.fuck.rpc.binding;

import com.shihang.fuck.rpc.FuckException;

public class InvokeException extends FuckException {
    public InvokeException() {
        super();
    }

    public InvokeException(String message) {
        super(message);
    }

    public InvokeException(Throwable throwable) {
        super(throwable);
    }

    public InvokeException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
