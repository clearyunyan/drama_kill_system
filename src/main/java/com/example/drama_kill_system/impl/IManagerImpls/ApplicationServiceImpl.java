package com.example.drama_kill_system.impl.IManagerImpls;

import com.example.drama_kill_system.entity.Application;
import com.example.drama_kill_system.mapper.ApplicationMapper;
import com.example.drama_kill_system.service.IManager.IApplicationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

    @Override
    public Boolean changePlayingStatus(Integer applicationId, Integer status) {
        if(status==0){
            return applicationMapper.refuseApplication(applicationId);
        }else{
            return applicationMapper.agreeApplication(applicationId);
        }
    }
}
