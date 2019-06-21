package xyz.wickc.bookstore.book.web.servlet;

import xyz.wickc.bookstore.ObjectServlet;
import xyz.wickc.bookstore.book.domain.Book;
import xyz.wickc.bookstore.book.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BookServlet", urlPatterns = {"/Servlet/BookServlet"})
public class BookServlet extends ObjectServlet {
    private BookService service = new BookService();
    public void findCategoryList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        String categoryName = req.getParameter("CategoryName");

        if (categoryName.equals("all")){
            req.setAttribute("list",service.findAll());
        }else{
            List<Book> bookList = service.findCategoryBook(categoryName);

            if (bookList.size() == 0){
                req.setAttribute("error","参数有误!");
                req.getRequestDispatcher("/error/error.jsp").include(req,resp);
                return;
            }

            req.setAttribute("list",bookList);
        }

        req.getRequestDispatcher("/jsps/book/list.jsp").include(req,resp);
    }

    public void bookdesc(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        String bidStr = req.getParameter("bookid");
        int bid = 0;
        if (bidStr == null){
            req.setAttribute("error","参数有误!");
            req.getRequestDispatcher("/error/error.jsp").include(req,resp);
            return;
        }

        try{
            bid = Integer.parseInt(bidStr);
        }catch (Exception e){
            req.setAttribute("error","参数有误!");
            req.getRequestDispatcher("/error/error.jsp").include(req,resp);
            return;
        }

        Book book = service.getBookByBid(bid);

        if (book == null){
            req.setAttribute("error","此书籍不存在!");
            req.getRequestDispatcher("/error/error.jsp").include(req,resp);
            return;
        }
        req.setAttribute("bookObj",book);
        req.getRequestDispatcher("/jsps/book/desc.jsp").include(req,resp);
    }
}