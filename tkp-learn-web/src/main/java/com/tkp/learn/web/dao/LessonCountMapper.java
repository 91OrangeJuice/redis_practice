package com.tkp.learn.web.dao;

import com.tkp.learn.web.model.po.LessonCountPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/6/2
 * @version: 1.0
 */
@Mapper
@Repository
public interface LessonCountMapper {
    //获取内勤课程数
    List<LessonCountPo> getLessonCountListByOaNo(@Param("oaNo") String oaNo);
    //获取外勤课程数
    List<LessonCountPo> getLessonCountListBySaleNo(@Param("saleNo") String saleNo);

}
