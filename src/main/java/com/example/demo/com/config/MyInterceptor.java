package com.example.demo.com.config;

import com.example.demo.com.mapper.UserMapper;
import com.example.demo.com.service.UserService;
import com.example.demo.com.utils.JWTUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Component
public class MyInterceptor implements HandlerInterceptor {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    private final Map<String, Object> map = new HashMap<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean b = false;
        String token = request.getHeader("token");
        if (!(handler instanceof HandlerMethod)) {
            b = true;
        }
//        获取用户名
        String username = JWTUtil.getUsername(token);
//        获取用户ID
        String userId = userMapper.getUserId(username);
//        判断是否正确
        b = JWTUtil.verify(token, username, userId);
        if (!b) {
            map.put("code", 400);
            map.put("msg", "token验证失败");
            String json = new Gson().toJson(map);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().print(json);
        }

        return b;
    }
}
