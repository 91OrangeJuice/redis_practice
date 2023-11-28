package com.tkp.learn.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/7/23
 * @version: 1.0
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient(autoRegister = false)
public class TkpLearnAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(TkpLearnAdminApplication.class, args);
    }

}
