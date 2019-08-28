package com.shihang.fuck.rpc.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;

public abstract class ExceptionUtils {

    private ExceptionUtils(){}

    public static Throwable unwrapThrowable(Throwable wrapped){
        Throwable unwrapped = wrapped;
        while(true){
            if(unwrapped instanceof InvocationTargetException){
                unwrapped = ((InvocationTargetException)unwrapped).getTargetException();
            } else if(unwrapped instanceof UndeclaredThrowableException){
                unwrapped = ((UndeclaredThrowableException)unwrapped).getUndeclaredThrowable();
            } else {
                return unwrapped;
            }
        }
    }
}
