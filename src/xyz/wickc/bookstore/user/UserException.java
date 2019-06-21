package xyz.wickc.bookstore.user;

public class UserException extends RuntimeException{
    public UserException(){
        super();
    }

    public UserException(String msg){
        super(msg);
    }
}
