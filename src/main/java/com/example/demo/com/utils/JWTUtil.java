package com.example.demo.com.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class JWTUtil {
//    Token过期时间30分钟
    public static  final long EXPIRE_TIME=30*60*1000;
    /**
     * @description: 检测token是否正确
     * @author: Ma LingFei
     * @date: 2022/5/31
     * @param: token
     * @param: userId
     * @param: password
     * @return: boolean
     **/
    public static boolean verify(String token,String username,String user_id){
        try {
            Algorithm algorithm=Algorithm.HMAC256(user_id);
            JWTVerifier verifier = JWT.require(algorithm).
                    withClaim("username",username).build();
            DecodedJWT verify = verifier.verify(token);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    /**
     * @description: 生成Token
     * @author: Ma LingFei
     * @date: 2022/5/31
     * @param: userId
     * @param: password
     * @return: java.lang.String
     */
    public static String getToken(String username,String user_id){
        Date date=new Date(System.currentTimeMillis()+EXPIRE_TIME);
        Algorithm algorithm=Algorithm.HMAC256(user_id);
        String token = JWT.create().
                withClaim("username",username).withExpiresAt(date).sign(algorithm);
        return token;
    }

    /**
     * @description: 获取用户名
     * @author: Ma LingFei
     * @date: 2022/5/31
     * @param: token
     * @return: java.lang.String
     */
    public static String getUsername(String token){
        DecodedJWT decode = JWT.decode(token);
        String username = decode.getClaim("username").asString();
        return username;
    }
}
