package com.example.demo.com.service;

import com.example.demo.com.mapper.CardMapper;
import com.example.demo.com.mapper.UserMapper;
import com.example.demo.com.utils.JWTUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CardService {
    private int code = 0;
    private String msg = null;
    private final Map<String, Object> map = new HashMap<>();
    @Autowired
    private CardMapper cardMapper;
    @Autowired
    private UserMapper userMapper;

    /*
     * @description: 删除购物车信息
     * @author: Ma LingFei
     * @date: 2022/6/12
     * @param: token
     * @param: ids
     * @return: java.lang.String
     **/
    @Transactional
    public String batchDeleteCards(String token, String ids) {
        int count = 0;
        try {
            String username = JWTUtil.getUsername(token);
            String userId = userMapper.getUserId(username);
            String replace = ids.replace("[", "").replace("]", "");
            String[] strings = replace.split(",");
            List<String> list = Arrays.asList(strings);
            System.out.println("list:" + list);
            for (int i = 0; i < list.size(); i++) {
                //循环删除数据
                String ids1 = list.get(i);
                System.out.println("id:" + ids1);
                count = cardMapper.batchDeleteCards(
                        ids1, userId);
                if (count != 0) {
                    code = 200;
                    msg = "操作成功";
                } else {
                    code = 400;
                    msg = "删除失败";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            code = 500;
            msg = "操作失败";
        }
        map.put("code", code);
        map.put("msg", msg);
        String json = new Gson().toJson(map);
        return json;
    }
}
