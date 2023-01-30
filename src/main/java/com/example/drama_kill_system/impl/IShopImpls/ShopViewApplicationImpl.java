package com.example.drama_kill_system.impl.IShopImpls;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.drama_kill_system.entity.Games;
import com.example.drama_kill_system.mapper.GamesMapper;
import com.example.drama_kill_system.result.Result;
import com.example.drama_kill_system.service.IShop.IShopViewApplicationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ShopViewApplicationImpl extends ServiceImpl<GamesMapper, Games> implements IShopViewApplicationService {

    @Resource
    private GamesMapper gamesMapper;

    @Override
    public Result firstAccept(Integer gamesId,Integer userId) {
        if("申请中".equals(gamesMapper.getStatus(gamesId))){
            //第一次申请
            gamesMapper.updateUserGamesId(gamesId,userId);
            //处理时间过会写 gamesMapper.updateTime();
            // return Result.ok(gamesMapper.firstAccept(gamesId));
            return Result.ok(gamesMapper.firstAccept(gamesId));
        }else{
            //相同局不同user申请
            return Result.ok(gamesMapper.updateUserGamesId(gamesId,userId));
        }
    }
}
