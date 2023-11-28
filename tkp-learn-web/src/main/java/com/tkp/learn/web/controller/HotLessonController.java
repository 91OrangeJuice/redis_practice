package com.tkp.learn.web.controller;

import com.tkp.learn.web.actuator.model.ViewBean;
import com.tkp.learn.web.model.bo.HotLessonBo;
import com.tkp.learn.web.service.HotLessonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * author: itw_lixg01
 * date: 2020-06-10 17:44
 * description: 热门课程
 **/
@RestController
@RequestMapping("/hotLesson")
@Slf4j
public class HotLessonController {

    @Autowired
    private HotLessonService hotLessonService;

    @GetMapping("/list")
    public ViewBean<List<HotLessonBo>> getHotLessons() {
        return ViewBean.createSuccess(hotLessonService.getHotLessons());
    }
}
