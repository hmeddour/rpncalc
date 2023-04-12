package com.exaltit.rpncalc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class RpncalcApplication {
    public static void main(String[] args) {
        SpringApplication.run(RpncalcApplication.class, args);
    }
}
