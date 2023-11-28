package com.tkp.learn.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tkp.learn.web.model.po.DictBranchPo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * author: itw_lixg01
 * date: 2020-06-08 11:01
 * description:
 **/
@Mapper
@Repository
public interface DictBranchSalesmanMapper extends BaseMapper<DictBranchPo> {
    String getOrgShortNameBySaleNo(String saleNo);
}
