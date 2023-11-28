package com.tkp.learn.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tkp.learn.admin.model.bo.LessonBo;
import com.tkp.learn.admin.model.po.LearnLessonPo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author itw_liupeng01
 * @since 2020-07-29
 */
@Mapper
@Repository
public interface LearnLessonMapper extends BaseMapper<LearnLessonPo> {

    List<LessonBo> getLessonList();

}
