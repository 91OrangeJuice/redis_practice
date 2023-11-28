package com.tkp.learn.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tkp.learn.web.model.po.DictBranchEmployeePo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * author: itw_lixg01
 * date: 2020-06-08 09:50
 * description: 内勤机构码表dao层
 **/
@Mapper
@Repository
public interface DictBranchEmployeeMapper extends BaseMapper<DictBranchEmployeePo> {

    /**
     * 根据内勤OA号获取内勤所在机构简称
     * @param oaNo 内勤OA号
     * @return 所在机构简称
     */
    String getOrgShortNameByOaNo(String oaNo);
}
