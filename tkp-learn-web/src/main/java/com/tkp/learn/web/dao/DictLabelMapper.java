package com.tkp.learn.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tkp.learn.web.model.po.DictLabelPo;
import com.tkp.learn.web.model.vo.LabelVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author: itw_lixg01
 * date: 2020-06-12 10:52
 * description:
 **/
@Mapper
@Repository
public interface DictLabelMapper extends BaseMapper<DictLabelPo> {
    List<LabelVo> getLabels();
}
