package com.tkp.learn.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tkp.learn.web.model.po.LearnVideoMonthTopListPo;
import com.tkp.learn.web.model.po.LearnVideoYearTopListPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/6/3
 * @version: 1.0
 */
@Mapper
@Repository
public interface LearnVideoYearTopListMapper extends BaseMapper<LearnVideoYearTopListPo> {

    LearnVideoYearTopListPo getUserYearRankByUserYearMonth(@Param("saleNo") String saleNo, @Param("year") int year, @Param("unit") String unit);

    List<LearnVideoYearTopListPo> getYearTopListByYearMonth(@Param("year") int year, @Param("startIndex") int startIndex, @Param("count") int count, @Param("order") String order, @Param("unit") String unit);

    Page<LearnVideoYearTopListPo> getYearTopPageByYearMonth(@Param("page") Page page, @Param("year") int year, @Param("order") String order, @Param("unit") String unit);

    int getYearTopCountByYearMonth(@Param("year") int year, @Param("unit") String unit);
}
