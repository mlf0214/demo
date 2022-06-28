package com.example.demo.com.service;

import com.example.demo.com.mapper.CardMapper;
import com.example.demo.com.mapper.CommodityMapper;
import com.example.demo.com.mapper.UserMapper;
import com.example.demo.com.pojo.bean.ReceivingBean;
import com.example.demo.com.pojo.sql.*;
import com.example.demo.com.utils.FileUtil;
import com.example.demo.com.utils.JWTUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    private final String path = "/src/main/resources/static/imags/user/";
    @Autowired
    UserMapper userMapper;
    @Autowired
    CommodityMapper commodityMapper;
    @Autowired
    CardMapper cardMapper;

    String json = "";
    String msg = "";
    String token = "";
    int code = 0;
    HashMap map = new HashMap();

    /**
     * @description: 注册
     * @author: Ma LingFei
     * @date: 2022/5/31
     * @param: username
     * @param: password
     * @param: userphone
     * @param: sex
     * @param: nickname
     * @return: java.lang.String
     */
    public String register(String username, String password, String userphone, String sex, String nickname, MultipartFile file) {
        int count = 0;
        try {
            String imguri = FileUtil.saveFile(username + ".jpg", System.getProperty("user.dir") + path,
                    file.getBytes());
            File file1 = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\static" + imguri);
//                这个用来判断是不是文件
            boolean exists = file1.exists();
            if (exists) {
                String replace = imguri.replace("\\", "/");
                count = userMapper.insertUser(username, password, userphone, sex, nickname, replace);
                System.out.println("imguri:" + replace);
                code = 200;
                msg = "注册成功";
            }
        } catch (Exception e) {
            count = 0;
            code = 500;
            e.printStackTrace();
        }
        if (count == 0) {
            code = 400;
            msg = "注册失败,此账号已注册";
        } else {
            code = 200;
            msg = "注册成功";
        }
        Map<String, Object> map1 = new HashMap<>();
        map1.put("msg", msg);
        map1.put("code", code);
        return new Gson().toJson(map1);
    }

    /**
     * @description: 登录功能
     * @author: Ma LingFei
     * @date: 2022/5/31
     * @param: username
     * @param: password
     * @return: java.lang.String
     */

    public String login(String username, String password) {
        try {
            User user = userMapper.findUser(username, password);
            if (user == null) {
                code = 400;
                msg = "账号/密码错误";
            } else {
                code = 200;
                msg = "登录成功";
//                获取token
                token = JWTUtil.getToken(username, userMapper.getUserId(username));
                map.put("token", token);
            }
        } catch (Exception e) {
            code = 500;
            msg = "登录失败";
            e.printStackTrace();
        }
        map.put("code", code);
        map.put("msg", msg);
        return new Gson().toJson(map);
    }

    /**
     * @description: 修改密码
     * @author: Ma LingFei
     * @date: 2022/5/31
     * @param: oldpassword
     * @param: newpassword
     * @param: token
     * @return: java.lang.String
     */
    public String setPsd(String oldpassword, String newpassword, String token) {
        String username = null;
        try {
            username = JWTUtil.getUsername(token);
            String userId = userMapper.getUserId(username);
            User user = userMapper.findUserById(Integer.valueOf(userId));
            if (!oldpassword.equals(user.getPassword())) {
                code = 400;
                msg = "原始密码错误";
            } else {
                userMapper.updatePassword(newpassword, Integer.valueOf(userId));
                code = 200;
                msg = "操作成功";
            }
        } catch (Exception e) {
            code = 400;
            msg = "验证失败";
            e.printStackTrace();
        }
        map.put("msg", msg);
        map.put("code", code);
        return new Gson().toJson(map);
    }

    /**
     * @description: 修改用户信息 （带图片）
     * @author: Ma LingFei
     * @date: 2022/5/31
     * @param: token
     * @param: userphone
     * @param: nickname
     * @param: sex
     * @return: java.lang.String
     */
    public String setUserData(String token, String userphone, String nickname, String sex, MultipartFile file) {
        String username = null;
        String imguri;
        int count;
        try {
            username = JWTUtil.getUsername(token);
            String userId = userMapper.getUserId(username);
            imguri = FileUtil.saveFile(username + ".jpg", System.getProperty("user.dir") + path, file.getBytes());
            String replace = imguri.replace("\\", "/");
//            count = userMapper.updateUserImg(userMapper.getUserId(username), replace);
            count = userMapper.updateUser(userphone, sex, nickname, userId, replace);
            if (count == 1) {
                code = 200;
                msg = "操作成功";
            } else {
                code = 400;
                msg = "找不到资源";
            }
        } catch (Exception e) {
            code = 500;
            msg = "操作失败";
        }
        Map<String, Object> map1 = new HashMap<>();
        map1.put("msg", msg);
        map1.put("code", code);
        return new Gson().toJson(map1);
    }

    /**
     * @description: 查询用户所有订单
     * @author: Ma LingFei
     * @date: 2022/5/31
     * @param: token
     * @return: java.lang.String
     */

    public String selectAllUserOrders(String token) {
        Map<String, Object> map1 = new HashMap<>();
        String username = null;
        try {
            username = JWTUtil.getUsername(token);
            String userId = userMapper.getUserId(username);
            List<Orders> orders = userMapper.selectAllOrders(Integer.valueOf(userId));
            msg = "操作成功";
            map1.put("datas", orders);
            code = 200;
        } catch (Exception e) {
            msg = "操作失败";
            code = 500;
        }
        map1.put("code", code);
        map1.put("msg", msg);
        return new Gson().toJson(map1);
    }

    /**
     * @description: 获取用户购物车信息
     * @author: Ma LingFei
     * @date: 2022/6/1
     * @param: token
     * @return: java.lang.String
     **/
    @GetMapping("/selectAllUserCards")
    public String selectAllUserCards(String token) {
        String username = null;
        try {
            username = JWTUtil.getUsername(token);
            String userId = userMapper.getUserId(username);
            List<Card> cards = userMapper.findUserAllCards(userId);
            code = 200;
            map.put("datas", cards);
            msg = "操作成功";

        } catch (Exception e) {
            code = 500;
            msg = "操作失败";
            e.printStackTrace();
        }
        map.put("code", code);
        map.put("msg", msg);
        return new Gson().toJson(map);
    }

    /*
     * @description: 生成订单
     * @author: Ma LingFei
     * @date: 2022/6/1
     * @param: token
     * @param: orderId
     * @param: cId
     * @param: allprice
     * @param: address
     * @return: java.lang.String
     **/
    public String addUserOrder(@RequestHeader("token") String token, String cId, String allprice, String address, String phone, String order_no) {
        try {
            String username = JWTUtil.getUsername(token);
            String userId = userMapper.getUserId(username);
            int i = userMapper.commitOrder(userId, cId, allprice, phone, address, 0, order_no);
            if (i == 1) {
                msg = "操作成功";
                code = 200;
            } else {
                msg = "操作失败";
                code = 500;
            }
        } catch (Exception e) {
            msg = e.toString();
            code = 500;
            e.printStackTrace();
        }
        map.put("code", code);
        map.put("msg", msg);
        return new Gson().toJson(map);
    }

    /**
     * @description: 查询订单
     * @author: Ma LingFei
     * @date: 2022/6/1
     * @param: token
     * @param: cId
     * @param: allprice
     * @param: address
     * @param: phone
     * @return: java.lang.String
     **/
    public String paymentUserOrder(@RequestHeader("token") String token, String order_id) {
        String username = JWTUtil.getUsername(token);
        String userId = userMapper.getUserId(username);
//        userMapper.addUserOrder(userId);


        return null;
    }

    /*
     * @description: 添加一条购物车信息
     * @author: Ma LingFei
     * @date: 2022/6/6
     * @param: token
     * @return: java.lang.String
     **/
    @Transactional
    public String addCard(String token, String c_id, String c_price, String count1) {
        int count = 0;
        //查询用户ID
        try {
            String username = JWTUtil.getUsername(token);
            System.out.println("c_id:" + c_id);
            String userId = userMapper.getUserId(username);
//            插入到card表当中
            if (userId != null && c_id != null) {
                try {
                    Commodity commodity = commodityMapper.findById(Integer.valueOf(c_id));
                    System.out.println("commodity:" + commodity);
                    count = userMapper.addUserCard(String.valueOf(commodity.getC_id()), userId, commodity.getC_uri(), commodity.getC_name(), c_price, count1);
                } catch (Exception e) {
                    code = 400;
                    msg = "操作失败,不能重复插入";
                    e.printStackTrace();
                }
            }
            if (count == 1) {
                code = 200;
                msg = "操作成功";
            }
        } catch (Exception e) {
            code = 500;
            msg = "操作失败";
            e.printStackTrace();
        }
        Map<String, Object> map1 = new HashMap<>();
        map1.put("msg", msg);
        map1.put("code", code);
        return new Gson().toJson(map);
    }

    /*
     * @description: 查询当前用户购物车所有信息
     * @author: Ma LingFei
     * @date: 2022/6/6
     * @param: token
     * @return: java.lang.String
     **/
    public String selectAllCards(String token) {
        List<Card> cards = new ArrayList<>();
        try {
            String username = JWTUtil.getUsername(token);
            String userId = userMapper.getUserId(username);
            cards = userMapper.setlectAllCard(userId);
            code = 200;
            msg = "操作成功";
            map.put("datas", cards);
        } catch (Exception e) {
            code = 500;
            msg = "操作失败";
            e.printStackTrace();
        }
        map.put("code", code);
        map.put("msg", msg);
        return new Gson().toJson(map);
    }

    /*
     * @description: 查询用户信息
     * @author: Ma LingFei
     * @date: 2022/6/12
     * @param: token
     * @return: java.lang.String
     **/
    public String findUserData(String token) {
        String username = JWTUtil.getUsername(token);
        Map<String, Object> map1 = new HashMap<>();
        try {
            UserData userData = userMapper.findUserDataByUserName(username);
            if (userData == null) {
                code = 404;
                msg = "找不到对象";
            } else {
                code = 200;
                msg = "操作成功";
//                userData.setUserimg_uri(System.getProperty("user.dir")+path+"X"+username+userMapper.getUserId(username)+".jpg");
                map1.put("data", userData);
            }
        } catch (Exception e) {
            code = 400;
            msg = "查询失败";
        }
        map1.put("code", code);
        map1.put("msg", msg);
        String toJson = new Gson().toJson(map1);
        return toJson;
    }

    /*
     * @description: 增加一条收货信息
     * @author: Ma LingFei
     * @date: 2022/6/9
     * @param: token
     * @param: map1
     * @return: java.lang.String
     **/
    public String addUserAddress(String token, Map<String, String> map1) {
        String username = JWTUtil.getUsername(token);
        String name = map1.get("name");
        String address = map1.get("address");
        String phone = map1.get("phone");
        try {
            String userId = userMapper.getUserId(username);
            int i = userMapper.insertReceiving(userId, address, phone, name);
            if (i == 1) {
                code = 200;
                msg = "操作成功";
            } else {
                code = 400;
                msg = "操作失败";
            }
        } catch (Exception e) {
            code = 500;
            msg = "操作失败";
            e.printStackTrace();
        }
        Map<String, Object> map2 = new HashMap<>();
        map2.put("code", code);
        map2.put("msg", msg);
        return new Gson().toJson(map2);
    }

    /*
     * @description: 删除一条用户收货信息
     * @author: Ma LingFei
     * @date: 2022/6/9
     * @param: token
     * @param: map1
     * @return: java.lang.String
     **/
    public String deleteUserAddress(String token, Map<String, String> map1) {
        String username = JWTUtil.getUsername(token);
        String userId = userMapper.getUserId(username);
        String r_id = map1.get("r_id");
        try {
            int i = userMapper.deleteUserAddress(r_id, userId);
            if (i == 1) {
                msg = "操作成功";
                code = 200;
            } else {
                msg = "操作失败";
                code = 400;
            }
        } catch (Exception e) {
            msg = "操作失败";
            code = 500;
            e.printStackTrace();
        }
        map.put("msg", msg);
        map.put("code", code);
        return new Gson().toJson(map);
    }

    /*
     * @description: 查询用户所有收货信息
     * @author: Ma LingFei
     * @date: 2022/6/9
     * @param: token
     * @param: map1
     * @return: java.lang.String
     **/
    public String finAllUserAddress(@RequestHeader("token") String token) {
        Map<String, Object> map1 = new HashMap<>();
        String username = JWTUtil.getUsername(token);
        List<Receiving> receivingList = null;
        try {
            String userId = userMapper.getUserId(username);
            receivingList = userMapper.finAllUserAddress(userId);
            if (receivingList.size() != 0) {
                code = 200;
                msg = "操作成功";
            } else {
                code = 400;
                msg = "操作失败";
            }
        } catch (Exception e) {
            code = 500;
            msg = "操作失败";
            e.printStackTrace();
        }
        map1.put("code", code);
        map1.put("msg", msg);
        map1.put("datas", receivingList);
        return new Gson().toJson(map1);
    }

    /*
     * @description: 更新收货信息
     * @author: Ma LingFei
     * @date: 2022/6/13
     * @param: token
     * @param: r_id
     * @return: java.lang.String
     **/
    public String updateUserAddress(String token, String r_id) {
        String username = JWTUtil.getUsername(token);
        try {
            String userId = userMapper.getUserId(username);
            Receiving arress = userMapper.findArressById(r_id, userId);
            int i = userMapper.updateUserAddress(r_id, arress.getName(), arress.getAddress(), arress.getPhone());
            if (i == 1) {
                code = 200;
                msg = "操作成功";
            } else {
                code = 400;
                msg = "找不到资源";
            }
        } catch (Exception e) {
            code = 500;
            msg = "操作失败";
            e.printStackTrace();
        }
        Map<String, Object> map1 = new HashMap<>();
        map1.put("code", code);
        map1.put("msg", msg);
        String s = new Gson().toJson(map1);
        return s;
    }

    public String uploadImage(MultipartFile file) {
        String img_uri;
        //        获取下载文件的路径也就是静态资源目录
        String path = System.getProperty("user.dir").replace("\\", "/") + "/src/main/resources/static/imags/user/";
        try {
            img_uri = FileUtil.saveFile(System.currentTimeMillis() + ".jpg", path, file.getBytes());
            code = 200;
            msg = "操作成功";
        } catch (IOException e) {
            code = 500;
            msg = "操作失败";
            throw new RuntimeException(e);
        }
        Map<String, Object> map1 = new HashMap<>();
        map1.put("img_uri", img_uri);
        map1.put("mag", msg);
        map1.put("code", code);
        return new Gson().toJson(map1);
    }

    /*
     * @description: 根据ID查询收货信息
     * @author: Ma LingFei
     * @date: 2022/6/27
     * @param: token
     * @param: map1
     * @return: java.lang.String
     **/
    public String findUserAddressById(String token, Map<String, String> map1) {
        String username = JWTUtil.getUsername(token);
        String r_id = map1.get("r_id");
        Receiving receiving = null;
        try {
            String userId = userMapper.getUserId(username);
            receiving = userMapper.findArressById(r_id, userId);
            if (receiving != null) {
                code = 200;
                msg = "操作成功";
            } else {
                code = 400;
                msg = "找不到资源";
            }
        } catch (Exception e) {
            code = 500;
            msg = "操作失败";
            e.printStackTrace();
        }
        Map<String, Object> map2 = new HashMap<>();
        map2.put("data", receiving);
        map2.put("code", code);
        map2.put("msg", msg);
        return new Gson().toJson(map2);
    }

    @Transactional
    public String addUserAddOrders(String token, Map<String, String> map1) {
        String json1 = map1.get("json");
        ReceivingBean bean = new Gson().fromJson(json1, ReceivingBean.class);
        String order_no = bean.getOrder_no();
        ReceivingBean.ReceivingDTO receiving = bean.getReceiving();
        List<ReceivingBean.CardDTO> cardDTOList = bean.getCard();
        String username = JWTUtil.getUsername(token);
        int count = 0;
        try {
            String userId = userMapper.getUserId(username);
            for (int i = 0; i < cardDTOList.size(); i++) {
                ReceivingBean.CardDTO cardDTO = cardDTOList.get(i);
                int i1 = userMapper.addUserOrder(userId, cardDTO.getC_price(), receiving.getPhone(), receiving.getAddress(), order_no,
                        cardDTO.getC_name(), String.valueOf(cardDTO.getC_count()), cardDTO.getImg_uri());
                count += i1;
            }
            if (count == cardDTOList.size()) {
                code = 200;
                msg = "操作成功";
//                支付成功，对购物车进行删除
                for (int i = 0; i < cardDTOList.size(); i++) {
                    Integer c_id = cardDTOList.get(i).getC_id();
                    cardMapper.batchDeleteCards(String.valueOf(c_id), userId);
                }

            } else {
                code = 400;
                msg = "找不到资源";
            }
        } catch (Exception e) {
            code = 500;
            msg = "操作失败";
            e.printStackTrace();
        }
        Map<String, Object> map2 = new HashMap<>();
        map2.put("code", code);
        map2.put("msg", msg);
        return new Gson().toJson(map2);
    }

    /*
     * @description: 设置用户信息不带图片
     * @author: Ma LingFei
     * @date: 2022/6/27
     * @param: nickname
     * @param: userphone
     * @param: sex
     * @return: java.lang.String
     **/
    public String setUserDataNoImg(String nickname, String userphone, String sex) {
        String username = JWTUtil.getUsername(token);
        int count = 0;
        try {
            String userId = userMapper.getUserId(username);
            count = userMapper.updateUserNoImg(userId, nickname, userphone, sex);
            if (count == 1) {
                code = 200;
                msg = "操作成功";
            } else {
                code = 400;
                msg = "找不到资源";
            }
        } catch (Exception e) {
            code = 500;
            msg = "操作失败";
            e.printStackTrace();
        }
        Map<String, Object> map1 = new HashMap<>();
        map1.put("code", code);
        map1.put("msg", msg);
        return new Gson().toJson(map1);
    }
}
