package com.example.drama_kill_system.mapper;

import com.example.drama_kill_system.entity.Application;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author luhe
 * @since 2023-01-17
 */
@Mapper
public interface ApplicationMapper extends BaseMapper<Application> {

    @Update("update application set status = '未通过' where applicationId = #{applicationId}")
    Boolean refuseApplication(int applicationId);

    @Update("update application set status = '已通过' where applicationId = #{applicationId}")
    Boolean agreeApplication(int applicationId);

}
