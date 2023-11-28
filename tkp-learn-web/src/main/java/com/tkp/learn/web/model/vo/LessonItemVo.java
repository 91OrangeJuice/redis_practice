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
 * date: 2020-06-04 20:29
 * description: 课程列表-课程实体
 **/

@Getter
@Setter
@ToString
public class LessonItemVo {
    private String lessonId;
    private String lessonName;
    private String description;
    private String status;
    private String labelCode;
    private int lessonDuration;
    private int learnDuration;
    private int learnersNum;
    private String iconPath;
    private List<TagBo> tags;

    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime createTime;
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime beginTime;
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime finishTime;
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime lastTime;

}
