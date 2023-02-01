package com.example.drama_kill_system.mapper.Shop;

import com.example.drama_kill_system.entity.ShopDrama;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author luhe
 * @since 2023-01-27
 */
@Mapper
public interface ShopDramaMapper extends BaseMapper<ShopDrama> {
    @Select("select drama_id from shop_drama where shop_id=#{id}")
    List<Integer> selectByShopId(@Param("id")Integer id);
}
