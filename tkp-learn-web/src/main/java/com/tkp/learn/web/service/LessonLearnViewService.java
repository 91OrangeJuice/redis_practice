package com.tkp.learn.web.service;

import com.tkp.learn.web.model.vo.MyLearningDetailVo;
import com.tkp.learn.web.model.vo.PageParamVo;
import com.tkp.learn.web.model.vo.RankingListPage;
import com.tkp.learn.web.model.vo.RankingListVo;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/6/15
 * @version: 1.0
 */
public interface LessonLearnViewService {

    MyLearningDetailVo getMyLearningDetail();

    RankingListVo getRankingListMonth(PageParamVo<RankingListPage> rankingListPage);

    RankingListVo getRankingListYear(PageParamVo<RankingListPage> rankingListPage);

}
