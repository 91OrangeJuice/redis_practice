package com.tkp.learn.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tkp.learn.admin.model.po.DictLearningStylePo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author itw_liupeng01
 * @since 2020-08-03
 */
@Mapper
@Repository
public interface DictLearningStyleMapper extends BaseMapper<DictLearningStylePo> {

}
