package com.example.drama_kill_system.impl.IManagerImpls;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.drama_kill_system.entity.AllDrama;
import com.example.drama_kill_system.mapper.Manager.AllDramaMapper;
import com.example.drama_kill_system.service.IManager.ManagerAllDramaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.drama_kill_system.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
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
public class ManagerAllDramaServiceImpl extends ServiceImpl<AllDramaMapper, AllDrama> implements ManagerAllDramaService {
private static String DramaKey="drama";
@Value("${file.path}")
private static String filePath;
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
        stringRedisTemplate.opsForValue().set(DramaKey+id,"",10, TimeUnit.MINUTES);
        return null;
    }
    stringRedisTemplate.opsForValue().set(DramaKey+id,JsonUtils.toString(allDrama),30, TimeUnit.MINUTES);
    return allDrama;
}

    /**
     * author:luhe
     * @param id
     * @return T/F
     * 删除数据,清除缓存
     */
    @Override
    public boolean deleteDrama(Integer id) {
        return BooleanUtil.isTrue(stringRedisTemplate.delete(DramaKey + id)) && allDramaMapper.deleteById(id) > 0;
    }

    @Override
    public synchronized boolean updateDrama(AllDrama allDrama) {
        if (allDramaMapper.updateById(allDrama) > 0) {
        stringRedisTemplate.opsForValue().set(DramaKey+allDrama.getId(),JsonUtils.toString(allDrama));
        return true;
        }
        return false;
    }

    @Override
    public boolean insert(MultipartFile file, AllDrama allDrama) {
        if (file.isEmpty()) {
            return false;
        }
        String org = file.getOriginalFilename();
        assert org != null;
        String last=org.substring(org.lastIndexOf("."));
        if(!(last.equals("jpg")||last.equals("png")||last.equals("bmp"))){
            return false;
        }
        String fileName=filePath+System.currentTimeMillis()+last;
        File dest=new File(fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            allDrama.setImg(fileName);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        if (allDramaMapper.insert(allDrama)>0) {
            AllDrama allDrama1 = allDramaMapper.selectOne(new LambdaUpdateWrapper<AllDrama>().eq(AllDrama::getAbout, allDrama.getAbout())
                    .eq(AllDrama::getCountMan, allDrama.getCountMan()));
            stringRedisTemplate.opsForValue().set(DramaKey+allDrama1.getId(),JsonUtils.toString(allDrama1));
            return true;
        }
        return false;
    }
}
