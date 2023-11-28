package com.tkp.learn.web.model.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * author: itw_lixg01
 * Date: 2020/3/2 17:22
 * Description：视频观看时长实体
 */
@Getter
@Setter
@ToString
public class LessonLearnedDurationBo {

    private String fileId;
    private int duration;
    private String status;
}
