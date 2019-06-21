package xyz.wickc.bookstore.order.service;

import xyz.wickc.bookstore.order.dao.OrderDao;
import xyz.wickc.bookstore.order.domain.Order;

import java.sql.SQLException;
import java.util.List;

public class OrderService {
    private OrderDao dao = new OrderDao();
    public void addOrder(Order order){
        dao.addOrder(order);
        dao.addOrderItemList(order.getOrderitemList());
    }

    public void insertAddress(String address,String oid){
        try {
            dao.updataAddress(address,oid);
            dao.updataState(1,oid);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("内部错误 Code:0x100000");
        }
    }

    public void chOrder(String oid){
        try {
            dao.updataState(2,oid);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("内部错误 Code:0x100000");
        }
    }

    public List<Order> getAllOrder(String uid){
        try{
            return dao.getOrder(uid);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("内部错误 Code:0x110000");
        }
    }

    public Order getAllOrderByOid(String oid){
        try{
            return dao.getOrderByoid(oid);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("内部错误 Code:0x111000");
        }
    }
}
