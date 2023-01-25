package com.example.drama_kill_system.controller.Shop;

import com.example.drama_kill_system.entity.dto.ShopDTO;
import com.example.drama_kill_system.result.Result;
import com.example.drama_kill_system.service.IShop.IShopService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController("/shop/shop")
public class ShopController {
    @Resource
    private IShopService iShopService;
@GetMapping("/getCode")
    private Result sendEmail(String email){
    if(iShopService.sendEmail(email)){
        return Result.ok("验证码发送成功");
    }
    return Result.fail("验证码发送失败");
}
@PostMapping("/res")
    private Result res(@RequestBody ShopDTO shopDTO){
    return iShopService.res(shopDTO);

}
}
