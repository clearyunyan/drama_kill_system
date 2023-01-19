package com.example.drama_kill_system.impl.IManagerImpls;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.drama_kill_system.entity.User;
import com.example.drama_kill_system.mapper.UserMapper;
import com.example.drama_kill_system.service.IManager.ManagerUsersService;

import java.util.List;

public class ManagerUsersServiceImpl extends ServiceImpl<UserMapper, User> implements ManagerUsersService {


    @Override
    public List<User> getAlRegisteredUsers() {
        return null;
    }
}
