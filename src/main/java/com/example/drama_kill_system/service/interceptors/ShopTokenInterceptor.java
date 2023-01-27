package com.example.drama_kill_system.service.interceptors;

import cn.hutool.core.util.StrUtil;
import com.example.drama_kill_system.controller.Manager.UserController;
import com.example.drama_kill_system.entity.Shop;
import com.example.drama_kill_system.service.IShop.IShopService;
import com.example.drama_kill_system.utils.RedisUtil;
import com.example.drama_kill_system.utils.ShopHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@Component
public class ShopTokenInterceptor implements HandlerInterceptor {

    public ShopTokenInterceptor(RedisUtil redisUtil, IShopService iShopService) {
        this.redisUtil = redisUtil;
        this.iShopService = iShopService;
    }

    private RedisUtil redisUtil;

    private IShopService iShopService;
    private static final String BEARER = "BEARER";

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  {
        String header = request.getHeader("Authorization");
        if (header != null && !"".equals(header)) {
            if (header.startsWith(BEARER)) {
                String token = header.substring(7);
                Integer shopId = (Integer) redisUtil.get(token);
                if (StrUtil.isBlank(shopId+"")||shopId==null) {
                    return false;
                }
                Shop shop = iShopService.getById(shopId);
                ShopHolder.saveShop(shop);
                redisUtil.set(token,shopId,1800);
                return true;
            }
        }
        return false;
    }
}
