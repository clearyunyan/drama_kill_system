package com.example.drama_kill_system.controller.Shop;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.drama_kill_system.entity.Games;
import com.example.drama_kill_system.mapper.GamesMapper;
import com.example.drama_kill_system.result.Result;
import com.example.drama_kill_system.service.IShop.IShopViewApplicationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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

    //查看该商家所有的游玩申请
    @GetMapping("/all/{shopId}")
    public Result viewApplication(@RequestParam(value = "current", defaultValue = "1") Integer current,
                                  @PathVariable("shopId") Integer shopId) {
        Page<Games> page=shopViewApplicationService.lambdaQuery()
                .eq(Games::getStatus,"申请中")
                .eq(Games::getShopId,shopId)
                .page(new Page<>(current,10));
        return Result.ok(page.getRecords());
    }

    //第一次同意申请，并将gamesId对应的局的status设置为“playing”，当不同的user再次申请时，直接修改user表的games_id
    @GetMapping("/accept/{gamesId}/{userId}")
    private Result accept(@PathVariable("gamesId") Integer gamesId,
                         @PathVariable("userId") Integer userId) {
            return Result.ok(shopViewApplicationService.firstAccept(gamesId,userId));
    }

    @GetMapping("/refuse")
    private Result Refuse(){
        return Result.fail("请求被拒绝");
    }
}

