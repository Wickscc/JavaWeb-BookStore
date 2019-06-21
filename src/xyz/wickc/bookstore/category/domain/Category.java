package xyz.wickc.bookstore.category.domain;

public class Category {
    private String cname;

    public Category() {
    }

    @Override
    public String toString() {
        return "Category{" +
                "cname='" + cname + '\'' +
                '}';
    }

    public Category(String cname) {
        this.cname = cname;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
