package com.example.drama_kill_system.controller.Manager;


import com.example.drama_kill_system.result.Result;
import com.example.drama_kill_system.service.IManager.IUserLoginService;
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
@RequestMapping("/manager/user")
public class UserController {
    public static String opKey="op";
    public static String opValue="ok";
    @Resource
    IUserLoginService iUserLoginService;
    @Resource
    RedisUtil redisUtil;
    @GetMapping("opLogin")
    public Result opLogin(String password){
    if (iUserLoginService.opLogin(password)){
        redisUtil.set(opKey,opValue,1800);
        return Result.ok("登录成功");
    }
    else
        return Result.fail("密码错误，请重新输入");
}

}
