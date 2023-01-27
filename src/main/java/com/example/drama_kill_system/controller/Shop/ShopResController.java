package com.example.drama_kill_system.controller.Shop;

import com.example.drama_kill_system.entity.Shop;
import com.example.drama_kill_system.entity.dto.ShopLoginDTO;
import com.example.drama_kill_system.result.Result;
import com.example.drama_kill_system.service.IShop.IShopService;
import com.example.drama_kill_system.utils.ShopHolder;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController()
@RequestMapping("/shop/shopRes")
public class ShopResController {
    @Resource
    private IShopService iShopService;
    //发送验证码
    @GetMapping("/getCode")
    private Result sendEmail(String email){
    if(iShopService.sendEmail(email)){
        return Result.ok("验证码发送成功");
    }
    return Result.fail("验证码发送失败");
    }
    //注册
    @PostMapping("/res")
    private Result res(@RequestBody ShopLoginDTO shopLoginDTO){
    return iShopService.res(shopLoginDTO);
}
    //登录
    @GetMapping("/login")
    private Result shopLogin(String email,String password ){
    return iShopService.login(email,password);
    }
    //登出
    @GetMapping("/logout")
    private Result shopLogout(HttpServletRequest request){
        return iShopService.logout(request);
    }
    //改密码
    @PutMapping("/changePassword")
    private Result changePassword(@RequestParam("oldPassword") String oldPassword,@RequestParam("newPassword") String newPassword){
        return iShopService.changePassword(oldPassword,newPassword);
    }
    //发邮件改密码,需要先发邮件
    @PutMapping("/changePasswordByEmail")
    private Result changePasswordByEmail(String code,String email,String newPassword){
        return iShopService.changePasswordByEmail(code,email,newPassword);
    }
    @GetMapping("/getShop")
    private Result getShop(){
        return Result.ok(ShopHolder.getShop());
    }
    @PutMapping ("/update")
    private Result updateShop(@RequestBody Shop shop){
        return iShopService.updateShop(shop);
    }
    @DeleteMapping("/delete")
    private Result deleteShop(HttpServletRequest request){
        return iShopService.deleteShop(request);
    }
}
