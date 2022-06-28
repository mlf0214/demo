package com.example.demo.com.pojo.sql;

public class UserBean {
    private String id;
    private String sno;
    private String password;

    @Override
    public String toString() {
        return "UserBean{" +
                "id='" + id + '\'' +
                ", sno='" + sno + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
