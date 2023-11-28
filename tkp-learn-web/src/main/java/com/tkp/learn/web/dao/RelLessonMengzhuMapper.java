package com.tkp.learn.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tkp.learn.web.model.po.LearnLessonPo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RelLessonMengzhuMapper extends BaseMapper<LearnLessonPo> {
    String selectByVideoId(String videoId);

}
