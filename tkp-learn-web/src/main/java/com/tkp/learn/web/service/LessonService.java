package com.tkp.learn.web.service;

import com.tkp.learn.web.model.vo.LessonDetailVo;
import com.tkp.learn.web.model.vo.LessonItemVo;
import com.tkp.learn.web.model.vo.LessonListCondition;
import com.tkp.learn.web.model.vo.PageParamVo;
import com.tkp.learn.web.model.vo.common.PageVo;

/**
 * author: itw_lixg01
 * date: 2020-06-04 20:27
 * description:
 **/
public interface LessonService {

    PageVo<LessonItemVo> getLessonList(PageParamVo<LessonListCondition> param);

    LessonDetailVo getLessonDetail(String lessonId);
}
