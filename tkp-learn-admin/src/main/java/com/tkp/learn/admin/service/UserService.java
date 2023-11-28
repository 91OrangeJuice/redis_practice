package com.tkp.learn.admin.service;


import com.tkp.learn.admin.security.SecurityUser;

/**
 * Created by itw_wangshuai01 on 2020/5/28.
 */
public interface UserService {

    SecurityUser loadUserByWorkNo(String workNo);
}
