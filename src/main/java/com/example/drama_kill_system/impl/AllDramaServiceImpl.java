package com.example.drama_kill_system.impl;

import cn.hutool.core.util.StrUtil;
import com.example.drama_kill_system.entity.AllDrama;
import com.example.drama_kill_system.mapper.AllDramaMapper;
import com.example.drama_kill_system.service.IAllDramaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.drama_kill_system.utils.JsonUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author luhe
 * @since 2023-01-17
 */
@Service
public class AllDramaServiceImpl extends ServiceImpl<AllDramaMapper, AllDrama> implements IAllDramaService {
private static String DramaKey="drama";
@Resource
    StringRedisTemplate stringRedisTemplate;
@Resource
    AllDramaMapper allDramaMapper;
@Override
    public synchronized AllDrama  queryById(Integer id)  {
    String dramaJson = stringRedisTemplate.opsForValue().get("drama");
    //存在且有值返回
    if (StrUtil.isNotBlank(dramaJson)){
        return JsonUtils.toBean(dramaJson,AllDrama.class);
    }
    if(dramaJson!=null){
        return null;
    }
    AllDrama allDrama =allDramaMapper.selectById(id);
    if (allDrama==null){
        //存空值
        stringRedisTemplate.opsForValue().set(DramaKey+id,"",30, TimeUnit.MINUTES);
        return null;
    }
    stringRedisTemplate.opsForValue().set(DramaKey+id,JsonUtils.toString(allDrama),30, TimeUnit.MINUTES);
    return allDrama;
}
}
