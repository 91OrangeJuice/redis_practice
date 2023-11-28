package com.tkp.learn.archive.task;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/6/5
 * @version: 1.0
 */
@SpringBootApplication
@EnableScheduling
public class TkpLearnArchiveTaskApplication {
    public static void main(String[] args) {
        SpringApplication.run(TkpLearnArchiveTaskApplication.class, args);
    }
}
