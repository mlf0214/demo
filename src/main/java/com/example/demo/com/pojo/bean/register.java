package com.example.demo.com.pojo.bean;

public class register {
    private String username;
    private String password;
    private String userphone;
    private Integer sex;
    private String nickname;

    @Override
    public String toString() {
        return "register{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userphone='" + userphone + '\'' +
                ", sex=" + sex +
                ", nickname='" + nickname + '\'' +
                '}';
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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
