package com.tkp.learn.archive.task.service.serviceImpl;

import com.tkp.learn.archive.task.dao.TaskTopListMapper;
import com.tkp.learn.archive.task.model.po.TaskTopListPo;
import com.tkp.learn.archive.task.model.po.VideoBehaviorHisPo;
import com.tkp.learn.archive.task.service.HandleDayTaskService;
import com.tkp.learn.archive.task.service.TaskTolListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;


/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/6/8
 * @version: 1.0
 */
@Service
@Slf4j
public class HandleDayTaskServiceImpl implements HandleDayTaskService {

    @Value("${task.topListTask.deviationValue}")
    private int deviationValue;

    @Autowired
    private TaskTolListService taskTolListService;
    @Autowired
    private TaskTopListMapper taskTopListMapper;

    @Override
    public void handleDayTask() throws Exception {
        //数据偏移量
        int start = 0;
        List<VideoBehaviorHisPo> bos = null;
        do {
            LOGGER.error("执行明细到年和月统计表,任务进度{}", start);
            bos = taskTopListMapper.queryVideoBehaviorHisTempPoPage(0, deviationValue);
            // 1.观看明细表lesson_statistics_salesman-》日统计表learn_data_video_day
            taskTolListService.handleDetailToMonthYear(bos);
            start += deviationValue;
        } while (!CollectionUtils.isEmpty(bos));
    }

    @Override
    public void handleDayTopList(List<TaskTopListPo> pos) throws Exception {
        for (TaskTopListPo po : pos) {
            // 1.处理月统计表learn_data_video_month-》月榜单表learn_video_month_top_list
            taskTolListService.handleDayToMonthTopList(po.getYear(), po.getMonth());
            // 2.处理年统计表learn_data_video_year--》年榜单表learn_video_year_top_list
            taskTolListService.handleDayToYearTopList(po.getYear());
        }
    }

    @Override
    public void initDayTask() throws Exception {
        //創建初始化表
        taskTopListMapper.initVideoBehVaviorHisBak();
        //数据偏移量
        int start = 0;
        List<VideoBehaviorHisPo> bos = null;
        do {
            LOGGER.error("执行明细到年和月统计表,任务进度{}", start);
            bos = taskTopListMapper.queryVideoBehaviorHisBakPoPage(0, deviationValue);
            // 1.观看明细表lesson_statistics_salesman-》日统计表learn_data_video_day
            taskTolListService.handleDetailToMonthYear4Init(bos);
            start += deviationValue;
        } while (!CollectionUtils.isEmpty(bos));
        List<VideoBehaviorHisPo> execptionPo = taskTopListMapper.queryVideoBehaviorBakException();
        taskTolListService.handleExceptionDataBak2Temp(execptionPo);
    }

}
