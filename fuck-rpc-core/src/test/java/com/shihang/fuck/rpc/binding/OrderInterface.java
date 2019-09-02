package com.shihang.fuck.rpc.binding;

import com.shihang.fuck.rpc.annotation.Command;
import com.shihang.fuck.rpc.annotation.HttpMethod;
import com.shihang.fuck.rpc.annotation.Mapper;
import com.shihang.fuck.rpc.handle.RpcResponse;

import java.util.List;

@Mapper(service = "orderdata.dev.svc.cluster.local:8080", path = "/${1}/api/orders/order/select")
interface OrderInterface {

    @Command(method = HttpMethod.POST)
    RpcResponse<List<Order>> select(String city, Order order);
}

class Order {
    private String orderNumber;

    private long id;

    private List<Integer> ids;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNumber='" + orderNumber + '\'' +
                ", id=" + id +
                ", ids=" + ids +
                '}';
    }
}