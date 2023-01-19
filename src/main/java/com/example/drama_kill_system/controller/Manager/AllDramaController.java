package com.example.drama_kill_system.controller.Manager;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.drama_kill_system.entity.AllDrama;
import com.example.drama_kill_system.result.Result;
import com.example.drama_kill_system.service.IManager.ManagerAllDramaService;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/{id}")
    private Result queryDramaById(@PathVariable("id") Integer id){
        return Result.ok(managerallDramaService.queryById(id));
    }
}
