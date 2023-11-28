package com.tkp.learn.web.service;

import com.tkp.learn.web.model.vo.LearnHistoryCondition;
import com.tkp.learn.web.model.vo.LessonItemVo;
import com.tkp.learn.web.model.vo.PageParamVo;
import com.tkp.learn.web.model.vo.common.PageVo;

/**
 * author: itw_lixg01
 * date: 2020-06-11 09:19
 * description: 学习历史
 **/
public interface LearnHistoryService {
    PageVo<LessonItemVo> getLearnHistory(PageParamVo<LearnHistoryCondition> param);
}
