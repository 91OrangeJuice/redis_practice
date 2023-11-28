package com.tkp.learn.archive.task.dao;

import com.tkp.learn.archive.task.model.po.LogTaskLearnDataPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/6/5
 * @version: 1.0
 */
@Mapper
@Repository
public interface LogTaskLearnDataMapper {

    LogTaskLearnDataPo getTaskLogById(@Param("id") String id);

    LogTaskLearnDataPo getLastTaskLogByCode(@Param("taskCode") String taskCode, @Param("status") int status);

    void saveTaskLog(LogTaskLearnDataPo po);

    void updateTaskLog(LogTaskLearnDataPo po);

    LogTaskLearnDataPo getLastTaskLogByCodeSingle(@Param("taskCode") String taskCode);

}
