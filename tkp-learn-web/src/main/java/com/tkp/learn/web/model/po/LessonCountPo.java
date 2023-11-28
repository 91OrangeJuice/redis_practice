package com.tkp.learn.web.model.po;

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
public class LessonCountPo {

    private int count;
    private String labelId;
    private int stateEmp;
    private String stateSal;
    private String userId;


}
