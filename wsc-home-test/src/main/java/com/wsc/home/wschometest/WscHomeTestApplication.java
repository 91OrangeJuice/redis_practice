package com.wsc.home.wschometest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringCloudApplication
@EnableDiscoveryClient(autoRegister = false)
@EnableCircuitBreaker
public class WscHomeTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(WscHomeTestApplication.class, args);
    }

}
