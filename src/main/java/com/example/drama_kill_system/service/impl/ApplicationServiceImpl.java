package com.example.drama_kill_system.service.impl;

import com.example.drama_kill_system.entity.Application;
import com.example.drama_kill_system.mapper.ApplicationMapper;
import com.example.drama_kill_system.service.IApplicationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
