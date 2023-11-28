package com.tkp.learn.admin.service.serviceImpl;

import com.tkp.learn.admin.actuator.exception.ServiceException;
import com.tkp.learn.admin.dao.EmployeeMapper;
import com.tkp.learn.admin.model.common.LoginUser;
import com.tkp.learn.admin.model.enums.IdentityEnum;
import com.tkp.learn.admin.security.SecurityUser;
import com.tkp.learn.admin.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by itw_wangshuai01 on 2020/5/28.
 */

@Service("employee")
public class EmployeeService implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public SecurityUser loadUserByWorkNo(final String oaNo) {
        final LoginUser loginUser = employeeMapper.selectByOaNo(oaNo);
        if (loginUser == null) {
            LOGGER.error("根据工号[{}]未查询到此内勤员工！", oaNo);
            throw new ServiceException("本功能仅限内勤用户使用，请确认您的OA账号是否正常");
        }
        return new SecurityUser(loginUser, IdentityEnum.EMPLOYEE);
    }

}
