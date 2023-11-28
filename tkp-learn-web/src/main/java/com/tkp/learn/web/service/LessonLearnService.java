package com.tkp.learn.web.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tkp.learn.web.model.bo.LessonLearnDurationBo;
import com.tkp.learn.web.model.vo.LearnHistoryCondition;
import com.tkp.learn.web.model.vo.LessonDetailVo;
import com.tkp.learn.web.model.vo.LessonItemVo;
import com.tkp.learn.web.model.vo.common.PageVo;

import java.util.Map;

/**
 * author: itw_lixg01
 * date: 2020-06-09 09:28
 * description: 课程学习情况处理
 **/

public interface LessonLearnService {

    Map<String, Integer> getLessonLearnersNumMap();

    Map<String, LessonLearnDurationBo> getLessonLearnDurationMap(String workNo);

    PageVo<LessonItemVo> getLearnHistory(Page<LessonItemVo> page, LearnHistoryCondition condition);

    int getLessonLearnersNumByLessonId(String lessonId);

    LessonDetailVo getLessonDetailByLessonIdAndWorkNo(String lessonId, String workNo);
}
