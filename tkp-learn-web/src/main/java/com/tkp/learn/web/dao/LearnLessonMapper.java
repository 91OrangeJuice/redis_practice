package com.tkp.learn.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tkp.learn.web.model.bo.HotLessonBo;
import com.tkp.learn.web.model.bo.LessonLearnersNumBo;
import com.tkp.learn.web.model.po.LearnLessonPo;
import com.tkp.learn.web.model.vo.LessonItemVo;
import com.tkp.learn.web.model.vo.LessonListCondition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.time.LocalDateTime;

/**
 * @author itw_liupeng01
 * @since 2020-06-05
 */
@Mapper
@Repository
public interface LearnLessonMapper extends BaseMapper<LearnLessonPo> {
    String queryBaseFileNameById(String uuid);

    Page<LessonItemVo> getLessonsBy(Page<LessonItemVo> page, @Param("condition") LessonListCondition condition);

    Page<LessonItemVo> getLessonsByNameAndLabelCode(Page<LessonItemVo> page, @Param("condition") LessonListCondition condition);

   int  updateByLessonId(String lessonId, LocalDateTime liveEndTime,int duration);

    List<LessonLearnersNumBo> getSalesmanLearnersNumLast30Days();

    List<LessonLearnersNumBo> getEmployeeLearnersNumLast30Days();

    HotLessonBo getLessonByLessonId(String lessonId);

    List<String> getAllLessonIds();
}
