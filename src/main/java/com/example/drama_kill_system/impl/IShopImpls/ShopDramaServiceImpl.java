package com.example.drama_kill_system.impl.IShopImpls;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.drama_kill_system.entity.AllDrama;
import com.example.drama_kill_system.entity.Application;
import com.example.drama_kill_system.entity.Shop;
import com.example.drama_kill_system.entity.ShopDrama;
import com.example.drama_kill_system.mapper.ApplicationMapper;
import com.example.drama_kill_system.mapper.ShopDramaMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.drama_kill_system.result.Result;
import com.example.drama_kill_system.service.IManager.ManagerAllDramaService;
import com.example.drama_kill_system.service.IShop.IShopDramaService;
import com.example.drama_kill_system.utils.ShopHolder;
import org.apache.catalina.core.ApplicationMapping;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author luhe
 * @since 2023-01-27
 */
@Service
public class ShopDramaServiceImpl extends ServiceImpl<ShopDramaMapper, ShopDrama> implements IShopDramaService {
    @Resource
    private ManagerAllDramaService managerAllDramaService;
    @Resource
    private ApplicationMapper applicationMapper;
    @Resource
    private ShopDramaMapper shopDramaMapper;
    @Override
    public Result getDrama(Integer current) {
        Shop shop = ShopHolder.getShop();
        List<Integer> list = shopDramaMapper.selectByShopId(shop.getId());
        Page<AllDrama> page=managerAllDramaService.lambdaQuery().in(AllDrama::getId,list).page(new Page<>(current,10));
        return Result.ok(page.getRecords(),page.getPages());
    }

    @Override
    public Result addDrama(Integer id) {
        AllDrama byId = managerAllDramaService.getById(id);
        ShopDrama shopDrama1 = shopDramaMapper.selectOne(new LambdaQueryWrapper<ShopDrama>().eq(ShopDrama::getDramaId, id)
                .eq(ShopDrama::getShopId, ShopHolder.getShop().getId()));
        if(shopDrama1!=null){
            return Result.fail("剧本已存在,请勿重复添加");
        }
        if(byId.getApply()==0){
            ShopDrama shopDrama=new ShopDrama();
            shopDrama.setDramaId(id);
            shopDrama.setShopId(ShopHolder.getShop().getId());
            shopDramaMapper.insert(shopDrama);
            return Result.ok("添加成功");
        }
        Application application=new Application();
        application.setShopId(ShopHolder.getShop().getId());
        application.setDramaId(id);
        Application application1 = applicationMapper.selectOne(new LambdaQueryWrapper<Application>().eq(Application::getShopId, application.getShopId())
                .eq(Application::getDramaId, application.getDramaId()));
        if (application1!=null&&application1.getStatus().equals("申请中")){
            return Result.fail("申请已发送,请勿重复申请");
        }
        application.setStatus("申请中");
        application.setShopName(ShopHolder.getShop().getName());
        application.setDramaName(byId.getName());
        application.setCreateTime(LocalDateTime.now());
        applicationMapper.insert(application);
        return Result.ok("该剧本需要申请,申请已发送");
    }
}
