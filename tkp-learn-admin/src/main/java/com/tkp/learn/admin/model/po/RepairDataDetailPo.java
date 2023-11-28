package com.tkp.learn.admin.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author itw_liupeng01
 * @since 2020-07-31
 */
@Data
@TableName(value = "repair_data_detail")
public class RepairDataDetailPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    @ApiModelProperty(value = "员工号")
    private String userNo;

    @ApiModelProperty(value = "员工类型")
    private String userType;

    @ApiModelProperty(value = "白名单电话号")
    private String whitePhone;

    @ApiModelProperty(value = "课程id")
    private String lessonId;

    @ApiModelProperty(value = "学习方式")
    private String learningStyle;

    @ApiModelProperty(value = "问题描述")
    private String questionDescript;

    @ApiModelProperty(value = "学习完成时间")
    private LocalDateTime learnedTime;

    @ApiModelProperty(value = "最后提交人id")
    private String lastCommitUser;

    @ApiModelProperty(value = "最后提交时间")
    private LocalDateTime lastCommitTime;

    @ApiModelProperty(value = "审批人id")
    private String approver;

    @ApiModelProperty(value = "审批时间")
    private LocalDateTime approveTime;

    @ApiModelProperty(value = "当前状态")
    private String currentStatus;

    @ApiModelProperty(value = "创建人")
    private String createUser;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新人")
    private String updateUser;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


}
