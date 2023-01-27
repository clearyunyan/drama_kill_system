package com.example.drama_kill_system.impl.IShopImpls;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.drama_kill_system.entity.Shop;
import com.example.drama_kill_system.entity.dto.ShopLoginDTO;
import com.example.drama_kill_system.mapper.ShopMapper;
import com.example.drama_kill_system.result.Result;
import com.example.drama_kill_system.service.IShop.IShopService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.drama_kill_system.utils.*;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
        mailClient.sendMail(email,"欢迎您注册或修改密码","您的验证码为:"+code);
        stringRedisTemplate.opsForValue().set(RedisKey.emailKey+email,code,20, TimeUnit.MINUTES);
        return true;
    }

    @Override
    public Result res(ShopLoginDTO shopLoginDTO) {
        String code =stringRedisTemplate.opsForValue().get(RedisKey.emailKey+ shopLoginDTO.getEmail());
        if(!shopLoginDTO.getCode().equals(code)){
            return Result.fail("验证码错误");
        }
        LambdaQueryWrapper<Shop> wrapper = new LambdaQueryWrapper<Shop>()
                .eq(Shop::getEmail, shopLoginDTO.getEmail());
         if(shopMapper.selectOne(wrapper)!=null){
             return Result.fail("用户已存在,请勿重复注册");
         }
         //通过认证,开始注册
        Shop shop=new Shop();
         shop.setEmail(shopLoginDTO.getEmail());shop.setAddress(shopLoginDTO.getAddress());
         shop.setPassword(PasswordUtils.encrypt(shopLoginDTO.getPassword()));
         shop.setName(shopLoginDTO.getName());shop.setAbout(shopLoginDTO.getAbout());
        boolean save = save(shop);
        if (save) {
            return Result.ok("注册成功");
        }
        return Result.fail("注册失败");
    }

    @Override
    public Result login(String email, String password) {
        LambdaQueryWrapper<Shop> wrapper = new LambdaQueryWrapper<Shop>()
                .eq(Shop::getEmail, email)
                .eq(Shop::getPassword, PasswordUtils.encrypt(password));

        Shop shop = baseMapper.selectOne(wrapper);
        if(BeanUtil.isEmpty(shop)){
            return Result.fail("账号或密码错误,请重新尝试");
        }
        //登录成功
        String token = JwtUtils.generateShopToken(shop.getId(),shop.getEmail());
        stringRedisTemplate.opsForValue().set(token,shop.getId()+"",30,TimeUnit.MINUTES);
        return Result.ok(token);
    }

    @Override
    public Result logout(HttpServletRequest request) {
        String header =  request.getHeader("Authorization");
        String token =  header.substring(7);
        ShopHolder.removeShop();
        stringRedisTemplate.delete(token);
        return Result.ok("登出成功");
    }

    @Override
    public Result changePassword(String oldPassword, String newPassword) {
        Shop shop = ShopHolder.getShop();
        if (oldPassword.equals(newPassword)){
            return Result.fail("旧密码不能和新密码相同");
        }
        if(StringUtils.isEmpty(oldPassword)||!(PasswordUtils.encrypt(oldPassword).equals(shop.getPassword()))){
            return Result.fail("原密码错误");
        }
        shop.setPassword(PasswordUtils.encrypt(newPassword));
        updateById(shop);
        ShopHolder.removeShop();
        ShopHolder.saveShop(shop);
        return Result.ok("修改密码成功");
    }

    @Override
    public Result changePasswordByEmail(String code, String email, String newPassword) {
        String co =stringRedisTemplate.opsForValue().get(RedisKey.emailKey+ email);
        if(!code.equals(co)){
            return Result.fail("验证码错误");
        }
        LambdaQueryWrapper<Shop> wrapper = new LambdaQueryWrapper<Shop>()
                .eq(Shop::getEmail,email);
        if(shopMapper.selectOne(wrapper)==null){
            return Result.fail("用户不存在,请先注册");
        }
        stringRedisTemplate.delete(RedisKey.emailKey+email);
        LambdaUpdateWrapper<Shop> update = new LambdaUpdateWrapper<Shop>()
                .eq(Shop::getEmail,email)
                .set(Shop::getPassword,PasswordUtils.encrypt(newPassword));
        if(update(update)){
            return Result.ok("修改密码成功");
        }
        return Result.fail("发生未知错误,请重试");
    }

    @Override
    public Result updateShop(Shop shop) {
        if (!updateById(shop)) {
            return Result.fail("修改失败");
        }
        ShopHolder.removeShop();
        ShopHolder.saveShop(shop);
        return Result.ok("修改成功");
    }

    @Override
    public Result deleteShop(HttpServletRequest request) {
        Shop shop = ShopHolder.getShop();
        if (removeById(shop)) {
            String header = request.getHeader("Authorization");
            String token =  header.substring(7);
            stringRedisTemplate.delete(token);
            ShopHolder.removeShop();
            return Result.ok("删除成功");
        }
        return Result.fail("删除失败");
    }
}
