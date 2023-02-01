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
 * @author lyf
 * @since 2023-01-20
 */
@RestController
@RequestMapping("/manager/application")
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
        return Result.ok(page.getRecords(), page.getPages());
    }

    //3.2拒绝请求
    @GetMapping("/refuse/{applicationId}")
    private Result refuseApplicationRequest(@PathVariable("applicationId") Integer applicationId){
        if (applicationService.changePlayingStatus(applicationId,0)) {
            return Result.ok("拒绝成功");
        }
        return Result.fail("拒绝失败");
    }

    //3.3同意请求
    @GetMapping("/agree/{applicationId}")
    private Result agreeApplicationRequest(@PathVariable("applicationId") Integer applicationId){
        if (applicationService.changePlayingStatus(applicationId,1)) {
            return Result.ok("同意成功");
        }
        return Result.fail("同意失败");
    }

}
