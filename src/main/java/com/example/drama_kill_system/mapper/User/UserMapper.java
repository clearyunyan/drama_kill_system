package com.example.drama_kill_system.mapper.User;

import com.example.drama_kill_system.entity.User;
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
public interface UserMapper extends BaseMapper<User> {
}
