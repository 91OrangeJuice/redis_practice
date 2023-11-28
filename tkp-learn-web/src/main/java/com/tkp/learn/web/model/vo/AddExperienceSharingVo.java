package com.tkp.learn.web.model.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddExperienceSharingVo {
    @NotNull(message = "lessonId（课程id）不能为空")
    private String lessonId;
    @NotNull(message = "content（心得内容）不能为空")
    private String content;
}
