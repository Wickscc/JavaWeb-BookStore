package xyz.wickc.bookstore.user.domain;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private String username;        // 用户名
    private String password;        // 密码
    private String email;               // 邮箱
    private String uid;
    private String code;                     // 激活码
    private boolean state;          // 激活状态

    public User() {
    }

    public User(String uid,String username, String password, String email, String code, boolean state) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.code = code;
        this.state = state;
        this.uid = uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", uid='" + uid + '\'' +
                ", code='" + code + '\'' +
                ", state=" + state +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return code == user.code &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(state, user.state);
    }

    public String getUid() {
        return uid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, email, code, state);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
