package xyz.wickc.bookstore.category.dao;

import com.wicks.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import xyz.wickc.bookstore.category.domain.Category;

import java.sql.SQLException;
import java.util.List;

public class CategoryDao {
    private QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSourceDBCP());
    public List<Category> getAllCategory() throws SQLException {
        String sql = "SELECT category.cname FROM category";
        return queryRunner.query(sql,new BeanListHandler<>(Category.class));
    }
}
