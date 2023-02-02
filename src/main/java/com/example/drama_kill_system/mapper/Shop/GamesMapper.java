package com.example.drama_kill_system.mapper.Shop;

import cn.hutool.core.date.DateTime;
import com.example.drama_kill_system.entity.Games;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalTime;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lyf
 * @since 2023-01-28
 */
@Mapper
public interface GamesMapper extends BaseMapper<Games> {

    @Update("update user set games_id = #{game_id} where id = #{user_id}")
    Boolean updateUserGamesId(@Param("game_id") Integer game_id,@Param("user_id") Integer user_id);

    @Update("update games set status = 'playing' , start_time = #{start_time} where game_id = #{game_id}")
    Boolean Accept(@Param("game_id") Integer games_id,@Param("start_time") DateTime start_time);

    @Select("select applicants_id from games where game_id = #{game_id}")
    Integer getUserId(Integer game_id);


}
