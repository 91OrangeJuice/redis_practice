package com.tkp.learn.archive.task.controller;

import com.tkp.learn.archive.task.model.po.common.ViewBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/7/20
 * @version: 1.0
 */
@RestController
@RequestMapping("/home")
@Slf4j
public class HomeController {

    @GetMapping("/sayHello")
    public ViewBean<String> sayHello(){
        return new ViewBean<>("hello world");
    }

}
