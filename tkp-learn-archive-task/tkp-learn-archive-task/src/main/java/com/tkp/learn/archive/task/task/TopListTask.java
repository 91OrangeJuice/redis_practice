package com.tkp.learn.archive.task.task;

import com.tkp.learn.archive.task.dao.LogTaskLearnDataMapper;
import com.tkp.learn.archive.task.dao.TaskTopListMapper;
import com.tkp.learn.archive.task.enums.TaskNameEnum;
import com.tkp.learn.archive.task.enums.TaskStatusEnum;
import com.tkp.learn.archive.task.model.po.LogTaskLearnDataPo;
import com.tkp.learn.archive.task.model.po.TaskTopListPo;
import com.tkp.learn.archive.task.model.po.VideoBehaviorHisPo;
import com.tkp.learn.archive.task.service.HandleDayTaskService;
import com.tkp.learn.archive.task.utils.DateUtils;
import com.tkp.learn.archive.task.utils.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/6/5
 * @version: 1.0
 */
@Slf4j
@Component
public class TopListTask {
    @Autowired
    private HandleDayTaskService handleDayTaskService;
    @Autowired
    private LogTaskLearnDataMapper logTaskLearnDataMapper;
    @Autowired
    private TaskTopListMapper taskTopListMapper;

    @Scheduled(initialDelayString = "${task.topListTask.handleDayTask.initialDelay}", fixedRateString = "${task.topListTask.handleDayTask.fixedRate}")
    public void handleDayTask() {
        List<VideoBehaviorHisPo> videoBehaviorHisPos = null;
        LogTaskLearnDataPo initPo = logTaskLearnDataMapper.getLastTaskLogByCode(TaskNameEnum.INITDAYTASK.getCode(), TaskStatusEnum.FINISH.getCode());
        if (ObjectUtils.isEmpty(initPo)) {
            LOGGER.error("初始化历史任务未完成，下次再执行{}任务", TaskNameEnum.HANDLEDAYTASK.getName());
            return;
        }
        //1.查询临时表内是否有数据
        videoBehaviorHisPos = taskTopListMapper.queryVideoBehaviorHisTempPoPage(0, 1);
        if (ObjectUtils.isEmpty(videoBehaviorHisPos)) {
            LOGGER.error("无历史增量数据，不执行{}任务", TaskNameEnum.HANDLEDAYTASK.getName());
            return;
        }
        try {
            //3.执行日跑批任务
            if (!CollectionUtils.isEmpty(videoBehaviorHisPos)) {
                handleDayTaskService.handleDayTask();
            }
        } catch (Exception e) {
            LogTaskLearnDataPo errorPo = buildDefTaskPo(TaskNameEnum.HANDLEDAYTASK, null, null,TaskStatusEnum.EXCEPTION);
            logTaskLearnDataMapper.saveTaskLog(errorPo);
            LOGGER.error("当前任务{}，任务中止;日志：{},请及时处理", TaskStatusEnum.getNameByCode(errorPo.getStatus()), errorPo.toString(), e);
            return;
        }
    }

    @Scheduled(initialDelayString = "${task.topListTask.handleTopListTask.initialDelay}", fixedRateString = "${task.topListTask.handleTopListTask.fixedRate}")
    public void handleTopListTask() {
        LogTaskLearnDataPo newLogPo = null;
        try {
            //1.生成任务日志
            newLogPo = buildBeginTaskPo(TaskNameEnum.HANDLETOPLISTTASK, null, null);
            logTaskLearnDataMapper.saveTaskLog(newLogPo);
            //2.执行日跑批任务
            List<TaskTopListPo> pos = new ArrayList<>();
            //生成本月及上月日期
            createTopListTaskPos(pos);
            handleDayTaskService.handleDayTopList(pos);
            //3.完成任务更新任务状态
            updateLogTastStatus(newLogPo, TaskStatusEnum.FINISH);
        } catch (Exception e) {
            LogTaskLearnDataPo errorPo = updateLogTastStatus(newLogPo, TaskStatusEnum.EXCEPTION);
            LOGGER.error("当前任务{}，任务中止;日志：{},请及时处理", TaskStatusEnum.getNameByCode(errorPo.getStatus()), errorPo.toString(), e);
            return;
        }
    }

    private void createTopListTaskPos(List<TaskTopListPo> pos) {
        String nowStr = DateUtils.getStringByDate(new Date());
        String[] nowStrs = nowStr.substring(0, 10).split("-");
        int nowYear = Integer.valueOf(nowStrs[0]);
        int nowMonth = Integer.valueOf(nowStrs[1]);
        String lastMonth = DateUtils.getLastMonthDate(new Date());
        String[] lastMonths = lastMonth.substring(0, 10).split("-");
        int lastMonthYear = Integer.valueOf(lastMonths[0]);
        int lastMonthMonth = Integer.valueOf(lastMonths[1]);
        TaskTopListPo nowPo = new TaskTopListPo();
        nowPo.setYear(nowYear);
        nowPo.setMonth(nowMonth);
        pos.add(nowPo);
        TaskTopListPo lastMonthPo = new TaskTopListPo();
        lastMonthPo.setYear(lastMonthYear);
        lastMonthPo.setMonth(lastMonthMonth);
        pos.add(lastMonthPo);
    }

    private LogTaskLearnDataPo updateLogTastStatus(LogTaskLearnDataPo newLogPo, TaskStatusEnum taskStatusEnum) {
        newLogPo.setStatus(taskStatusEnum.getCode());
        newLogPo.setEndDate(new Date());
        logTaskLearnDataMapper.updateTaskLog(newLogPo);
        LOGGER.error("完成任务日志：{}", newLogPo.toString());
        LogTaskLearnDataPo po = logTaskLearnDataMapper.getTaskLogById(newLogPo.getId());
        return po;
    }

    private LogTaskLearnDataPo buildBeginTaskPo(TaskNameEnum taskEnmu, Date statisticalBeginTime, Date statisticalEndTime) {
        LogTaskLearnDataPo po = new LogTaskLearnDataPo();
        po.setId(UUIDUtils.generateUUID());
        po.setTaskName(taskEnmu.getName());
        po.setTaskCode(taskEnmu.getCode());
        po.setBeginDate(new Date());
        po.setStatus(TaskStatusEnum.WORKING.getCode());
        po.setStatisticalBeginTime(statisticalBeginTime);
        po.setStatisticalEndTime(statisticalEndTime);
        return po;
    }

    private LogTaskLearnDataPo buildDefTaskPo(TaskNameEnum taskEnmu, Date statisticalBeginTime, Date statisticalEndTime,TaskStatusEnum taskStatusEnum) {
        LogTaskLearnDataPo po = new LogTaskLearnDataPo();
        po.setId(UUIDUtils.generateUUID());
        po.setTaskName(taskEnmu.getName());
        po.setTaskCode(taskEnmu.getCode());
        po.setBeginDate(new Date());
        po.setStatus(taskStatusEnum.getCode());
        po.setStatisticalBeginTime(statisticalBeginTime);
        po.setStatisticalEndTime(statisticalEndTime);
        return po;
    }


}
