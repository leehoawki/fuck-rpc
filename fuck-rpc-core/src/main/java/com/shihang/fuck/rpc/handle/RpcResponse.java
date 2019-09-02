package com.shihang.fuck.rpc.handle;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RpcResponse<T> {

    @JsonProperty("ErrorMsg")
    private String errorMsg;

    @JsonProperty("ErrorCode")
    private int errorCode;

    @JsonProperty("Data")
    private Data<T> data;

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public Data<T> getData() {
        return data;
    }

    public void setData(Data<T> data) {
        this.data = data;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "RpcResponse{" +
                "errorMsg='" + errorMsg + '\'' +
                ", errorCode=" + errorCode +
                ", data=" + data +
                '}';
    }
}
