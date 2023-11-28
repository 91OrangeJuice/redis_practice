package com.tkp.learn.archive.task.dao;

import com.tkp.learn.archive.task.model.po.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/6/4
 * @version: 1.0
 */
@Mapper
@Repository
public interface TaskTopListMapper {

    List<LearnDataVideoDayPo> getMonthTopListFromLearnDataVideoMonth(@Param("year") int year, @Param("month") int month,@Param("startIndex") int startIndex, @Param("size") int size);

    List<LearnDataVideoDayPo> getYearTopListFromViewLearnDataVideoYear(@Param("startIndex") int startIndex, @Param("size") int size, @Param("year") int year);

    // 月表處理
    LearnDataVideoMonthPo queryLearnDataVideoMonth(@Param("year") int year, @Param("month") int month,@Param("saleNo") String saleNo);

    void insertLearnDataVideoMonth(@Param("pos") List<LearnDataVideoMonthPo> pos);

    void updateLearnDataVideoMonthById(@Param("pos")List<LearnDataVideoMonthPo> pos);

    int deleteLearnDataVideoMonth(@Param("year") int year, @Param("month") int month);
    //年表處理
    LearnDataVideoYearPo queryLearnDataVideoYear(@Param("year") int year,@Param("saleNo") String saleNo);

    void insertLearnDataVideoYear(@Param("pos") List<LearnDataVideoYearPo> pos);

    void updateLearnDataVideoYearById(@Param("pos") List<LearnDataVideoYearPo> pos);
    //月榜處理
    void insertMonthTopList(@Param("pos") List<MonthTaskTopListPo> pos);

    int deleteMonthTopList(@Param("year") int year, @Param("month") int month);
    //年榜處理
    void insertYearTopList(@Param("pos") List<YearTaskTopListPo> pos);

    int deleteYearTopList(@Param("year") int year);
    //獲取明細歷史
    List<TaskTopListPo> getSalesDurationDetail(@Param("startIndex") int startIndex, @Param("size") int size);

    List<VideoBehaviorHisPo> queryVideoBehaviorHisTempPoPage(@Param("startIndex") int startIndex, @Param("size") int size);

    List<VideoBehaviorHisPo> queryVideoBehaviorTempException();

    List<VideoBehaviorHisPo> queryVideoBehaviorBakException();

    void insertVideoBehaviorHis(@Param("pos")List<VideoBehaviorHisPo> po);

    void insertVideoBehaviorHisTemp(@Param("pos")List<VideoBehaviorHisPo> po);

    void deleteVideoBehaviorHisTemp(@Param("pos")List<VideoBehaviorHisPo> po);

    void deleteVideoBehaviorHisBak(@Param("pos")List<VideoBehaviorHisPo> po);

    List<VideoBehaviorHisPo> queryVideoBehaviorHisBakPoPage(@Param("startIndex") int startIndex, @Param("size") int size);

    void initVideoBehVaviorHisBak();

}
