package com.example.demo.com.pojo.bean;

import java.util.List;

public class ReceivingBean {

    private String order_no;
    private ReceivingDTO receiving;
    private List<CardDTO> card;

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public ReceivingDTO getReceiving() {
        return receiving;
    }

    public void setReceiving(ReceivingDTO receiving) {
        this.receiving = receiving;
    }

    public List<CardDTO> getCard() {
        return card;
    }

    public void setCard(List<CardDTO> card) {
        this.card = card;
    }

    public static class ReceivingDTO {
        private String address;
        private String name;
        private String phone;
        private Integer r_id;
        private String user_id;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Integer getR_id() {
            return r_id;
        }

        public void setR_id(Integer r_id) {
            this.r_id = r_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }
    }

    public static class CardDTO {
        private Integer c_count;
        private Integer c_id;
        private String c_name;
        private String c_price;
        private String img_uri;
        private Integer user_id;

        public Integer getC_count() {
            return c_count;
        }

        public void setC_count(Integer c_count) {
            this.c_count = c_count;
        }

        public Integer getC_id() {
            return c_id;
        }

        public void setC_id(Integer c_id) {
            this.c_id = c_id;
        }

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

        public String getImg_uri() {
            return img_uri;
        }

        public void setImg_uri(String img_uri) {
            this.img_uri = img_uri;
        }

        public Integer getUser_id() {
            return user_id;
        }

        public void setUser_id(Integer user_id) {
            this.user_id = user_id;
        }
    }
}
