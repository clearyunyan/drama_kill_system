package com.example.drama_kill_system.controller.Shop;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.drama_kill_system.entity.AllDrama;
import com.example.drama_kill_system.result.Result;
import com.example.drama_kill_system.service.IManager.ManagerAllDramaService;
import com.example.drama_kill_system.service.IShop.IShopDramaService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/shop/drama")
public class ShopDramaController {
    @Resource
    private IShopDramaService iShopDramaService;
    @Resource
    private ManagerAllDramaService managerAllDramaService;
    @GetMapping("/getAllDrama")
    private Result getAllDrama(@RequestParam(value = "current", defaultValue = "1") Integer current) {
        Page<AllDrama> page= managerAllDramaService.query().page(new Page<>(current,10));
        return Result.ok(page.getRecords(),page.getPages());
    }
    @GetMapping("/getDrama")
    private Result getDrama(@RequestParam(value = "current", defaultValue = "1") Integer current) {
        return iShopDramaService.getDrama(current);
    }
    @GetMapping("/addDrama")
    private Result addDrama(Integer id){
        return iShopDramaService.addDrama(id);
    }
}
