package com.example.drama_kill_system.service.IManager;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.drama_kill_system.entity.Shop;

import java.util.List;

public interface ManageShopsService extends IService<Shop> {

    List<Shop> selectJoinedShops();

    Boolean handlePlayRequests();


}
