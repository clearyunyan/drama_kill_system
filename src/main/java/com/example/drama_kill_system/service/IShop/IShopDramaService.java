package com.example.drama_kill_system.service.IShop;

import com.example.drama_kill_system.entity.ShopDrama;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.drama_kill_system.result.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luhe
 * @since 2023-01-27
 */
public interface IShopDramaService extends IService<ShopDrama> {

    Result getDrama(Integer current);

    Result addDrama(Integer id);
}
