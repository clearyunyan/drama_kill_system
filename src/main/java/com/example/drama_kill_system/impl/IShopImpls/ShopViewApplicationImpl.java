package com.example.drama_kill_system.impl.IShopImpls;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.drama_kill_system.entity.Games;
import com.example.drama_kill_system.mapper.Shop.GamesMapper;
import com.example.drama_kill_system.result.Result;
import com.example.drama_kill_system.service.IShop.IShopViewApplicationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalTime;

@Service
public class ShopViewApplicationImpl extends ServiceImpl<GamesMapper, Games> implements IShopViewApplicationService {

    @Resource
    private GamesMapper gamesMapper;

    @Override
    public Result Accept(Integer game_id, DateTime time, Integer userId) {
        return Result.ok(gamesMapper.Accept(game_id,time) && gamesMapper.updateUserGamesId(game_id,userId));
    }
}
