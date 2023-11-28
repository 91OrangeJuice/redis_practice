package com.tkp.learn.web.model.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * author: itw_lixg01
 * date: 2020-06-08 17:22
 * description: 课程标签
 **/

@Getter
@Setter
@ToString
public class LessonTagBo {

    private String lessonId;

    private String tagCode;

    private String tagName;

    private int sortNo;
}
