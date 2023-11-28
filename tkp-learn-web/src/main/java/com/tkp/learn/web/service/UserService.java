package com.tkp.learn.web.service;

import com.tkp.learn.web.security.SecurityUser;

/**
 * Created by itw_wangshuai01 on 2020/5/28.
 */
public interface UserService {

    SecurityUser loadUserByWorkNo(String workNo);
}
