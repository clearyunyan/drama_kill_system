package com.example.drama_kill_system.impl.IManagerImpls;

import com.example.drama_kill_system.entity.User;
import com.example.drama_kill_system.mapper.User.UserMapper;
import com.example.drama_kill_system.service.IManager.IUserLoginService;
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
public class ManagerLoginLoginServiceImpl extends ServiceImpl<UserMapper, User> implements IUserLoginService {
private static String opPassword="qwe123ewq321";
    @Override
    public Boolean opLogin(String password) {
        if (!password.equals(opPassword)){
        return false;
        }
        return true;
    }


}
