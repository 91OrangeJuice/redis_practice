package com.tkp.learn.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tkp.learn.admin.model.common.LoginUser;
import com.tkp.learn.admin.model.po.EmployeePo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by itw_wangshuai01 on 2020/5/28.
 */

@Mapper
@Repository
public interface EmployeeMapper extends BaseMapper<EmployeePo> {

    LoginUser selectByOaNo(String oaNo);

    List<EmployeePo> queryEmployeePoByUserIds(@Param("userIds") List<String> userIds);

}
