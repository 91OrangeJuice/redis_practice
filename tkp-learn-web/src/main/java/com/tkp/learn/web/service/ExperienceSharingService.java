package com.tkp.learn.web.service;

import com.tkp.learn.web.model.bo.PersonExperienceSharingBo;
import com.tkp.learn.web.model.vo.AddExperienceSharingVo;
import com.tkp.learn.web.model.vo.ExperienceSharingVo;
import com.tkp.learn.web.model.vo.PageParamVo;
import com.tkp.learn.web.model.vo.common.PageVo;
import org.apache.ibatis.annotations.Param;

/**
 * @author itw_liupeng01
 * @since 2020-06-05
 */
public interface ExperienceSharingService {
    int addExperienceSharing(AddExperienceSharingVo addExperienceSharingVo);

    PageVo getExperienceSharingList(PageParamVo<ExperienceSharingVo> pageParamVo);

    int deleteExperienceSharing(String courseExperienceId);

    int addPraises(String courseExperienceId);

    PageVo<PersonExperienceSharingBo> getMyLearnDataLessonExperienceList(PageParamVo pageParamVo);

    PersonExperienceSharingBo getThisLearnDataLessonExperience(String courseExperienceId);

}
