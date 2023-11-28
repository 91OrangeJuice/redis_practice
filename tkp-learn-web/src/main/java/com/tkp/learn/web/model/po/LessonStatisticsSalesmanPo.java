package com.tkp.learn.web.model.po;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description: 课程统计业务员
 * @ClassName: LessonStatisticsSalesmanPo
 * @author: wanggang
 * @date: 2020年3月5日 下午6:00:40 
 */
@Getter
@Setter
@TableName("lesson_statistics_salesman")
@ToString
public class LessonStatisticsSalesmanPo {

	@TableId
	@TableField( "id")
	private String id;

	@TableField( "sale_no")
	private String saleNo;

	@TableField( "lesson_id")
	private String lessonId;

	@TableField( "learned_duration")
	private int learnedDuration;

	@TableField( "percentage")
	private double percentage;

	@TableField( "status")
	private String status;

	@TableField( "begin_time")
	private LocalDateTime beginTime;

	@TableField( "finish_time")
	private LocalDateTime finishTime;

	@TableField( "last_time")
	private LocalDateTime lastTime;

}
