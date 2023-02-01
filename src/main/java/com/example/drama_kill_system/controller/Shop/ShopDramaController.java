package com.example.drama_kill_system.controller.Shop;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.drama_kill_system.entity.AllDrama;
import com.example.drama_kill_system.result.Result;
import com.example.drama_kill_system.service.IManager.ManagerAllDramaService;
import com.example.drama_kill_system.service.IShop.IShopDramaService;

import org.springframework.web.bind.annotation.*;

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
    @DeleteMapping("/deleteDrama")
    private Result deleteDrama(Integer id){
        boolean b = iShopDramaService.removeById(id);
        if (b) {
            return Result.ok("删除成功");
        }
        return Result.fail("删除失败");
    }
    @GetMapping("getDrama/{id}")
    public Result getDramaById(@PathVariable("id") Integer id) {
        return Result.ok(managerAllDramaService.queryById(id));
    }
    @GetMapping("/selectByType")
    private Result queryDramaByType(@RequestParam(value = "current", defaultValue = "1") Integer current,@RequestParam("type") String type){
        Page<AllDrama> page=managerAllDramaService.lambdaQuery()
                .eq(AllDrama::getType,type).page(new Page<>(current,10));
        return Result.ok(page.getRecords(),page.getPages());
    }
    //条件查询,人数
    @GetMapping("/selectByCountMan")
    private Result queryDramaByCountMan(@RequestParam(value = "current", defaultValue = "1") Integer current,@RequestParam("countMan") Integer countMan){
        Page<AllDrama> page=managerAllDramaService.lambdaQuery()
                .eq(AllDrama::getCountMan,countMan).page(new Page<>(current,10));
        return Result.ok(page.getRecords(),page.getPages());
    }
    //条件查询,两个都有:
    @GetMapping("/selectByTwo")
    private  Result queryDramaByTwo(@RequestParam(value = "current", defaultValue = "1") Integer current
            ,@RequestParam("countMan") Integer countMan,@RequestParam("type") String type){
        Page<AllDrama> page=managerAllDramaService.lambdaQuery()
                .eq(AllDrama::getType,type)
                .eq(AllDrama::getCountMan,countMan).page(new Page<>(current,10));
        return Result.ok(page.getRecords(),page.getPages());
    }
}
