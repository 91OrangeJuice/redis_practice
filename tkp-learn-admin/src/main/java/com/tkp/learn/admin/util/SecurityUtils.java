package com.tkp.learn.admin.util;

import com.tkp.learn.admin.actuator.exception.CheckException;
import com.tkp.learn.admin.actuator.model.ErrorCode;
import com.tkp.learn.admin.remote.dto.tkpop.TkopUserDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    private SecurityUtils() {
        throw new IllegalStateException("SecurityUtils class");
    }


    /**
     * 获取当前登录的客户信息
     *
     * @return Customer
     */
    public static TkopUserDto getLoginUser() {
        Authentication subject = SecurityContextHolder.getContext().getAuthentication();
        if (subject == null) {
            throw new CheckException("用户信息失效，请重新登录！", ErrorCode.NO_LOGIN);
        }

        return (TkopUserDto) subject.getPrincipal();
    }

}
