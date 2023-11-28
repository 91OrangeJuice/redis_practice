package com.tkp.learn.web.dao;

import com.tkp.learn.web.model.po.TaskLessonScanPo;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface TaskLessonScanMapper {

    int deleteByPrimaryKey(String id);

    int insert(TaskLessonScanPo record);

    int insertSelective(TaskLessonScanPo record);

    TaskLessonScanPo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TaskLessonScanPo record);

    int updateByPrimaryKey(TaskLessonScanPo record);

    String selectByLessonId(String lessonId);

    int updateEnableTrue(String id);
}