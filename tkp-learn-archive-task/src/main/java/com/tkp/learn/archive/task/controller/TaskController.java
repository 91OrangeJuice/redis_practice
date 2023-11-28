package com.tkp.learn.archive.task.controller;

import com.tkp.learn.archive.task.dao.LogTaskLearnDataMapper;
import com.tkp.learn.archive.task.enums.TaskNameEnum;
import com.tkp.learn.archive.task.enums.TaskStatusEnum;
import com.tkp.learn.archive.task.model.po.LogTaskLearnDataPo;
import com.tkp.learn.archive.task.model.po.TaskTopListPo;
import com.tkp.learn.archive.task.model.po.common.ErrorCode;
import com.tkp.learn.archive.task.model.po.common.ViewBean;
import com.tkp.learn.archive.task.service.HandleDayTaskService;
import com.tkp.learn.archive.task.utils.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/6/24
 * @version: 1.0
 */
@RestController
@RequestMapping("/task")
@Slf4j
public class TaskController {

    @Autowired
    private HandleDayTaskService handleDayTaskService;
    @Autowired
    private LogTaskLearnDataMapper logTaskLearnDataMapper;

    @PostMapping("/handleTopListTask")
    public ViewBean<String> handleTopListTask(@RequestBody List<TaskTopListPo> pos) {
        try {
            handleDayTaskService.handleDayTopList(pos);
        } catch (Exception e) {
            LOGGER.error("手动榜单处理",e);
            return ViewBean.createFail("处理失败", ErrorCode.PARAM_ERROR);
        }
        return ViewBean.createSuccess("处理成功");
    }

    @GetMapping("/initDayTask")
    public ViewBean<String> initDayTask() {
        try {
            LogTaskLearnDataPo initPo = logTaskLearnDataMapper.getLastTaskLogByCodeSingle(TaskNameEnum.INITDAYTASK.getCode());
            if (!ObjectUtils.isEmpty(initPo) && TaskStatusEnum.EXCEPTION.getCode() != initPo.getStatus()) {
                LOGGER.error("初始化任务已执行，无需再次执行");
                return ViewBean.createFail("初始化任务已执行，无需再次执行", ErrorCode.SERVICE_ERROR);
            }
            LogTaskLearnDataPo taskpo = buildBeginTaskPo(TaskNameEnum.INITDAYTASK, null, null);
            logTaskLearnDataMapper.saveTaskLog(taskpo);
            handleDayTaskService.initDayTask();
            updateLogTastStatus(taskpo, TaskStatusEnum.FINISH);
        } catch (Exception e) {
            LOGGER.error("初始化异常",e);
            return ViewBean.createFail("处理失败", ErrorCode.PARAM_ERROR);
        }
        return ViewBean.createSuccess("处理成功");
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

    private LogTaskLearnDataPo updateLogTastStatus(LogTaskLearnDataPo newLogPo, TaskStatusEnum taskStatusEnum) {
        newLogPo.setStatus(taskStatusEnum.getCode());
        newLogPo.setEndDate(new Date());
        logTaskLearnDataMapper.updateTaskLog(newLogPo);
        LOGGER.error("完成任务日志：{}", newLogPo.toString());
        LogTaskLearnDataPo po = logTaskLearnDataMapper.getTaskLogById(newLogPo.getId());
        return po;
    }

}
