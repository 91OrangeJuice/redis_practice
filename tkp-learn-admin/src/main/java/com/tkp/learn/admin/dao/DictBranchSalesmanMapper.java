package com.tkp.learn.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tkp.learn.admin.model.po.DictBranchPo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * author: itw_liupeng01
 * date: 2020-07-28 11:01
 * description:
 **/
@Mapper
@Repository
public interface DictBranchSalesmanMapper extends BaseMapper<DictBranchPo> {
}
