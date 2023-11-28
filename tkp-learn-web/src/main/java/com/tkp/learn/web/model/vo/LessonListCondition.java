package com.tkp.learn.web.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * author: itw_lixg01
 * date: 2020-06-04 19:51
 * description:
 **/
@ToString
@Getter
@Setter
public class LessonListCondition {
    private String name;
    private List<String> categoryIds;
    private String labelCode;
}
