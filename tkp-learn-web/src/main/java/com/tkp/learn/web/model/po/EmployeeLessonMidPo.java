package com.tkp.learn.web.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @Description: 课程统计业务员(内勤)
 * @ClassName: EmployeeLessonMidPo
 * @author: itw_liupeng01
 * @date: 2020年5月14日 上午11:00:20
 */
@Getter
@Setter
@TableName("employee_lesson_mid")
@ToString
public class EmployeeLessonMidPo {


    @TableId
    @TableField("uuid")
    private String uuid;

    @TableField("sale_no")
    private String saleNo;

    @TableField("oa_no")
    private String oaNo;

    @TableField("learned_duration")
    private int learnedDuration;

    @TableField("percentage")
    private double percentage;

    @TableField("status")
    private String status;

    @TableField("update_time")
    private LocalDateTime updateTime;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("finish_time")
    private LocalDateTime finishTime;

    @TableField("begin_time")
    private LocalDateTime beginTime;

    @TableField("latest_learned_time")
    private LocalDateTime latestLearnedTime;

}
