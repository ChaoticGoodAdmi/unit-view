package com.example.unitview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class UnitViewApplication {

    public static void main(String[] args) {
        SpringApplication.run(UnitViewApplication.class, args);
    }

}
