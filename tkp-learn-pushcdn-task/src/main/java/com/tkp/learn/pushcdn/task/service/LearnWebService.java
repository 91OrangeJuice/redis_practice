package com.tkp.learn.pushcdn.task.service;

import com.tkp.learn.pushcdn.task.actuator.exception.RemoteException;
import com.tkp.learn.pushcdn.task.actuator.model.ErrorCode;
import com.tkp.learn.pushcdn.task.remote.api.LearnWebServiceApi;
import com.tkp.learn.pushcdn.task.remote.dto.HotLessonDto;
import com.tkp.learn.pushcdn.task.remote.dto.LearnWebResponse;
import com.tkp.learn.pushcdn.task.remote.dto.MenuDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by itw_wangshuai01 on 2020/6/15.
 */

@Service
public class LearnWebService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LearnWebService.class);

    @Autowired
    private LearnWebServiceApi learnWebServiceApi;

    public List<HotLessonDto> getHotLessons() {
        final LearnWebResponse<List<HotLessonDto>> response;
        try {
            response = learnWebServiceApi.getHotLessons();
        } catch (Exception e) {
            LOGGER.error("调用learn_web获取热门课程接口异常！", e);
            throw new RemoteException("获取热门课程异常，请稍后再试!", ErrorCode.REMOTE_EXCEPTION);
        }
        if (!response.getSuccess()) {
            LOGGER.error("调用learn_web获取热门课程接口错误，错误原因：[{}]", response.getErrorMessage());
            throw new RemoteException(response.getErrorMessage(), ErrorCode.REMOTE_DATA_ERROR);
        }
        return response.getData();
    }

    public List<MenuDto> getMenus() {
        final LearnWebResponse<List<MenuDto>> response;
        try {
            response = learnWebServiceApi.getMenus();
        } catch (Exception e) {
            LOGGER.error("调用learn_web获取菜单列表接口异常！", e);
            throw new RemoteException("获取菜单列表异常，请稍后再试!", ErrorCode.REMOTE_EXCEPTION);
        }
        if (!response.getSuccess()) {
            LOGGER.error("调用learn_web获取菜单列表接口错误，错误原因：[{}]", response.getErrorMessage());
            throw new RemoteException(response.getErrorMessage(), ErrorCode.REMOTE_DATA_ERROR);
        }
        return response.getData();
    }
}
