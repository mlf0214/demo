package com.example.demo.com.controller;

import com.example.demo.com.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/commodity")
public class CommodityController {
    @Autowired
    public CommodityService commodityService;
    /**
     * @description: 根据类别查询商品列表
     * @author: Ma LingFei
     * @date: 2022/6/1
     * @param: type
     * @return: java.lang.String
     **/
    @GetMapping("/findAllCommodity")
    public String findCommodityByType( String type){
//        System.out.println(type);
        return commodityService.findByType(type);
    }
    /**
     * @description: 根据ID查询商品详情
     * @author: Ma LingFei
     * @date: 2022/6/1
     * @param: id
     * @return: java.lang.String
     **/
    @GetMapping("/findCommodity/{id}")
    public String findCommodityById(@PathVariable("id") String id){
        System.out.println("id:"+id);
        return commodityService.findById(id);
    }
    /**
     * @description: 根据价格升降序
     * @author: Ma LingFei
     * @date: 2022/6/1
     * @param: type
     * @param: order
     * @return: java.lang.String
     **/
    @GetMapping("/findCommodityOrder")
    public String findCommodityOrder(String type,String order){
        return commodityService.findCommoditysOrder(type, order);
    }
    /*
     * @description: 查询推荐商品列表
     * @author: Ma LingFei
     * @date: 2022/6/2
     * @return: java.lang.String
     **/
    @GetMapping("/findRecommend")
    public String findRecommend(){
        return commodityService.findRecommend();
    }
    /*
     * @description: 查询轮播图
     * @author: Ma LingFei
     * @date: 2022/6/2
     * @return: java.lang.String
     **/
    @GetMapping("/banner")
    public String findBanner(){
        return commodityService.findBanner();
    }

}
