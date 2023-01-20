package com.example.drama_kill_system.impl.IManagerImpls;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.drama_kill_system.entity.User;
import com.example.drama_kill_system.mapper.UserMapper;
import com.example.drama_kill_system.service.IManager.ManagerUsersService;
import org.springframework.stereotype.Service;


@Service
public class ManagerUsersServiceImpl extends ServiceImpl<UserMapper, User> implements ManagerUsersService {

}

