package com.example.drama_kill_system.utils;

import com.example.drama_kill_system.entity.Shop;

public class ShopHolder {
    private static final ThreadLocal<Shop> tl = new ThreadLocal<>();

    public static void saveShop(Shop shop){
        tl.set(shop);
    }

    public static Shop getShop(){
        return tl.get();
    }

    public static void removeShop(){
        tl.remove();
    }
}
