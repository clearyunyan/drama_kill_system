package com.example.drama_kill_system.controller.Manager;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.drama_kill_system.entity.Application;
import com.example.drama_kill_system.result.Result;
import com.example.drama_kill_system.service.IManager.IApplicationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  处理剧本杀游玩申请
 * </p>
 *
 * @author AHMEDALATTAR416
 * @since 2023-01-20
 */
@RestController
@RequestMapping("/application")
public class ApplicationController {

    @Resource
    private IApplicationService applicationService;

    //3.处理商家的剧本杀申请 0拒绝1同意
    //3.1 展示所有请求
    @GetMapping("/show")
    private Result showApplicationRequest(@RequestParam(value = "current", defaultValue = "1") Integer current){
        Page<Application> page=applicationService.lambdaQuery()
                .eq(Application::getStatus,"申请中")
                .page(new Page<>(current,10));
        return Result.ok(page.getRecords());
    }

    //3.2拒绝请求
    @GetMapping("/refuse/{applicationId}")
    private Result refuseApplicationRequest(@PathVariable("applicationId") Integer applicationId,String reason){
        applicationService.changePlayingStatus(applicationId,0);
        return Result.fail("reason");
    }

    //3.3同意请求
    @GetMapping("/agree/{applicationId}")
    private Result agreeApplicationRequest(@PathVariable("applicationId") Integer applicationId){
        return Result.ok(applicationService.changePlayingStatus(applicationId,1));
    }

}
