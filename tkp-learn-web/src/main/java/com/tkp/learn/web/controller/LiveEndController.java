package com.tkp.learn.web.controller;

import com.tkp.learn.web.actuator.exception.CheckException;
import com.tkp.learn.web.actuator.model.ErrorCode;
import com.tkp.learn.web.actuator.model.ViewBean;
import com.tkp.learn.web.model.vo.LiveEndVideoDataVo;
import com.tkp.learn.web.service.serviceImpl.LiveEndService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/live")
public class LiveEndController {
    public static final Logger LOGGER = LoggerFactory.getLogger(LiveEndController.class);

    private LiveEndService liveEndService;

    @Autowired
    public LiveEndController(LiveEndService liveEndService) {
        this.liveEndService = liveEndService;
    }

    @PostMapping(value = "/end")
    public ViewBean<Integer> updateLiveLesson(@RequestBody @Validated LiveEndVideoDataVo liveEndVideoDataVo, BindingResult binding) {
        if (binding.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            binding.getFieldErrors().forEach(fieldError -> errorMessage.append(fieldError.getField()).append(":").append(fieldError.getDefaultMessage()).append(","));
            LOGGER.error("{}", errorMessage);
            throw new CheckException(errorMessage.toString(), ErrorCode.PARAM_ERROR);
        }
        int count = liveEndService.updateLiveLesson(liveEndVideoDataVo);
        return ViewBean.createSuccess(count);
    }
}
