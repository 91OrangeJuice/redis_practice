package com.tkp.learn.web.controller;

import com.tkp.learn.web.actuator.model.ErrorCode;
import com.tkp.learn.web.actuator.model.ViewBean;
import com.tkp.learn.web.model.vo.*;
import com.tkp.learn.web.model.vo.common.PageVo;
import com.tkp.learn.web.service.LessonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * author: itw_lixg01
 * date: 2020-06-04 19:36
 * description: 课程查询
 **/
@RestController
@RequestMapping("/lesson")
@Slf4j
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @PostMapping("/list.auth")
    public ViewBean<PageVo<LessonItemVo>> getLessonList(@RequestBody @Validated PageParamVo<LessonListCondition> param, BindingResult binding) {
        if (binding.hasErrors()) {
            for (ObjectError error : binding.getAllErrors()) {
                LOGGER.info("课程列表请求参数有误:{}", error.getDefaultMessage());
            }
            return ViewBean.createFail("课程列表请求参数有误", ErrorCode.PARAM_ERROR);
        }
        return ViewBean.createSuccess(lessonService.getLessonList(param));
    }

    @GetMapping("/getLessonDetail.auth")
    public ViewBean<LessonDetailVo> getColumnFileDetail(@RequestParam("lessonId") String lessonId) {
        LOGGER.info("文件ID：【{}】", lessonId);
        if (StringUtils.isEmpty(lessonId)) {
            return ViewBean.createFail("文件ID不能为空", ErrorCode.PARAM_ERROR);
        }
        return  ViewBean.createSuccess(lessonService.getLessonDetail(lessonId));
    }
}
