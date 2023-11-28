package com.tkp.learn.web.service;

import com.tkp.learn.web.actuator.aspect.ParamsValidated;
import com.tkp.learn.web.model.bo.PersonalLearnBo;

import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/6/2
 * @version: 1.0
 */
public interface LessonCountService {
    @ParamsValidated
    PersonalLearnBo getLessonCountAll(@NotNull String userId);

}
