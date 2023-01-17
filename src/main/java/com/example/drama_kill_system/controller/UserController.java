package com.example.drama_kill_system.controller;


import cn.hutool.core.util.ObjectUtil;
import com.example.drama_kill_system.Exception.AppException;
import com.example.drama_kill_system.result.Result;
import com.example.drama_kill_system.service.IUserService;
import com.example.drama_kill_system.utils.RedisUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author luhe
 * @since 2023-01-17
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    IUserService iUserService;
    @Resource
    RedisUtil redisUtil;
    @GetMapping("opLogin")
    public Result opLogin(String password, HttpServletRequest request){
    if (iUserService.opLogin(password)){
        redisUtil.set("op","ok",6000);
        return Result.ok("登录成功");
    }
    else
        return Result.fail("密码错误，请重新输入");
}

}
