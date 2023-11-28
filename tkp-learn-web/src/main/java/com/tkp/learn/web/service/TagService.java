package com.tkp.learn.web.service;

import com.tkp.learn.web.model.bo.TagBo;

import java.util.List;
import java.util.Map;

/**
 * author: itw_lixg01
 * date: 2020-06-05 17:13
 * description: 标签
 **/
public interface TagService {

    /**
     * 获取课程与标签对应关系
     * @return
     */
    Map<String, List<TagBo>> getLessonTagMap();

    /**
     * 根据课程id获取课程标签
     * @param lessonId 课程id
     * @return 课程标签
     */
    List<TagBo> getTagsByLessonId(String lessonId);
}
