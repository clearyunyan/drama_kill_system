package com.example.drama_kill_system.service.IManager;

import com.example.drama_kill_system.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luhe
 * @since 2023-01-17
 */
public interface IUserLoginService extends IService<User> {

    Boolean opLogin(String password);

}
