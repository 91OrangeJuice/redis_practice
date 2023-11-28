package com.tkp.learn.web.service;

import com.tkp.learn.web.actuator.aspect.ParamsValidated;
import com.tkp.learn.web.model.bo.VideoBehaviorHisStatisticsBo;

import javax.validation.constraints.NotNull;

/**
 * @description: 获取学习时长接口（月时长，总时长）
 * @author: itw_wangsc01
 * @createDate: 2020/6/1
 * @version: 1.0
 */
public interface LearningDurationService {
    @ParamsValidated
    int getCurrentMonthLearningDuration(@NotNull String userId);
    @ParamsValidated
    int getTotalLearningDuration(@NotNull String userId);
    @ParamsValidated
    VideoBehaviorHisStatisticsBo getLearningDuration(@NotNull String userId);

    VideoBehaviorHisStatisticsBo getLearningDurationByDate(@NotNull String userId,int year,int month);





}
