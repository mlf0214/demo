package com.example.demo.com.mapper;

import com.example.demo.com.pojo.sql.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select password from user where user_id=#{user_id}")
    String findByPassword(String user_id);

    /*
     * @description: 查询用户信息
     * @author: Ma LingFei
     * @date: 2022/6/27
     * @param: username
     * @param: password
     * @return: com.example.demo.com.pojo.sql.User
     **/
    @Select("select * from user where username=#{username} and password=#{password}")
    User findUser(String username, String password);

    @Select("select * from user where user_id=#{userId}")
    User findUserById(Integer id);

    /*
     * @description: 修改密码
     * @author: Ma LingFei
     * @date: 2022/6/27
     * @param: password
     * @param: id
     * @return: int
     **/
    @Update("update user set password=#{password} where user_id=#{id}")
    int updatePassword(String password, Integer id);

    /*
     * @description: 添加用户信息
     * @author: Ma LingFei
     * @date: 2022/6/27
     * @param: username
     * @param: password
     * @param: userphone
     * @param: sex
     * @param: nickname
     * @param: replace
     * @return: int
     **/
    @Insert("insert into user (username,password,userphone,sex,nickname,userimg_uri) values(#{username},#{password},#{userphone},#{sex},#{nickname},#{replace})")
    int insertUser(String username, String password, String userphone, String sex, String nickname, String replace);

    /*
     * @description: 修改用户信息
     * @author: Ma LingFei
     * @date: 2022/6/27
     * @param: userphone
     * @param: sex
     * @param: nickname
     * @param: user_id
     * @param: imguri
     * @return: int
     **/
    @Update("update user set userphone=#{userphone},sex=#{sex},nickname=#{nickname},userimg_uri=#{imguri} where user_id=#{user_id}")
    int updateUser(String userphone, String sex, String nickname, String user_id, String imguri);

    /*
     * @description: 查询所有订单列表
     * @author: Ma LingFei
     * @date: 2022/6/27
     * @param: user_id
     * @return: java.util.List<com.example.demo.com.pojo.sql.Orders>
     **/
    @Select("select * from orders where user_id=#{user_id}")
    List<Orders> selectAllOrders(Integer user_id);

    @Select("select user_id from user where username=#{username}")
    String getUserId(String username);

    @Select("select * from card where user_id=#{user_id}")
    List<Card> findUserAllCards(String user_id);

    @Insert("insert into orders (user_id,c_id,applice,phone,state,address,order_no) values (#{user_id},#{c_id},#{applice},#{phone},#{state},#{address},#{order_no})")
    int commitOrder(String user_id, String c_id, String allprice, String phone, String address, Integer state, String order_no);

    @Update("update orders set state=#{state} where order_id=#{order_id}")
    int paymentOrder(Integer order_id, Integer user_id);

    @Select("select * from orders where user_id=#{user_id} and order_id=#{order_id}")
    Orders selectthisMyOrder(String user_id, String order_id);

    /*
     * @description: 添加一条购物车信息
     * @author: Ma LingFei
     * @date: 2022/6/27
     * @param: c_id
     * @param: user_id
     * @param: c_uri
     * @param: c_name
     * @param: c_price
     * @param: count1
     * @return: int
     **/
    @Insert("insert into card (c_id,user_id,img_uri,c_name,c_price,c_count) values(#{c_id},#{user_id},#{c_uri},#{c_name},#{c_price},#{count1})")
    int addUserCard(String c_id, String user_id, String c_uri, String c_name, String c_price, String count1);

    /*
     * @description: 查询所有购物车
     * @author: Ma LingFei
     * @date: 2022/6/27
     * @param: userId
     * @return: java.util.List<com.example.demo.com.pojo.sql.Card>
     **/
    @Select("select * from card where user_id=#{userId}")
    List<Card> setlectAllCard(String userId);

    @Select("select userphone,sex,nickname,userimg_uri from user where username=#{username}")
    UserData findUserDataByUserName(String username);

    /*
     * @description: 增加一条收货信息
     * @author: Ma LingFei
     * @date: 2022/6/27
     * @param: userId
     * @param: address
     * @param: phone
     * @param: name
     * @return: int
     **/
    @Insert("insert into receiving (user_id,address,phone,name) " +
            "values(#{userId},#{address},#{phone},#{name})")
    int insertReceiving(String userId, String address, String phone, String name);

    /*
     * @description: 根据ID删除收货信息
     * @author: Ma LingFei
     * @date: 2022/6/27
     * @param: r_id
     * @param: userId
     * @return: int
     **/
    @Delete("delete from receiving where r_id=#{r_id} and user_id=#{userId}")
    int deleteUserAddress(String r_id, String userId);

    /*
     * @description: 查询用户收货信息列表
     * @author: Ma LingFei
     * @date: 2022/6/27
     * @param: userId
     * @return: java.util.List<com.example.demo.com.pojo.sql.Receiving>
     **/
    @Select("select * from receiving where user_id=#{userId}")
    List<Receiving> finAllUserAddress(String userId);

    /*
     * @description: 更新收货信息
     * @author: Ma LingFei
     * @date: 2022/6/27
     * @param: r_id
     * @param: name
     * @param: arress
     * @param: phone
     * @return: int
     **/
    @Update("update receiving set (name,address,phone) values (#{name},#{address},#{phone}) where r_id=#{r_id}")
    int updateUserAddress(String r_id, String name, String arress, String phone);

    @Select("select * from receiving where r_id=#{r_id} and user_id=#{userId}")
    Receiving findArressById(String r_id, String userId);

    @Update("update user set userimg_uri=#{userImg} where user_id=#{userId}")
    int updateUserImg(String userId, String userImg);

    @Insert("insert into orders(user_id,price,phone,address,order_no,c_name,number,img_uri) values(#{userId},#{c_price},#{phone},#{address},#{order_no},#{c_name},#{number},#{img_uri})")
    int addUserOrder(String userId, String c_price, String phone, String address, String order_no, String c_name, String number, String img_uri);

    /*
     * @description: 修改用户信息不带图片
     * @author: Ma LingFei
     * @date: 2022/6/27
     * @param: userId
     * @param: nickname
     * @param: userphone
     * @param: sex
     * @return: int
     **/
    @Update("update user set nickname=#{nickname},userphone=#{userphone},sex=#{sex} where user_id=#{userId}")
    int updateUserNoImg(String userId, String nickname, String userphone, String sex);
}
