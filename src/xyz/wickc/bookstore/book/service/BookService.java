package xyz.wickc.bookstore.book.service;

import com.sun.mail.imap.protocol.ID;
import xyz.wickc.bookstore.book.dao.BookDao;
import xyz.wickc.bookstore.book.domain.Book;

import java.sql.SQLException;
import java.util.List;

public class BookService {
    private BookDao dao = new BookDao();

    public List<Book> findAll(){
        try {
            return dao.getAllBook();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("内部错误! Code : 0x000021");
        }
    }

    public List<Book> findCategoryBook(String category){
        try {
            return dao.getCategoryBook(category);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("内部错误! Code : 0x000021");
        }
    }

    public Book getBookByBid(int bid){
        try {
            return dao.toIdGetBook(bid);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("内部错误! Code : 0x000022");
        }
    }
}
