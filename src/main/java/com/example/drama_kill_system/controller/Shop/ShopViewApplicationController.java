package com.example.drama_kill_system.controller.Shop;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.drama_kill_system.entity.Games;
import com.example.drama_kill_system.mapper.Shop.GamesMapper;
import com.example.drama_kill_system.result.Result;
import com.example.drama_kill_system.service.IShop.IShopViewApplicationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalTime;

/**
 * <p>
 *  查看用户游玩申请，同意或拒绝申请
 *  申请同意则产生一个局
 * </p>
 *
 * @author lyf
 * @since 2023-01-31
 */
@RestController()
@RequestMapping("/shop/viewApplication")
public class ShopViewApplicationController {

    @Resource
    private IShopViewApplicationService shopViewApplicationService;
    @Resource
    private GamesMapper gamesMapper;

    //查看该商家所有的游玩申请
    @GetMapping("/all/{shopId}")
    public Result viewApplication(@RequestParam(value = "current", defaultValue = "1") Integer current,
                                  @PathVariable("shopId") Integer shopId) {
        Page<Games> page=shopViewApplicationService.lambdaQuery()
                .eq(Games::getStatus,"申请中")
                .eq(Games::getShopId,shopId)
                .page(new Page<>(current,10));
        return Result.ok(page.getRecords(),page.getPages());
    }

    //同意申请，并将gamesId对应的局的status设置为“playing”
    //用户加playing的局，逻辑在用户系统那边处理吧
    @GetMapping("/accept/{gamesId}")
    private Result accept(@PathVariable("gamesId") Integer gamesId){

        //申请者的userId
        Integer applicantsId = gamesMapper.getUserId(gamesId);

        return Result.ok(shopViewApplicationService.Accept(gamesId, LocalTime.now(),applicantsId));
    }

    @GetMapping("/refuse")
    private Result Refuse(){
        return Result.fail("请求被拒绝,请重试");
    }
}

