package com.example.drama_kill_system.mapper;

import com.example.drama_kill_system.entity.Games;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    @Update("update user set games_id = #{games_id} where user_id = #{user_id}")
    Boolean updateUserGamesId(@Param("games_id") Integer games_id,@Param("user_id") Integer user_id);

    //过几天写跟时间有关的
    @Update("")
    Boolean updateTime();

    @Update("update games set status = 'playing' where gameId = #{gameId}")
    Boolean firstAccept(Integer gameId);

    @Select("select status from games where gameId = #{gameId}")
    String getStatus(Integer gameId);
}
