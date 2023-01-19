package com.example.drama_kill_system.service.IManager;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.drama_kill_system.entity.Shop;
import com.example.drama_kill_system.entity.User;

import java.util.List;

public interface ManagerUsersService extends IService<User> {
    List<User> getAllUsers();
}
