package com.tkp.learn.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient(autoRegister = false)
public class TkpLearnWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(TkpLearnWebApplication.class, args);
    }

}
