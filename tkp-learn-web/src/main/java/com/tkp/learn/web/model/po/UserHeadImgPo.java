package com.tkp.learn.web.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * author: itw_lixg01
 * date: 2020-06-04 09:08
 * description: 用户头像
 **/

@ToString
@Getter
@Setter
@TableName(value = "learn_user_head_img")
public class UserHeadImgPo {

    @TableId
    @TableField(value = "id")
    private String id;

    @TableField(value = "work_no")
    private String workNo;

    @TableField(value = "head_img_url")
    private String headImgUrl;

    @TableField(value = "create_time")
    private LocalDateTime createTime;

    @TableField(value = "update_time")
    private LocalDateTime updateTime;
}
