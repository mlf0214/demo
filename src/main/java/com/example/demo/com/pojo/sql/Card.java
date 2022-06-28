package com.example.demo.com.pojo.sql;

public class Card {
    private Integer c_id;
    private Integer user_id;
    private String img_uri;
    private String c_price;
    private Integer c_count;
    private String c_name;

    public String getC_name() {

        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getC_price() {
        return c_price;
    }

    public void setC_price(String c_price) {
        this.c_price = c_price;
    }

    public Integer getC_count() {
        return c_count;
    }

    public void setC_count(Integer c_count) {
        this.c_count = c_count;
    }

    public String getImg_uri() {
        return img_uri;
    }

    public void setImg_uri(String img_uri) {
        this.img_uri = img_uri;
    }

    public Integer getC_id() {
        return c_id;
    }

    public void setC_id(Integer c_id) {
        this.c_id = c_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Card{" +
                "c_id=" + c_id +
                ", user_id=" + user_id +
                '}';
    }
}
