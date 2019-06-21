package xyz.wickc.bookstore.order.domain;

import java.util.List;

public class Order {
    private String oid; // 订单编号
    private String ordertime; // 订单时间
    private double price;   // 订单总额
    private int state;  // 订单状态
    private String uid; // 所属订单的用户ID
    private String address; //用户地址

    private List<Orderitem> orderitemList;

    public Order() {
    }

    public Order(String oid, String ordertime,double price, int state, String uid, List<Orderitem> orderitemList) {
        this.oid = oid;
        this.ordertime = ordertime;
        this.price = price;
        this.state = state;
        this.uid = uid;
        this.orderitemList = orderitemList;
    }

    @Override
    public String toString() {
        return "Order{" +
                "oid='" + oid + '\'' +
                ", ordertime='" + ordertime + '\'' +
                ", price='" + price + '\'' +
                ", state=" + state +
                ", uid='" + uid + '\'' +
                ", address='" + address + '\'' +
                ", orderitemList=" + orderitemList +
                '}';
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Orderitem> getOrderitemList() {
        return orderitemList;
    }

    public void setOrderitemList(List<Orderitem> orderitemList) {
        this.orderitemList = orderitemList;
    }
}
