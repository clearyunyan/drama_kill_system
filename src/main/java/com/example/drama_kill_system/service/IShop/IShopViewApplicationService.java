package com.example.drama_kill_system.service.IShop;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.drama_kill_system.entity.Games;
import com.example.drama_kill_system.result.Result;

import java.time.LocalTime;

public interface IShopViewApplicationService extends IService<Games> {

    Result Accept(Integer gamesId, LocalTime time,Integer userId);

}
