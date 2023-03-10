package com.example.drama_kill_system.interceptors;

import cn.hutool.core.util.StrUtil;
import com.example.drama_kill_system.controller.Manager.UserController;
import com.example.drama_kill_system.utils.RedisUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class opInterceptor implements HandlerInterceptor {

    private RedisUtil redisUtil;

    public opInterceptor(RedisUtil redisUtil) {
        this.redisUtil=redisUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  {
        String token= (String) redisUtil.get(UserController.opKey);
        if (StrUtil.isNotEmpty(token)) {
            redisUtil.set(UserController.opKey,token,1800);
            return true;
        }
        else return false;
    }
}
