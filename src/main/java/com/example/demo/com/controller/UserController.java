package com.example.demo.com.controller;

import com.example.demo.com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
@ResponseBody
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * @description: 注册
     * @author: Ma LingFei
     * @date: 2022/5/31
     * @param: json
     * @return: java.lang.String
     */
    @PostMapping("/register")
    public String register(@RequestPart MultipartFile file, @RequestPart String username, String password, String userphone,
                           String nickname, String sex) {

        System.out.println("username:" + username);
        return userService.register(username, password, userphone, sex, nickname, file);
    }

    /**
     * @description: 登录
     * @author: Ma LingFei
     * @date: 2022/5/31
     * @param: json
     * @return: java.lang.String
     */
    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> json) {
        String username = String.valueOf(json.get("username"));
        String password = json.get("password");
        System.out.println(username);
        return userService.login(username, password);
    }

    /**
     * @description: 设置密码
     * @author: Ma LingFei
     * @date: 2022/5/31
     * @param: json
     * @return: java.lang.String
     */
    @PutMapping("/setPassword")
    public String setPsd(@RequestHeader("token") String token, @RequestBody Map<String, String> json) {
        String oldpassword = json.get("oldpassword");
        String newpassword = json.get("newpassword");
        System.out.println(oldpassword);
        return userService.setPsd(oldpassword, newpassword, token);
    }

    /**
     * @description: 设置用户信息带图片
     * @author: Ma LingFei
     * @date: 2022/5/31
     * @param: json
     * @return: java.lang.String
     */
    @PostMapping("/setUserData")
    public String setUserData(@RequestHeader("token") String token, @RequestPart String userphone
            , String sex, String nickname, MultipartFile file) {
        System.out.println("带图片的请求");
        return userService.setUserData(String.valueOf(token), userphone, nickname, sex, file);
    }

    /*
     * @description: 设置用户信息，不带图片的
     * @author: Ma LingFei
     * @date: 2022/6/15
     * @param: token
     * @param: map
     * @return: java.lang.String
     **/
    @PutMapping("/setUserDataNoImg")
    public String setUserdataNoImg(@RequestHeader("token") String token, @RequestBody Map<String, String> map) {
        String nickname = map.get("nickname");
        String userphone = map.get("phone");
        String sex = map.get("sex");
        System.out.println("不带图片的请求");
        System.out.println("token:" + token);
        return userService.setUserDataNoImg(nickname, userphone, sex);
    }

    /*
     * @description: 查询用户信息
     * @author: Ma LingFei
     * @date: 2022/6/8
     * @param: token
     * @return: java.lang.String
     **/
    @GetMapping("/findUserData")
    public String findUserData(@RequestHeader("token") String token) {
        return userService.findUserData(token);
    }

    /**
     * @description: 查询用户所有订单
     * @author: Ma LingFei
     * @date: 2022/5/31
     * @param: token
     * @return: java.lang.String
     */
    @GetMapping("/findAllUserOrders")
    public String findAllUserOrders(@RequestHeader String token) {
        return userService.selectAllUserOrders(token);
    }

    /**
     * @description: 查询用户购物车信息
     * @author: Ma LingFei
     * @date: 2022/5/31
     * @param: token
     * @return: java.lang.String
     */
    @GetMapping("/findAllUserCards")
    public String findAlllUserCard(@RequestHeader String token) {
        return userService.selectAllUserCards(token);
    }

    /**
     * @description: 添加支付订单
     * @author: Ma LingFei
     * @date: 2022/6/1
     * @param: token
     * @param: order_id
     * @return: java.lang.String
     **/
    @PostMapping("/paymentUserOrder")
    public String paymentUserOrder(@RequestHeader("token") String token, String order_id) {
        return userService.paymentUserOrder(token, order_id);
    }

    /*
     * @description: 添加一条购物车信息
     * @author: Ma LingFei
     * @date: 2022/6/6
     * @param: token
     * @param: map
     * @return: java.lang.String
     **/
    @PostMapping("/addOrder")
    public String addOrder(@RequestHeader("token") String token, @RequestBody Map<String, String> map) {
        String c_id = map.get("c_id");
        String c_price = map.get("c_price");
        String count = map.get("count");
        System.out.println("c_id:" + c_id + ";token=" + token);
        return userService.addCard(token, c_id, c_price, count);
    }

    /*
     * @description: 查询当前用户下的所有购物车信息
     * @author: Ma LingFei
     * @date: 2022/6/6
     * @param: token
     * @return: java.lang.String
     **/
    @GetMapping("/selectAllCards")
    public String selectAllCards(@RequestHeader("token") String token) {
        return userService.selectAllCards(token);
    }

    /*
     * @description: 添加一条收货信息
     * @author: Ma LingFei
     * @date: 2022/6/9
     * @param: token
     * @return: java.lang.String
     **/
    @PutMapping("/addUserAddress")
    public String addUserAddress(@RequestHeader("token") String token, @RequestBody Map<String, String> map) {

        return userService.addUserAddress(token, map);
    }

    /*
     * @description: 删除一条收货信息
     * @author: Ma LingFei
     * @date: 2022/6/9
     * @param: token
     * @param: map
     * @return: java.lang.String
     **/
    @DeleteMapping("/deleteUserAddress")
    public String deleteUserAddress(@RequestHeader("token") String token, @RequestBody Map<String, String> map) {
        System.out.println(map.get("r_id"));
        return userService.deleteUserAddress(token, map);
    }

    /*
     * @description: 查询用户所有收货信息
     * @author: Ma LingFei
     * @date: 2022/6/9
     * @param: token
     * @param: map
     * @return: java.lang.String
     **/
    @GetMapping("/finAllUserAddress")
    public String finAllUserAddress(@RequestHeader("token") String token) {
        return userService.finAllUserAddress(token);
    }

    /*
     * @description: 更新用户收货信息
     * @author: Ma LingFei
     * @date: 2022/6/12
     * @param: token
     * @param: map
     * @return: java.lang.String
     **/
    @PutMapping("/updateUserAddress")
    public String updateUserAddress(@RequestHeader("token") String token, @RequestBody Map<String, String> map) {
        String r_id = map.get("r_id");
        return userService.updateUserAddress(token, r_id);
    }

    @PostMapping("/uploadImage")
    public String uploadImage(@RequestPart MultipartFile file) {

        return userService.uploadImage(file);
    }

    /*
     * @description: 根据id查询收货信息
     * @author: Ma LingFei
     * @date: 2022/6/14
     * @param: token
     * @param: map
     * @return: java.lang.String
     **/
    @PostMapping("/findUserAddressById")
    public String findUserAddressById(@RequestHeader("token") String token, @RequestBody Map<String, String> map) {

        return userService.findUserAddressById(token, map);
    }

    /*
     * @description: 添加一条订单信息
     * @author: Ma LingFei
     * @date: 2022/6/15
     * @param: token
     * @param: map
     * @return: java.lang.String
     **/
    @PostMapping("/addUserAddOrders")
    public String addUserAddOrders(@RequestHeader("token") String token, @RequestBody Map<String, String> map) {
        System.out.println("map:" + map);
        return userService.addUserAddOrders(token, map);
    }
}
