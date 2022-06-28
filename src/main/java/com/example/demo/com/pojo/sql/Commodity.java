package com.example.demo.com.pojo.sql;

public class Commodity {
    private Integer c_id;
    private double c_price;
    private String c_uri;
    private String c_advertisement;
    private String c_name;
    private String c_type;

    public String getC_type() {
        return c_type;
    }

    public void setC_type(String c_type) {
        this.c_type = c_type;
    }

    public double getC_price() {
        return c_price;
    }

    public void setC_price(double c_price) {
        this.c_price = c_price;
    }

    public String getC_uri() {
        return c_uri;
    }

    public void setC_uri(String c_uri) {
        this.c_uri = c_uri;
    }

    public String getC_advertisement() {
        return c_advertisement;
    }

    public void setC_advertisement(String c_advertisement) {
        this.c_advertisement = c_advertisement;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public Integer getC_id() {
        return c_id;
    }

    public void setC_id(Integer c_id) {
        this.c_id = c_id;
    }

    @Override
    public String toString() {
        return "Commodity{" +
                "c_id=" + c_id +
                ", c_price=" + c_price +
                ", c_uri='" + c_uri + '\'' +
                ", c_advertisement='" + c_advertisement + '\'' +
                ", c_name='" + c_name + '\'' +
                '}';
    }
}
