package com.tkp.learn.admin.service.serviceImpl;

import com.tkp.learn.admin.actuator.exception.ServiceException;
import com.tkp.learn.admin.dao.SalesmanMapper;
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

@Service("salesman")
public class SalesmanService implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SalesmanService.class);

    @Autowired
    private SalesmanMapper salesmanMapper;

    @Override
    public SecurityUser loadUserByWorkNo(final String saleNo) {
        final LoginUser loginUser = salesmanMapper.selectBySaleNo(saleNo);
        if (loginUser == null) {
            LOGGER.error("根据工号[{}]未查询到此业务员！", saleNo);
            throw new ServiceException("请使用主账户登录泰易销后进行学习，感谢您的配合");
        }
        return new SecurityUser(loginUser, IdentityEnum.SALESMAN);
    }

}
