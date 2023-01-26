package com.example.drama_kill_system.controller.Shop;

import com.example.drama_kill_system.entity.Shop;
import com.example.drama_kill_system.result.Result;
import com.example.drama_kill_system.utils.ShopHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop/drama")
public class ShopDramaController {
    @GetMapping("/test")
    public Result test(){
        Shop shop = ShopHolder.getShop();
        System.out.println(shop);
        return Result.ok();
    }
}
