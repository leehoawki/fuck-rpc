package com.shihang.fuck.rpc.serialize.handler;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Data<T> {

    @JsonProperty("Count")
    private Integer count;

    @JsonProperty("Result")
    private T result;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Data{" +
                "count=" + count +
                ", result=" + result +
                '}';
    }
}
