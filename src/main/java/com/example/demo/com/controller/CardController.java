package com.example.demo.com.controller;


import com.example.demo.com.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@ResponseBody
@RequestMapping("/card")
public class CardController {
    @Autowired
    CardService cardService;

    /*
     * @description: 删除购物车信息
     * @author: Ma LingFei
     * @date: 2022/6/12
     * @param: token
     * @param: map
     * @return: java.lang.String
     **/
    @PostMapping("/batchDeleteCards")
    @Transactional
    public String batchDeleteCards(@RequestHeader("token") String token, @RequestBody Map<String, String> map) {
        String ids = map.get("Ids");
        System.out.println("ids:" + ids);
        return cardService.batchDeleteCards(token, ids);
    }


}
