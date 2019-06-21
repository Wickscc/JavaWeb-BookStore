package xyz.wickc.bookstore.book.dao;

import com.wicks.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import xyz.wickc.bookstore.book.domain.Book;

import java.sql.SQLException;
import java.util.List;

public class BookDao {
    private QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSourceDBCP());

    public List<Book> getAllBook() throws SQLException {
        String sql = "SELECT book.* FROM book,category WHERE book.cid = category.cid";
        return queryRunner.query(sql,new BeanListHandler<>(Book.class));
    }

    public List<Book> getCategoryBook(String category) throws SQLException {
        String sql = "SELECT book.* FROM book,category WHERE book.cid = category.cid AND category.cname = ?";
        return queryRunner.query(sql,new BeanListHandler<>(Book.class),category);
    }

    public Book toIdGetBook(int bid) throws SQLException {
        String sql = "SELECT book.* FROM book,category WHERE book.cid = category.cid AND book.bid = ?";
        return queryRunner.query(sql,new BeanHandler<>(Book.class),bid);
    }
}
