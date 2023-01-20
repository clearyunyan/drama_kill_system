package com.example.drama_kill_system.service.IManager;

import com.example.drama_kill_system.entity.AllDrama;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luhe
 * @since 2023-01-17
 */
public interface ManagerAllDramaService extends IService<AllDrama> {

    AllDrama queryById(Integer id);

    boolean deleteDrama(Integer id);

    boolean updateDrama(AllDrama allDrama);

    boolean insert(AllDrama allDrama);
}
