package com.tkp.learn.web.service;

import com.tkp.learn.web.actuator.aspect.ParamsValidated;
import com.tkp.learn.web.model.bo.LearnVideoTopListBo;
import com.tkp.learn.web.model.vo.PageParamVo;
import com.tkp.learn.web.model.vo.RankingListPage;
import com.tkp.learn.web.model.vo.RankingListVo;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/6/3
 * @version: 1.0
 */
public interface LearnVideoYearTopListService {
    @ParamsValidated
    LearnVideoTopListBo getCurrentYearRankByUserId(@NotNull String userId);

    List<LearnVideoTopListBo> getCurrentYearTopList();

    RankingListVo getCurrentYearTopPage(PageParamVo<RankingListPage> rankingListPage);

    LearnVideoTopListBo getYearRankByUserIdDate(String userId, int year);
}
