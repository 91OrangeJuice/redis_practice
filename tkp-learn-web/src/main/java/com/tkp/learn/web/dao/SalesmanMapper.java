package com.tkp.learn.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tkp.learn.web.model.bo.LessonLearnDurationBo;
import com.tkp.learn.web.model.bo.LessonLearnersNumBo;
import com.tkp.learn.web.model.bo.LoginUser;
import com.tkp.learn.web.model.po.SalesmanPo;
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
public interface SalesmanMapper extends BaseMapper<SalesmanPo> {

    LoginUser selectBySaleNo(String saleNo);

    List<LessonLearnDurationBo> getSalesmanLearnedDuration(String saleNo);

    List<LessonLearnersNumBo> getSalesmanLessonLearnersNum();

    Page<LessonItemVo> getLearnHistory(Page<LessonItemVo> page, @Param("condition") LearnHistoryCondition condition);

    int getSalesmanLessonLearnersNumByLessonId(String lessonId);

    LessonDetailVo getLessonDetailByLessonIdAndSaleNo(@Param("lessonId") String lessonId, @Param("saleNo") String saleNo);
}
