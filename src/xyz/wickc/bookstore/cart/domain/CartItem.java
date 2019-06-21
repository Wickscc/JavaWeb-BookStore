package xyz.wickc.bookstore.cart.domain;

import xyz.wickc.bookstore.book.domain.Book;

import java.io.Serializable;

public class CartItem implements Serializable {
    private Book book;
    private int count;

    public CartItem(Book book, int count) {
        this.book = book;
        this.count = count;
    }

    public CartItem() {
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "book=" + book +
                ", count=" + count +
                '}';
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
