package com.tkp.learn.web.model.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ExperienceSharingVo {
    @NotNull(message = "lessonId（课程id）不能为空")
    private String lessonId;
    private String searchCondition;
    private String orgNo;
    @NotNull(message = "orderByCondition（排序规则）不能为空")
    private String orderByCondition;
    private String workNo;


}
