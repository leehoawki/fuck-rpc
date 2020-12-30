package com.shihang.fuck.rpc.discovery;

public class MethodMeta {

    private String method;

    private String url;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "MethodMeta{" +
                ", method='" + method + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
