package com.example.demo.com.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CardMapper {
    /*
     * @description: 删除购物车信息
     * @author: Ma LingFei
     * @date: 2022/6/27
     * @param: Ids
     * @param: user_id
     * @return: int
     **/
    @Delete("delete from card where c_id=#{Ids} and user_id=#{user_id}")
    int batchDeleteCards(String Ids, String user_id);
}
