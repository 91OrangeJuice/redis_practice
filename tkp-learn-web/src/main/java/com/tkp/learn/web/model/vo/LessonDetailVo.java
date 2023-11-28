package com.tkp.learn.web.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tkp.learn.web.model.bo.TagBo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

/**
 * author: itw_lixg01
 * date: 2020-06-12 20:55
 * description:
 **/

@Getter
@Setter
@ToString
public class LessonDetailVo {

    private String lessonId;

    private String lessonName;

    private String description;

    /**
     * 课程预览图
     */
    private String imgPreview;

    /**
     * 内容概览图
     */
    private String imgLong;

    private String lessonUrl;

    private String status;

    private String labelCode;

    private int lessonDuration;

    private int learnedDuration;

    private int learnersNum;

    private List<TagBo> tags;

    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy/MM/dd HH:mm:ss")
    private LocalDateTime createTime;

    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy/MM/dd HH:mm:ss")
    private LocalDateTime liveBeginTime;

    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy/MM/dd HH:mm:ss")
    private LocalDateTime liveEndTime;
}
