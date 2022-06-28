package com.example.demo.com.pojo.bean;

public class User {
    private String username;
    public String password;
    public String userphone;
    private String nickname;
    private String sex;
    public String userimg_uri;

    public String getUserimg_uri() {
        return userimg_uri;
    }

    public void setUserimg_uri(String userimg_uri) {
        this.userimg_uri = userimg_uri;
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

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + userphone + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
