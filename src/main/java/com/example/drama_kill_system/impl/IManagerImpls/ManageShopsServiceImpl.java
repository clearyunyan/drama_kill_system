package com.example.drama_kill_system.impl.IManagerImpls;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.drama_kill_system.entity.Shop;
import com.example.drama_kill_system.mapper.ShopMapper;
import com.example.drama_kill_system.service.IManager.ManageShopsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ManageShopsServiceImpl extends ServiceImpl<ShopMapper, Shop> implements ManageShopsService {

    @Resource
    private ShopMapper shopMapper;

    @Override
    public List<Shop> selectJoinedShops() {
        return shopMapper.selectAllJoinedShops();
    }

    @Override
    public Boolean handlePlayRequests() {
        return shopMapper.selectRequest();
    }
}


