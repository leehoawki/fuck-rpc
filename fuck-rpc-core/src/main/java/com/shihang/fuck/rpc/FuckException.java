package com.shihang.fuck.rpc;

/**
 * 所有RPC受检异常类的基类
 *
 * @author chenfuqian
 * @since 1.0.0
 */
public class FuckException extends RuntimeException {

    public FuckException(){
        super();
    }

    public FuckException(String message){
        super(message);
    }

    public FuckException(Throwable throwable){
        super(throwable);
    }

    public FuckException(String message, Throwable throwable){
        super(message, throwable);
    }
}
