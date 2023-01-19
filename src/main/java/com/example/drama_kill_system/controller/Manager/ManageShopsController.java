package com.example.drama_kill_system.controller.Manager;


import com.example.drama_kill_system.result.Result;
import com.example.drama_kill_system.service.IManager.ManageShopsService;
import com.example.drama_kill_system.service.IManager.ManagerUsersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/shops")
    private Result getJoinedShops() {
        return Result.ok(shopService.selectJoinedShops());
    }


    //3.处理商家的剧本杀申请,requestId是申请剧本杀的商家的主键id
    @GetMapping("/{requestId}")
    private Result dealApplicationRequest(@PathVariable("requestId") Integer id){
        return Result.ok(shopService.handlePlayRequests(id));
    }


    //4.查看已注册的用户信息
    @GetMapping("/all")
    private Result getAllUsers(){
        return Result.ok(usersService.getAllUsers());
    }

}
