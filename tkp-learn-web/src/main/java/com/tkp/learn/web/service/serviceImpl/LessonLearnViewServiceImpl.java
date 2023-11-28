package com.tkp.learn.web.service.serviceImpl;

import com.tkp.learn.web.model.bo.LearnVideoTopListBo;
import com.tkp.learn.web.model.bo.PersonalLearnBo;
import com.tkp.learn.web.model.bo.VideoBehaviorHisStatisticsBo;
import com.tkp.learn.web.model.vo.MyLearningDetailVo;
import com.tkp.learn.web.model.vo.PageParamVo;
import com.tkp.learn.web.model.vo.RankingListPage;
import com.tkp.learn.web.model.vo.RankingListVo;
import com.tkp.learn.web.security.SecurityUser;
import com.tkp.learn.web.service.*;
import com.tkp.learn.web.service.factory.StrategyContext;
import com.tkp.learn.web.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/6/15
 * @version: 1.0
 */
@Service
public class LessonLearnViewServiceImpl implements LessonLearnViewService {

    @Autowired
    private StrategyContext<LessonCountService> lessonCountServiceContext;
    @Autowired
    private StrategyContext<LearningDurationService> learningDurationServiceContext;
    @Autowired
    private LearnVideoMonthTopListService learnVideoMonthTopListService;
    @Autowired
    private LearnVideoYearTopListService learnVideoYearTopListService;
    @Autowired
    private LearnDataPraisesService learnDataPraisesService;

    @Override
    public MyLearningDetailVo getMyLearningDetail() {
        MyLearningDetailVo mlVo = new MyLearningDetailVo();
        SecurityUser localUser = SecurityUtils.getLoginUser();
        String userId = localUser.getWorkNo();
        LearningDurationService learningDurationService = learningDurationServiceContext.getStrategy(localUser.getIdentity().getCode());
        VideoBehaviorHisStatisticsBo vbhBo = learningDurationService.getLearningDuration(userId);
        LessonCountService lessonCountService = lessonCountServiceContext.getStrategy(localUser.getIdentity().getCode());
        PersonalLearnBo lcbo = lessonCountService.getLessonCountAll(userId);
        LearnVideoTopListBo monthTopBo = learnVideoMonthTopListService.getCurrentMonthRankByUserId(userId);
        LearnVideoTopListBo yearTopBo = learnVideoYearTopListService.getCurrentYearRankByUserId(userId);
        int praisesCount = learnDataPraisesService.getCurrentUserNewPraiseCountByUserId(userId);
        buildMyLearningDetailVo(mlVo, vbhBo, lcbo, monthTopBo, yearTopBo, praisesCount, localUser);
        return mlVo;
    }

    @Override
    public RankingListVo getRankingListMonth(PageParamVo<RankingListPage> rankingListPage) {
        RankingListVo vo = learnVideoMonthTopListService.getCurrentMonthTopPage(rankingListPage);
        SecurityUser localUser = SecurityUtils.getLoginUser();
        String userId = localUser.getWorkNo();
        LearningDurationService learningDurationService = learningDurationServiceContext.getStrategy(localUser.getIdentity().getCode());
        VideoBehaviorHisStatisticsBo vbhBo = learningDurationService.getLearningDurationByDate(userId, rankingListPage.getCondition().getYear(), rankingListPage.getCondition().getMonth());
        LearnVideoTopListBo monthTopBo = learnVideoMonthTopListService.getMonthRankByUserIdDate(userId, rankingListPage.getCondition().getYear(), rankingListPage.getCondition().getMonth());
        buildMonthRankingListVo(vo, vbhBo, monthTopBo, localUser);
        return vo;
    }

    @Override
    public RankingListVo getRankingListYear(PageParamVo<RankingListPage> rankingListPage) {
        RankingListVo vo = learnVideoYearTopListService.getCurrentYearTopPage(rankingListPage);
        SecurityUser localUser = SecurityUtils.getLoginUser();
        String userId = localUser.getWorkNo();
        LearningDurationService learningDurationService = learningDurationServiceContext.getStrategy(localUser.getIdentity().getCode());
        VideoBehaviorHisStatisticsBo vbhBo = learningDurationService.getLearningDurationByDate(userId, rankingListPage.getCondition().getYear(), rankingListPage.getCondition().getMonth());
        LearnVideoTopListBo yearTopBo = learnVideoYearTopListService.getYearRankByUserIdDate(userId, rankingListPage.getCondition().getYear());
        buildYearRankingListVo(vo, vbhBo, yearTopBo, localUser);
        return vo;
    }

    private void buildMyLearningDetailVo(MyLearningDetailVo mlVo, VideoBehaviorHisStatisticsBo vbhBo
            , PersonalLearnBo lcbo, LearnVideoTopListBo monthTopBo, LearnVideoTopListBo yearTopBo
            , int praisesCount, SecurityUser localUser) {
        if (null == mlVo) {
            mlVo = new MyLearningDetailVo();
        }
        mlVo.setUserCode(localUser.getWorkNo());
        mlVo.setUserName(localUser.getUsername());
        mlVo.setIconPath(localUser.getHeadImgUrl());
        mlVo.setOrgName(localUser.getOrgName());
        mlVo.setOrgShortName(localUser.getOrgShortName());
        mlVo.setMonthDuration(vbhBo.getMonthDuration());
        mlVo.setTotalDuration(vbhBo.getTotalDuration());
        mlVo.setFinishLessonCount(lcbo.getFinishLessonCount());
        mlVo.setTotalLessonCount(lcbo.getTotalLessonCount());
        mlVo.setRequiredLessonCount(lcbo.getRequiredLessonCount());
        mlVo.setRequiredFinishLessonCount(lcbo.getRequiredFinishLessonCount());
        mlVo.setMonthRanking(monthTopBo.getRanking());
        mlVo.setYearRanking(yearTopBo.getRanking());
        mlVo.setPraiseCount(praisesCount);
    }


    private void buildMonthRankingListVo(RankingListVo mlVo, VideoBehaviorHisStatisticsBo vbhBo, LearnVideoTopListBo topBo, SecurityUser localUser) {
        if (null == mlVo) {
            mlVo = new RankingListVo();
        }
        mlVo.setUserName(localUser.getUsername());
        mlVo.setIconPath(localUser.getHeadImgUrl());
        mlVo.setMyRank(topBo.getRanking());
        mlVo.setStudyDuration(vbhBo.getMonthDuration());
        mlVo.setOrgShortName(localUser.getOrgShortName());
    }

    private void buildYearRankingListVo(RankingListVo mlVo, VideoBehaviorHisStatisticsBo vbhBo, LearnVideoTopListBo topBo, SecurityUser localUser) {
        if (null == mlVo) {
            mlVo = new RankingListVo();
        }
        mlVo.setUserName(localUser.getUsername());
        mlVo.setIconPath(localUser.getHeadImgUrl());
        mlVo.setMyRank(topBo.getRanking());
        mlVo.setStudyDuration(vbhBo.getYearDuration());
        mlVo.setOrgShortName(localUser.getOrgShortName());
    }

}
