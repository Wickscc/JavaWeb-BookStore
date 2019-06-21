package xyz.wickc.bookstore.order.dao;

import com.wicks.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import xyz.wickc.bookstore.book.dao.BookDao;
import xyz.wickc.bookstore.order.domain.Order;
import xyz.wickc.bookstore.order.domain.Orderitem;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderDao {
    private QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSourceDBCP());
    private BookDao bookDao = new BookDao();

    public void addOrder(Order order){
        Connection connection = JDBCUtils.getConnectionSen();

        String sql = "INSERT INTO orders(oid,ordertime,price,state,uid) VALUES(?,?,?,?,?)";
        Object  [] values = {
                order.getOid(),
                order.getOrdertime(),
                order.getPrice(),
                order.getState(),
                order.getUid(),
        };


        try{
            connection.setAutoCommit(false);
            queryRunner.update(sql,values);
            connection.commit();
        }catch (SQLException e){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void addOrderItemList(List<Orderitem> orderitemList){
        Connection connection = JDBCUtils.getConnectionSen();
        String sql = "INSERT INTO orderitem VALUES(?,?,?,?,?)";

        for (Orderitem orderitem : orderitemList){
            Object  [] values = {
                    orderitem.getIid(),
                    orderitem.getCount(),
                    orderitem.getSubtotal(),
                    orderitem.getOid(),
                    orderitem.getBid()
            };

            try{
                connection.setAutoCommit(false);
                queryRunner.update(sql,values);
                connection.commit();
            }catch (SQLException e){
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void updataAddress(String address,String oid) throws SQLException {
        String sql = "UPDATE orders SET address = ? WHERE oid = ?";
        queryRunner.update(sql,address,oid);
    }

    public void updataState(int state,String oid) throws SQLException {
        String sql = "UPDATE orders SET state = ? WHERE oid = ?";
        queryRunner.update(sql,state,oid);
    }

    public List<Order> getOrder(String uid) throws SQLException {
        String sql = "SELECT * FROM orders WHERE orders.uid = ?";
        List<Order> orderList = queryRunner.query(sql,new BeanListHandler<>(Order.class),uid);

        for(Order order : orderList){
            String oid = order.getOid();
            List<Orderitem> orderitemList = getOrderitem(oid);

            order.setOrderitemList(orderitemList);
        }

        return orderList;
    }

    private List<Orderitem> getOrderitem(String oid) throws SQLException {
        String sql = "SELECT * FROM orderitem WHERE orderitem.oid = ?";
        List<Orderitem> orderitemList =  queryRunner.query(sql,new BeanListHandler<>(Orderitem.class),oid);
        for(Orderitem orderitem : orderitemList){
            orderitem.setBook(bookDao.toIdGetBook(Integer.parseInt(orderitem.getBid())));
        }
        return orderitemList;
    }

    public Order getOrderByoid(String oid) throws SQLException {
        String sql = "SELECT * FROM orders WHERE orders.oid = ?";
        Order order = queryRunner.query(sql,new BeanHandler<>(Order.class),oid);
        order.setOrderitemList(getOrderitem(oid));
        return order;
    }
}
