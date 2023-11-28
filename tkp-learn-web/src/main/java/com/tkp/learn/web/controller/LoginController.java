package com.tkp.learn.web.controller;

import com.tkp.learn.web.actuator.exception.CheckException;
import com.tkp.learn.web.actuator.model.ErrorCode;
import com.tkp.learn.web.actuator.model.ViewBean;
import com.tkp.learn.web.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by itw_wangshuai01 on 2020/5/28.
 */

@Controller
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    @GetMapping(value = "/login")
    @ResponseBody
    public ViewBean<String> login(@RequestParam("tempToken") String tempToken) {
        if (StringUtils.isEmpty(tempToken)) {
            LOGGER.error("业务员登录卡券系统时tempToken为空！[{}]", tempToken);
            throw new CheckException("tempToken为空！", ErrorCode.PARAM_ERROR);
        }
        return ViewBean.createSuccess(loginService.login(tempToken));
    }
}
