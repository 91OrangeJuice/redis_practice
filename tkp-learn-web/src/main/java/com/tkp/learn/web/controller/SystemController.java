package com.tkp.learn.web.controller;

import com.tkp.learn.web.actuator.model.ViewBean;
import com.tkp.learn.web.model.vo.UserVo;
import com.tkp.learn.web.service.SystemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author: itw_lixg01
 * date: 2020-06-02 19:04
 * description:
 **/
@Slf4j
@RestController
@RequestMapping("/system")
public class SystemController {

    @Autowired
    private SystemService systemService;

    @GetMapping("/getCurrentUser.auth")
    public ViewBean<UserVo> getCurrentUser(){
        return ViewBean.createSuccess(systemService.getCurrentUser());
    }
}
