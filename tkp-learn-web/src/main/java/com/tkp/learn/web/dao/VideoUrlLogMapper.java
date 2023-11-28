package com.tkp.learn.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tkp.learn.web.model.po.VideoUrlLogPo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * author: itw_lixg01
 * date: 2020-04-29 14:39
 * description:
 */
@Mapper
@Repository
public interface VideoUrlLogMapper extends BaseMapper<VideoUrlLogPo> {
}
