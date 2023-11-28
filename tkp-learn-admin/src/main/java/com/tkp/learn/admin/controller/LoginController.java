package com.tkp.learn.admin.controller;


import com.tkp.learn.admin.actuator.exception.CheckException;
import com.tkp.learn.admin.actuator.model.ErrorCode;
import com.tkp.learn.admin.actuator.model.ViewBean;
import com.tkp.learn.admin.model.common.LoginUser;
import com.tkp.learn.admin.service.LoginService;
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
    public ViewBean<LoginUser> login(@RequestParam("tempToken") String tempToken) {
        if (StringUtils.isEmpty(tempToken)) {
            LOGGER.error("业务员登录泰运营系统时tempToken为空！[{}]", tempToken);
            throw new CheckException("tempToken为空！", ErrorCode.PARAM_ERROR);
        }
        LoginUser user=loginService.login(tempToken);
        if(StringUtils.isEmpty(user.getRoleName())){
            LOGGER.error("业务员[{}]无访问权限", user.toString());
            return ViewBean.createFail("无权限",ErrorCode.ILLEGAL_ROLE);
        }
        return ViewBean.createSuccess(user);
    }
}
