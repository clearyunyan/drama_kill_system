package com.example.drama_kill_system.mapper;

import com.example.drama_kill_system.entity.Shop;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author luhe
 * @since 2023-01-17
 */
@Mapper
public interface ShopMapper extends BaseMapper<Shop> {

    @Select("select * from application where shop.shopId = #{shopId} and status = '申请中'")
    Boolean selectRequest(Integer shopId);
}
