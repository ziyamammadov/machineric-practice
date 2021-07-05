package com.machineric.machinericpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableFeignClients
public class MachinericPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MachinericPracticeApplication.class, args);
    }

}
