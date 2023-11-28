package com.tkp.learn.archive.task.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/6/5
 * @version: 1.0
 */
@Setter
@Getter
@ToString
@TableName(value = "log_task_learn_data_video")
public class LogTaskLearnDataPo {
    @TableId
    @TableField(value = "id")
    private String id ;
    @TableField(value = "task_name")
    private String taskName;
    @TableField(value = "task_code")
    private String taskCode;
    @TableField(value = "begin_date")
    private Date beginDate ;
    @TableField(value = "end_date")
    private Date endDate ;
    @TableField(value = "status")
    private int status ;
    @TableField(value = "statistical_beginTime")
    private Date statisticalBeginTime ;
    @TableField(value = "statistical_endTime")
    private Date statisticalEndTime ;

}
