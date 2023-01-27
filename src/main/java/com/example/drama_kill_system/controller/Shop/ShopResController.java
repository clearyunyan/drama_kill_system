package com.example.drama_kill_system.controller.Shop;

import com.example.drama_kill_system.entity.dto.ShopLoginDTO;
import com.example.drama_kill_system.result.Result;
import com.example.drama_kill_system.service.IShop.IShopService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController()
@RequestMapping("/shop/shopRes")
public class ShopResController {
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
    private Result res(@RequestBody ShopLoginDTO shopLoginDTO){
    return iShopService.res(shopLoginDTO);
}
    @GetMapping("/login")
    private Result shopLogin(String email,String password ){
    return iShopService.login(email,password);
    }
    @GetMapping("/logout")
    private Result shopLogout(HttpServletRequest request){
        return iShopService.logout(request);
    }
    @PutMapping("/changePassword")
    private Result changePassword(@RequestParam("oldPassword") String oldPassword,@RequestParam("newPassword") String newPassword){
        return iShopService.changePassword(oldPassword,newPassword);
    }
    @PutMapping("/changePasswordByEmail")
    private Result changePasswordByEmail(String code,String email,String newPassword){
        return iShopService.changePasswordByEmail(code,email,newPassword);
    }
}
