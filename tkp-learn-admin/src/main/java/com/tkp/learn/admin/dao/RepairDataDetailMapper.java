package com.tkp.learn.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tkp.learn.admin.model.bo.LessonBo;
import com.tkp.learn.admin.model.bo.RepairDataDetailBo;
import com.tkp.learn.admin.model.bo.StatisticsDataBo;
import com.tkp.learn.admin.model.po.*;
import com.tkp.learn.admin.model.vo.QueryDataParamVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author itw_liupeng01
 * @since 2020-07-31
 */
@Mapper
@Repository
public interface RepairDataDetailMapper extends BaseMapper<RepairDataDetailPo> {

    IPage<RepairDataDetailBo> getRepairDataDetailSearchList(Page<RepairDataDetailBo> Page, @Param("queryDataParamVo") QueryDataParamVo queryDataParamVo);

    void insertRepairDataDetail(@Param("param") List<RepaireDataPo> param);

    void updateRepairDataDetail(@Param("param") List<RepaireDataPo> param);

    List<RepaireDataPo> queryRepairDataDetail(@Param("ids") List<String> ids);

    StatisticsDataBo getSaleStatistics(@Param("lessonId") String lessonId, @Param("userId") String userId);

    StatisticsDataBo getEmployeeStatistics(@Param("lessonId") String lessonId, @Param("userId") String userId);

    List<LessonBo> getLessonByIds(@Param("ids") List<String> ids);

    void inserVideoBehavior(@Param("po") VideoBehaviorPo videoBehaviorPo);

    void inserEmployeeVideoBehavior(@Param("po") EmployeeVideoBehaviorPo employeeVideoBehaviorPo);

    void saveOperation(@Param("param") List<OperationPo> param);

}
