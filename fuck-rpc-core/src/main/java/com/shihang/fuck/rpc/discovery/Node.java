package com.shihang.fuck.rpc.discovery;

public class Node {
    private String host;

    private int count;

    private int systemcount;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getSystemcount() {
        return systemcount;
    }

    public void setSystemcount(int systemcount) {
        this.systemcount = systemcount;
    }

    @Override
    public String toString() {
        return "Node{" +
                "host='" + host + '\'' +
                ", count=" + count +
                ", systemcount=" + systemcount +
                '}';
    }
}