package com.tkp.learn.pushcdn.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient(autoRegister = false)
@EnableScheduling
public class TkpLearnPushcdnTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(TkpLearnPushcdnTaskApplication.class, args);
    }

}
