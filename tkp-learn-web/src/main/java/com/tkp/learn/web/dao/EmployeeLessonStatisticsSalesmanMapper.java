package com.tkp.learn.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tkp.learn.web.model.po.EmployeeLessonMidPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Description: 内勤统计
 * @ClassName: EmployeeLessonMidPo
 * @author: itw_liupeng01
 * @date: 2020年5月14日 上午11:00:20
 */
@Mapper
@Repository
public interface EmployeeLessonStatisticsSalesmanMapper extends BaseMapper<EmployeeLessonMidPo> {


    /**
     * 获取视频观看人数
     * @param lessonId 文件编码
     * @return 观看人数
     */
    int getVideoLearnersNum(String lessonId);

    /**
     * 获取当前业务员对于指定视频的观看时长
     * @param oaNo 业务员TK号
     * @param lessonId 文件编码     * @return 观看时长
     */
    Integer getDurationBySaleNoAndFileId(@Param("oaNo") String oaNo, @Param("lessonId") String lessonId);

}
