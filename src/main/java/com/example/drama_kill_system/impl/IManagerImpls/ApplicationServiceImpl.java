package com.example.drama_kill_system.impl.IManagerImpls;

import com.example.drama_kill_system.entity.Application;
import com.example.drama_kill_system.entity.ShopDrama;
import com.example.drama_kill_system.mapper.Manager.ApplicationMapper;
import com.example.drama_kill_system.mapper.Shop.ShopDramaMapper;
import com.example.drama_kill_system.service.IManager.IApplicationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author luhe
 * @since 2023-01-17
 */
@Service
public class ApplicationServiceImpl extends ServiceImpl<ApplicationMapper, Application> implements IApplicationService {

    @Resource
    private ApplicationMapper applicationMapper;
    @Resource
    private ShopDramaMapper shopDramaMapper;

    @Override
    @Transactional
    public Boolean changePlayingStatus(Integer applicationId, Integer status) {
        if(status==0){

            return applicationMapper.refuseApplication(applicationId,LocalTime.now());
        }else{
            Application application = applicationMapper.selectById(applicationId);
            ShopDrama shopDrama=new ShopDrama();
            shopDrama.setShopId(application.getShopId());
            shopDrama.setDramaId(application.getDramaId());
            shopDramaMapper.insert(shopDrama);
            return applicationMapper.agreeApplication(applicationId,LocalTime.now());
        }
    }
}
