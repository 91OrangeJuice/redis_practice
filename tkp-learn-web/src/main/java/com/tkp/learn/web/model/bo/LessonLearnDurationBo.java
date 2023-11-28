package com.tkp.learn.web.model.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * author: itw_lixg01
 * date: 2020-06-08 18:04
 * description:
 **/
@Getter
@Setter
@ToString
public class LessonLearnDurationBo {
    private String lessonId;
    private int duration;
    private String status;
    private LocalDateTime beginTime;
    private LocalDateTime finishTime;
    private LocalDateTime lastTime;
}
