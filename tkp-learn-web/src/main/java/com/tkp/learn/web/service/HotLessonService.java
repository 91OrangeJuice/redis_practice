package com.tkp.learn.web.service;

import com.tkp.learn.web.model.bo.HotLessonBo;

import java.util.List;

/**
 * author: itw_lixg01
 * date: 2020-06-10 14:14
 * description: 热门课程
 **/
public interface HotLessonService {

    List<HotLessonBo> getHotLessons();
}
