package com.tkp.learn.web.model.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * author: itw_lixg01
 * date: 2020-06-09 20:27
 * description: 热门课程列表-课程实体
 **/
@Getter
@Setter
@ToString
public class HotLessonBo {
    private String lessonId;
    private String lessonName;
    private String description;
    private String labelCode;
    private int learnersNum;
    private String iconPath;
    private String createTime;
    private List<TagBo> tags;
}
