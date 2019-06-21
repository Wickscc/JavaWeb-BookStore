package xyz.wickc.bookstore.user.dao;

import com.wicks.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;
import xyz.wickc.bookstore.user.domain.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserDao {
    QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSourceDBCP());
    public boolean isHasUsername(String username) throws SQLException {
        String sql = "SELECT user.username FROM user WHERE user.username = ?";
        List<User> userList = queryRunner.query(sql,new BeanListHandler<>(User.class),username);

        if (userList.size() != 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean isHasEmail(String email) throws SQLException {
        String sql = "SELECT user.email FROM user WHERE user.email = ?";
        List<User> userList = queryRunner.query(sql,new BeanListHandler<>(User.class),email);

        if (userList.size() != 0){
            return true;
        }else{
            return false;
        }
    }

    public void regist(User user) throws SQLException {
        Connection connection = JDBCUtils.getConnectionSen();
        connection.setAutoCommit(false);

        try{
            String sql = "INSERT INTO USER VALUES(?,?,?,?,?,?)";
            Object [] values = {
                    user.getUid(),
                    user.getUsername(),
                    user.getPassword(),
                    user.getEmail(),
                    user.getCode(),
                    user.getState(),
            };
            queryRunner.update(connection,sql,values);
            connection.commit();
        }catch (SQLException e){
            connection.rollback();
            throw new SQLException(e);
        }finally {
            connection.close();
        }
    }

    public boolean active(String code) throws SQLException {
        String querySql = "SELECT * from user WHERE user.code = ?";

        User user = queryRunner.query(querySql,new BeanHandler<>(User.class),code);
        if (user == null || user.getState()){
            return false;
        }

        String sql = "UPDATE `user` SET state = TRUE WHERE user.code = ?";
        int lineNub = queryRunner.update(sql,code);

        if (lineNub != 0){
            return true;
        }else{
            return false;
        }
    }

    //  服务器查询User对象
    public User login(String username,String password) throws SQLException {
        String sql = "SELECT * FROM user WHERE user.username = ? AND user.password = ?";
        return queryRunner.query(sql,new BeanHandler<>(User.class),username,password);
    }

    @Test
    public void fun() throws SQLException {
        System.out.println(isHasEmail("wicksc@outlook.com"));
    }
}
