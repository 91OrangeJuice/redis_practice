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
 * date: 2020-04-29 14:39
 * description:
 **/
@Getter
@Setter
@ToString
@TableName("video_url_log")
public class VideoUrlLogPo {

    @TableId
    @TableField("id")
    private String id;

    @TableField("file_uuid")
    private String fileUuid;

    @TableField("user_code")
    private String userCode;

    @TableField("clue_id")
    private String clueId;

    @TableField("url")
    private String url;

    @TableField("create_time")
    private LocalDateTime createTime;
}
