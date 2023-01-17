package com.example.drama_kill_system.service.impl;

import com.example.drama_kill_system.entity.User;
import com.example.drama_kill_system.mapper.UserMapper;
import com.example.drama_kill_system.result.Result;
import com.example.drama_kill_system.service.IUserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
private static String opPassword;
    @Override
    public Boolean opLogin(String password) {
        if (!password.equals("qwe123ewq321")){
        return false;
        }
        return true;
    }


}
