package com.shihang.fuck.rpc.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 网络工具
 *
 * @author chenfuqian
 * @since 1.0.0
 */
public abstract class InetUtils {

    // 最小端口号
    private static final int MIN_PORT = 0;
    //最大端口号
    private static final int MAX_PORT = 65535;

    private InetUtils(){}

    /**
     * get ip address with given hostname
     * @param host hostname
     * @return ip address
     */
    public static String getIpByHost(String host){
        try {
            return InetAddress.getByName(host).getHostAddress();
        } catch (UnknownHostException e) {
            return host;
        }
    }

    /**
     * 判断端口是否可用
     *
     * @param port 给定端口
     * @return true if port is available
     */
    public static boolean isAvailablePort(int port){
        return MIN_PORT < port && port <= MAX_PORT;
    }
}
