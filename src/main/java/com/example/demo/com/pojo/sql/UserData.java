package com.example.demo.com.pojo.sql;

public class UserData {
    private Integer sex;
    private String userphone;
    private String nickname;
    private String userimg_uri;

    public String getUserimg_uri() {
        return userimg_uri;
    }

    public void setUserimg_uri(String userimg_uri) {
        this.userimg_uri = userimg_uri;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
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

    @Override
    public String toString() {
        return "UserData{" +
                "sex=" + sex +
                ", userphone='" + userphone + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
