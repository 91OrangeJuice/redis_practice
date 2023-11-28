package com.tkp.learn.web.service.serviceImpl;

import com.tkp.learn.web.model.bo.VideoBehaviorHisStatisticsBo;
import com.tkp.learn.web.model.vo.UserVo;
import com.tkp.learn.web.security.SecurityUser;
import com.tkp.learn.web.service.LearnDataPraisesService;
import com.tkp.learn.web.service.LearningDurationService;
import com.tkp.learn.web.service.SystemService;
import com.tkp.learn.web.service.factory.StrategyContext;
import com.tkp.learn.web.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author: itw_lixg01
 * date: 2020-06-02 19:08
 * description:
 **/
@Service
@Slf4j
public class SystemServiceImpl implements SystemService {

    @Autowired
    private StrategyContext<LearningDurationService> learningDurationServiceContext;
    @Autowired
    private LearnDataPraisesService learnDataPraisesService;

    @Override
    public UserVo getCurrentUser() {
        SecurityUser user = SecurityUtils.getLoginUser();
        int praisesCount = learnDataPraisesService.getCurrentUserNewPraiseCountByUserId(user.getWorkNo());

        LearningDurationService learningDurationService = learningDurationServiceContext.getStrategy(user.getIdentity().getCode());
        VideoBehaviorHisStatisticsBo learningDuration = learningDurationService.getLearningDuration(user.getWorkNo());

        return buildUserVo(user, praisesCount, learningDuration);
    }

    private UserVo buildUserVo(SecurityUser user, int praisesCount,
                               VideoBehaviorHisStatisticsBo learningDuration) {
        UserVo userVo = new UserVo();
        userVo.setUserCode(user.getWorkNo());
        userVo.setUserName(user.getName());
        userVo.setIconPath(user.getHeadImgUrl());
        userVo.setMonthDuration(learningDuration.getMonthDuration());
        userVo.setTotalDuration(learningDuration.getTotalDuration());
        userVo.setPraiseCount(praisesCount);
        userVo.setOrgShortName(user.getOrgShortName());

        return userVo;
    }
}
