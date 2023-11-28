package com.tkp.learn.web.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author itw_liupeng01
 * @since 2020-06-05
 */
@Data
@TableName("learn_data_praises")
public class LearnDataPraisesPo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 课程心得ID
     */
    private String courseExperienceId;

    /**
     * 获赞人工号
     */
    private String wonPraiseWorkNo;

    /**
     * 点赞人工作号
     */
    private String workNo;

    /**
     * 姓名
     */
    private String name;

    /**
     * 状态（99删除，01未读，02已读）
     */
    private String status;

    /**
     * 排序字段
     */
    private Integer orderNo;

    private LocalDateTime createTime;


}
