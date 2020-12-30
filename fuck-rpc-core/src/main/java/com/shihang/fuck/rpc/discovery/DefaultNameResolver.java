package com.shihang.fuck.rpc.discovery;

import com.shihang.fuck.rpc.FuckException;
import com.shihang.fuck.rpc.utils.StringUtils;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.ServiceType;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;


public class DefaultNameResolver implements NameResolver {

    @Override
    public Collection<ServiceInstance> resolve(String serviceId) {
        try {
            String[] address = StringUtils.split(serviceId, ":");
            String host;
            int port;
            if (address.length == 1) {
                host = address[0];
                port = 80;
            } else {
                host = address[0];
                port = Integer.valueOf(address[1]);
            }
            ServiceInstance<?> instance = new ServiceInstance<>("", "", host, port, 443, null, 0, ServiceType.DYNAMIC, null, false);
            return Collections.unmodifiableList(Arrays.asList(instance));
        } catch (Exception ex) {
            throw new FuckException("resolve services failed.", ex);
        }
    }

    @Override
    public MethodMeta resolveMethod(String service) {
        throw new FuckException("not supported");
    }
}
