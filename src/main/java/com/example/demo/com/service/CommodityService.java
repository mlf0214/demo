package com.example.demo.com.service;


import com.example.demo.com.mapper.CommodityMapper;
import com.example.demo.com.pojo.bean.Banner;
import com.example.demo.com.pojo.sql.Commodity;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CommodityService {
    @Autowired
    CommodityMapper commodityMapper;

    private final Gson gson = new Gson();
    private final HashMap map = new HashMap();
    private String msg;
    private Integer code;


    /**
     * @description: 根据类别查询商品列表
     * @author: Ma LingFei
     * @date: 2022/6/1
     * @param: type
     * @return: java.lang.String
     **/
    public String findByType(String type) {
        try {
            List<Commodity> commodityList = commodityMapper.findAllCommodityByType(type);
            code = 200;
            msg = "操作成功";
            map.put("datas", commodityList);
        } catch (Exception e) {
            code = 500;
            msg = "操作失败";
        }
        map.put("code", code);
        map.put("msg", msg);
        String json = new Gson().toJson(map);
        return json;
    }

    /**
     * @description: 根据ID查询商品详情
     * @author: Ma LingFei
     * @date: 2022/6/1
     * @param: id
     * @return: java.lang.String
     **/
    public String findById(String id) {
        Commodity commodity = null;
        try {
            commodity = commodityMapper.findById(Integer.valueOf(id));
            code = 200;
            msg = "操作成功";
        } catch (Exception e) {
            code = 500;
            msg = "操作失败";
            e.printStackTrace();
        }
        map.put("code", code);
        map.put("msg", msg);
        map.put("data", commodity);
        return gson.toJson(map);
    }

    /**
     * @description: 查询指定类别的商品按照升降序
     * @author: Ma LingFei
     * @date: 2022/6/1
     * @param: null
     * @return: null
     **/
    public String findCommoditysOrder(String type, String order) {
        try {
            List<Commodity> commodityList = commodityMapper.findCommoditysOrder(type, order);
            code = 200;
            map.put("datas", commodityList);
            msg = "操作成功";
        } catch (Exception e) {
            code = 500;
            msg = "操作失败";
        }
        map.put("code", code);
        map.put("msg", msg);
        return gson.toJson(map);
    }

    /*
     * @description: 查询推荐商品列表
     * @author: Ma LingFei
     * @date: 2022/6/2
     * @return: java.lang.String
     **/
    public String findRecommend() {
        String datas;
        List<Commodity> commodityList = null;
        try {
            code = 200;
            commodityList = commodityMapper.findRecommend();
            msg = "查询成功";
        } catch (Exception e) {
            code = 500;
            msg = "查询失败";
            e.printStackTrace();
        }
        map.put("code", code);
        map.put("msg", msg);
        map.put("datas", commodityList);
        return gson.toJson(map);
    }

    /**
     * @description: 查询轮播图
     * @author: Ma LingFei
     * @date: 2022/6/2
     * @return: java.lang.String
     **/
    public String findBanner() {
        List<Banner> bannerList = null;
        try {
            bannerList = commodityMapper.findBanner();
            code = 200;
            msg = "查询成功";
        } catch (Exception e) {
            code = 500;
            msg = "查询失败";
            map.put("datas", "");
        }
        map.put("code", code);
        map.put("msg", msg);
        map.put("datas", bannerList);
        return gson.toJson(map);
    }
}
