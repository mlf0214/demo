package com.example.demo.com.mapper;

import com.example.demo.com.pojo.bean.Banner;
import com.example.demo.com.pojo.sql.Commodity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommodityMapper {
    /*
     * @description: 根绝类别查询商品列表
     * @author: Ma LingFei
     * @date: 2022/6/27
     * @param: type
     * @return: java.util.List<com.example.demo.com.pojo.sql.Commodity>
     **/
    @Select("select * from commoditys where c_type=#{c_type}")
    List<Commodity> findAllCommodityByType(String type);

    @Select("select * from commoditys")
    List<Commodity> findAllCommodity();

    /*
     * @description: 根据ID查询商品
     * @author: Ma LingFei
     * @date: 2022/6/27
     * @param: c_id
     * @return: com.example.demo.com.pojo.sql.Commodity
     **/
    @Select("select * from commoditys where c_id=#{c_id}")
    Commodity findById(Integer c_id);

    @Select("select * from where c_type=#{c_type} order by c_price #{order}")
    List<Commodity> findCommoditysOrder(String c_type, String order);

    /*
     * @description: 查询推荐商品
     * @author: Ma LingFei
     * @date: 2022/6/27
     * @return: java.util.List<com.example.demo.com.pojo.sql.Commodity>
     **/
    @Select("SELECT * FROM commoditys ORDER BY RAND() LIMIT 8")
    List<Commodity> findRecommend();

    /*
     * @description: 查询Banner
     * @author: Ma LingFei
     * @date: 2022/6/27
     * @return: java.util.List<com.example.demo.com.pojo.bean.Banner>
     **/
    @Select("select * from banner")
    List<Banner> findBanner();
}
