package com.tkp.learn.web.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("learn_data_lesson_experience")
public class LearnDataLessonExperiencePo {
    @TableId(type = IdType.UUID )
    @TableField("id")
    private String id;
    @TableField("lesson_id")
    private String lessonId;
    @TableField("course_name")
    private String courseName;
    @TableField("sale_no")
    private String saleNo;
    @TableField("branch_code")
    private String branchCode;
    @TableField("content")
    private String content;
    @TableField("won_praise_num")
    private long wonPraiseNum;
    @TableField("top")
    private long top;
    @TableField("status")
    private String status;
    @TableField("publish_time")
    private LocalDateTime publishTime;
    @TableField("order_no")
    private long orderNo;
    @TableField("user_type")
    private String userType;
}
