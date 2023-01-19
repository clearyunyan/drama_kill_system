package com.example.drama_kill_system.controller.Manager;


import com.example.drama_kill_system.entity.Shop;
import com.example.drama_kill_system.service.IManager.ManageShopsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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
    private ManageShopsService service;










    //3.处理商家的剧本杀申请
    @GetMapping
    Boolean dealApplicationRequest(){
        return service.handlePlayRequests();

    }


    //4.查看已注册的用户信息


}
