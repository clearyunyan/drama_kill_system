package com.example.drama_kill_system.controller.Manager;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.drama_kill_system.entity.AllDrama;
import com.example.drama_kill_system.result.Result;
import com.example.drama_kill_system.service.IManager.ManagerAllDramaService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
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
@RequestMapping("/manager/all-drama")

public class AllDramaController {
    @Resource
    private ManagerAllDramaService managerallDramaService;
    @GetMapping("/all")
    private Result queryDrama(@RequestParam(value = "current", defaultValue = "1") Integer current){
        Page<AllDrama> page= managerallDramaService.query().page(new Page<>(current,10));
        return Result.ok(page.getRecords());
    }
    //条件查询,type
    @GetMapping("/selectByType")
    private Result queryDramaByType(@RequestParam(value = "current", defaultValue = "1") Integer current,@RequestParam("type") String type){
        Page<AllDrama> page=managerallDramaService.lambdaQuery()
                .eq(AllDrama::getType,type).page(new Page<>(current,10));
        return Result.ok(page.getRecords());
    }
    //条件查询,人数
    @GetMapping("/selectByCountMan")
    private Result queryDramaByCountMan(@RequestParam(value = "current", defaultValue = "1") Integer current,@RequestParam("countMan") Integer countMan){
        Page<AllDrama> page=managerallDramaService.lambdaQuery()
                .eq(AllDrama::getCountMan,countMan).page(new Page<>(current,10));
        return Result.ok(page.getRecords());
    }
    //条件查询,两个都有:
    @GetMapping("/selectByTwo")
    private  Result queryDramaByTwo(@RequestParam(value = "current", defaultValue = "1") Integer current
            ,@RequestParam("countMan") Integer countMan,@RequestParam("type") String type){
        Page<AllDrama> page=managerallDramaService.lambdaQuery()
                .eq(AllDrama::getType,type)
                .eq(AllDrama::getCountMan,countMan).page(new Page<>(current,10));
        return Result.ok(page.getRecords());
    }
    @GetMapping("/{id}")
    private Result queryDramaById(@PathVariable("id") Integer id){
        return Result.ok(managerallDramaService.queryById(id));
    }
    @PostMapping("add")
    private Result addAllDrama(MultipartFile file,@RequestBody AllDrama allDrama){
        if (managerallDramaService.insert(file,allDrama)) {
            return Result.ok("添加成功");
        }
        return Result.fail("添加失败,请再尝试");
    }
    @DeleteMapping ("/delete/{id}")
    private Result deleteAllDrama(@PathVariable("id") Integer id){
        if (managerallDramaService.deleteDrama(id)) {
             return Result.ok("删除成功");
        }
        return Result.fail("删除失败,请再尝试");
    }

    /**
     * author:  luhe
     * @param allDrama
     * @return
     * 修改剧本杀数据并更新缓存,加了悲观锁防止缓存击穿
     */
    @PutMapping("/update")
    private  Result  updateAllDrama(@RequestBody AllDrama allDrama){
        if (managerallDramaService.updateDrama(allDrama)){
            return Result.ok("更新成功");
        }
        return Result.fail("更新失败,请重试");
    }
}
