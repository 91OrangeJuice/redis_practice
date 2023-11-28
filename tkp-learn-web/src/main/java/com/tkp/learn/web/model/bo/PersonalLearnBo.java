package com.tkp.learn.web.model.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/6/2
 * @version: 1.0
 */
@Getter
@Setter
@ToString
public class PersonalLearnBo {

    private String userId;
    private int requiredLessonCount;
    private int requiredFinishLessonCount;
    private int finishLessonCount;
    private int totalLessonCount;

}
