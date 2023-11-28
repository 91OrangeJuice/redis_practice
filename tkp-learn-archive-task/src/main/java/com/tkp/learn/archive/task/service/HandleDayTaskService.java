package com.tkp.learn.archive.task.service;

import com.tkp.learn.archive.task.model.po.TaskTopListPo;

import java.util.List;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/6/8
 * @version: 1.0
 */
public interface HandleDayTaskService {
    void handleDayTask() throws Exception;

    void handleDayTopList(List<TaskTopListPo> pos) throws Exception;

    void initDayTask() throws Exception;
}
