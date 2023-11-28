package com.tkp.learn.admin.model.bo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LessonBo {
    private String uuid;
    private String name;
    private int size;
    private String type;
    private LocalDateTime beginTime;
}
