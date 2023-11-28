package com.tkp.learn.web.model.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName(value="rel_lesson_mengzhu")
public class RelLessonMengzhuPo {
    private String uuid;
    private String lessonId;
    private String videoId;
    private LocalDateTime createTime;
}
