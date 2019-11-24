package com.monster.melon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @MapperScan("com.monster.melon.mapper")
public class MelonApplication {

    public static void main(String[] args) {
        SpringApplication.run(MelonApplication.class, args);
    }

}
