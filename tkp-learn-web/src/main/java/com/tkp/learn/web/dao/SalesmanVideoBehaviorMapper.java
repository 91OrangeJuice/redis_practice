package com.tkp.learn.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface SalesmanVideoBehaviorMapper extends BaseMapper<VideoBehaviorHisPo> {

    Integer getMonthLearningDurationByUserId(@Param("salesmanId") String salesmanId);

    Integer getTotalLearningDurationByUserId(@Param("salesmanId")String salesmanId);

    List<VideoBehaviorHisPo> getAllLearningDurationByDate(@Param("salesmanId")String salesmanId, @Param("year") int year, @Param("month") int month);

}
