package com.tkp.learn.web.service;

import com.tkp.learn.web.actuator.aspect.ParamsValidated;

import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/6/3
 * @version: 1.0
 */
public interface LearnDataPraisesService {
    @ParamsValidated
    int getCurrentUserNewPraiseCountByUserId(@NotNull String userId);

}
