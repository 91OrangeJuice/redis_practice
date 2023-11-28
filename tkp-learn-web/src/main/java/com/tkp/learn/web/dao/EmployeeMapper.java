package com.tkp.learn.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tkp.learn.web.model.bo.LessonLearnDurationBo;
import com.tkp.learn.web.model.bo.LessonLearnersNumBo;
import com.tkp.learn.web.model.bo.LoginUser;
import com.tkp.learn.web.model.po.EmployeePo;
import com.tkp.learn.web.model.vo.LearnHistoryCondition;
import com.tkp.learn.web.model.vo.LessonDetailVo;
import com.tkp.learn.web.model.vo.LessonItemVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by itw_wangshuai01 on 2020/5/28.
 */

@Mapper
@Repository
public interface EmployeeMapper extends BaseMapper<EmployeePo> {

    LoginUser selectByOaNo(String oaNo);

    List<LessonLearnDurationBo> getEmployeeLearnedDuration(String workNo);

    List<LessonLearnersNumBo> getEmployeeLessonLearnersNum();

    Page<LessonItemVo> getLearnHistory(Page<LessonItemVo> page, @Param("condition") LearnHistoryCondition condition);

    int getEmployeeLessonLearnersNumByLessonId(String lessonId);

    LessonDetailVo getLessonDetailByLessonIdAndOaNo(@Param("lessonId") String lessonId, @Param("oaNo") String oaNo);
}
