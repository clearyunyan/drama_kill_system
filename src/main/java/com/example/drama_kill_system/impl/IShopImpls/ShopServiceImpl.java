package com.example.drama_kill_system.impl.IShopImpls;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.drama_kill_system.entity.Shop;
import com.example.drama_kill_system.entity.dto.ShopDTO;
import com.example.drama_kill_system.mapper.ShopMapper;
import com.example.drama_kill_system.result.Result;
import com.example.drama_kill_system.service.IShop.IShopService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.drama_kill_system.utils.MailClient;
import com.example.drama_kill_system.utils.RedisKey;
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
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements IShopService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private MailClient mailClient;
    @Resource
    private ShopMapper shopMapper;
    @Override
    public boolean sendEmail(String email) {
        String code= RandomUtil.randomString(6);
        mailClient.sendMail(email,"欢迎您注册","您的验证码为:"+code);
        stringRedisTemplate.opsForValue().set(RedisKey.emailKey+email,code,20, TimeUnit.MINUTES);
        return true;
    }

    @Override
    public Result res(ShopDTO shopDTO) {
        LambdaQueryWrapper<Shop> wrapper = new LambdaQueryWrapper<Shop>()
                .eq(Shop::getEmail, shopDTO.getEmail());
         if(shopMapper.selectOne(wrapper)!=null){
             return Result.fail("用户已存在,请勿重复注册");
         }
         String code =stringRedisTemplate.opsForValue().get(RedisKey.emailKey+shopDTO.getEmail());
         if(!shopDTO.getCode().equals(code)){
             return Result.fail("验证码错误");
         }
        Shop shop=new Shop();
         shop.setEmail(shopDTO.getEmail());shop.setAddress(shopDTO.getAddress());shop.setPassword(shop.getPassword());
         shop.setName(shopDTO.getName());shop.setAbout(shopDTO.getAbout());
        boolean save = save(shop);
        if (save) {
            return Result.ok("注册成功");
        }
        return Result.fail("注册失败");
    }
}
