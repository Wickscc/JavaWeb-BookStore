package xyz.wickc.bookstore.order.domain;

import xyz.wickc.bookstore.book.domain.Book;

public class Orderitem {
    private String iid;     // 订单项目ID
    private int count;     //   个数
    private double subtotal;    // 一个项目的小计
    private String oid;     // 所属订单ID
    private String bid;     // 书ID

    private Book book; // 书对象

    public Orderitem() {
    }

    public Orderitem(String iid, int count, double subtotal, String oid, String bid,Book book) {
        this.iid = iid;
        this.count = count;
        this.subtotal = subtotal;
        this.oid = oid;
        this.bid = bid;
        this.book = book;
    }

    @Override
    public String toString() {
        return "orderitem{" +
                "iid='" + iid + '\'' +
                ", count=" + count +
                ", subtotal=" + subtotal +
                ", oid='" + oid + '\'' +
                ", bid='" + bid + '\'' +
                '}';
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getIid() {
        return iid;
    }

    public void setIid(String iid) {
        this.iid = iid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }
}
