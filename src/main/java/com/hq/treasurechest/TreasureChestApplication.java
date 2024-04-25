package com.hq.treasurechest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hq.treasurechest.mapper")
public class TreasureChestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TreasureChestApplication.class, args);
    }

}
