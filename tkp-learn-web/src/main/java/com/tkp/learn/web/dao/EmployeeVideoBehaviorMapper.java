package com.tkp.learn.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tkp.learn.web.model.po.EmployeeVideoBehaviorHisPo;
import com.tkp.learn.web.model.po.VideoBehaviorHisPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/6/1
 * @version: 1.0
 */
@Mapper
@Repository
public interface EmployeeVideoBehaviorMapper extends BaseMapper<EmployeeVideoBehaviorHisPo> {

    Integer getMonthLearningDurationByUserId(@Param("oaNo") String oaNo);

    Integer getTotalLearningDurationByUserId(@Param("oaNo") String oaNo);

    List<VideoBehaviorHisPo> getAllLearningDurationByDate(@Param("oaNo") String oaNo, @Param("year") int year, @Param("month") int month);

}
