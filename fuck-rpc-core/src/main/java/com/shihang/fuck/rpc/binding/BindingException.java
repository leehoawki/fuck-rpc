package com.shihang.fuck.rpc.binding;

import com.shihang.fuck.rpc.FuckException;

public class BindingException extends FuckException {

    public BindingException(){
        super();
    }

    public BindingException(String message){
        super();
    }

    public BindingException(Throwable throwable){
        super(throwable);
    }

    public BindingException(String message, Throwable throwable){
        super(message, throwable);
    }
}
