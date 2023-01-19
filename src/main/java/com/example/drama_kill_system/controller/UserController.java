package com.example.drama_kill_system.controller;


import com.example.drama_kill_system.result.Result;
import com.example.drama_kill_system.service.IUserService;
import com.example.drama_kill_system.utils.RedisUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
    public static String opKey="op";
    public static String opValue="ok";
    @Resource
    IUserService iUserService;
    @Resource
    RedisUtil redisUtil;
    @GetMapping("opLogin")
    public Result opLogin(String password){
    if (iUserService.opLogin(password)){
        redisUtil.set(opKey,opValue,6000);
        return Result.ok("登录成功");
    }
    else
        return Result.fail("密码错误，请重新输入");
}

}
