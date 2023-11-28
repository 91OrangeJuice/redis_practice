package com.tkp.learn.archive.task.service;

import com.tkp.learn.archive.task.model.po.TaskTopListPo;
import com.tkp.learn.archive.task.model.po.VideoBehaviorHisPo;

import java.util.List;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/6/5
 * @version: 1.0
 */
public interface TaskTolListService {

    List<TaskTopListPo> getSalesDurationDetail(String begin, String end, int startIndex, int size);

    void handleDetailToMonthYear(List<VideoBehaviorHisPo> bos) throws Exception;

    void handleDetailToMonthYear4Init(List<VideoBehaviorHisPo> bos) throws Exception;

    void handleDayToMonthTopList(int year, int month) throws Exception;

    void handleDayToYearTopList(int year) throws Exception;

    void handleExceptionDataBak2Temp(List<VideoBehaviorHisPo> bos) throws Exception;

}
