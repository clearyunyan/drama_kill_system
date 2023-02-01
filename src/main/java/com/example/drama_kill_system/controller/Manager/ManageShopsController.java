package com.example.drama_kill_system.controller.Manager;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.drama_kill_system.entity.Shop;
import com.example.drama_kill_system.entity.User;
import com.example.drama_kill_system.result.Result;
import com.example.drama_kill_system.service.IManager.ManageShopsService;
import com.example.drama_kill_system.service.IManager.ManagerUsersService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器,处理管理系统的各种需求:
 *  1.管理账户的登录,修改密码
 *  2.查看已加入的商家
 *  3.处理商家的剧本杀申请
 *  4.查看已注册的用户信息
 * </p>
 *
 * @author AHMEDALATTAR416
 * @since 2023-01-19
 */
@RestController
@RequestMapping("/manager/shop")
public class ManageShopsController {

    @Resource
    private ManageShopsService shopService;

    @Resource
    private ManagerUsersService usersService;



    //1.管理账户的登录,修改密码



    //2查看已加入的商家
    @GetMapping("/allshops")
    private Result getJoinedShops(@RequestParam(value = "current", defaultValue = "1") Integer current
            ) {
        Page<Shop> page=shopService.lambdaQuery()
                .page(new Page<>(current,10));
        return Result.ok(page.getRecords(),page.getPages());
    }



    //4.查看已注册的用户信息
    @GetMapping("/alluser")
    private Result getAllUsers(@RequestParam(value = "current", defaultValue = "1") Integer current){
        Page<User> page=usersService.lambdaQuery()
                .page(new Page<>(current,10));
        return Result.ok(page.getRecords(),page.getPages());
    }

}
