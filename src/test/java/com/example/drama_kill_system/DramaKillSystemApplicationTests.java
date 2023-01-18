package com.example.drama_kill_system;

import com.example.drama_kill_system.mapper.ApplicationMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DramaKillSystemApplicationTests {

    @Autowired
    private ApplicationMapper applicationMapper;


    @Test
    void contextLoads() {
        System.out.println(applicationMapper.selectById(1));
        System.out.println("哈哈哈哈");
    }

}
