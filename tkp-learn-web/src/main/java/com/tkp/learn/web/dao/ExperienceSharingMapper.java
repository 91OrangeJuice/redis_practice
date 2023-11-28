package com.tkp.learn.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tkp.learn.web.model.bo.ExperienceSharingBo;
import com.tkp.learn.web.model.bo.PersonExperienceSharingBo;
import com.tkp.learn.web.model.po.LearnDataLessonExperiencePo;
import com.tkp.learn.web.model.vo.ExperienceSharingVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author itw_liupeng01
 * @since 2020-06-05
 */
@Repository
@Mapper
public interface ExperienceSharingMapper extends BaseMapper<LearnDataLessonExperiencePo> {

    int addWonPraiseNum(String courseExperienceId);

    IPage<ExperienceSharingBo> getExperienceSharingList(Page<ExperienceSharingBo> ewxperienceSharingBoPag, ExperienceSharingVo experienceSharingVo);

    IPage<PersonExperienceSharingBo> getMyLearnDataLessonExperienceList(Page<PersonExperienceSharingBo> ewxperienceSharingBoPag,@Param("workNo") String workNo);

    PersonExperienceSharingBo getThisLearnDataLessonExperience(@Param("courseExperienceId") String courseExperienceId);

}
