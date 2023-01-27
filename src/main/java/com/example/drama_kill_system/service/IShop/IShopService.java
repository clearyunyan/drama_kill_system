package com.example.drama_kill_system.service.IShop;

import com.example.drama_kill_system.entity.Shop;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.drama_kill_system.entity.dto.ShopLoginDTO;
import com.example.drama_kill_system.result.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luhe
 * @since 2023-01-17
 */
public interface IShopService extends IService<Shop> {
    boolean sendEmail(String email);

    Result res(ShopLoginDTO shopLoginDTO);

    Result login(String email, String password);

    Result logout(HttpServletRequest request);

    Result changePassword(String oldPassword, String newPassword);

    Result changePasswordByEmail(String code, String email, String newPassword);

    Result updateShop(Shop shop);

    Result deleteShop(HttpServletRequest request);
}
