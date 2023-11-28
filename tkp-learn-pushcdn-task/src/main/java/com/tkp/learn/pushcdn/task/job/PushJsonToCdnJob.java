package com.tkp.learn.pushcdn.task.job;

import com.tkp.learn.pushcdn.task.model.enums.FileFormat;
import com.tkp.learn.pushcdn.task.model.enums.FileName;
import com.tkp.learn.pushcdn.task.remote.dto.HotLessonDto;
import com.tkp.learn.pushcdn.task.remote.dto.MenuDto;
import com.tkp.learn.pushcdn.task.service.CdnService;
import com.tkp.learn.pushcdn.task.service.LearnWebService;
import com.tkp.learn.pushcdn.task.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by itw_wangshuai01 on 2020/6/15.
 */

@Service
public class PushJsonToCdnJob {

    @Autowired
    private LearnWebService learnWebService;
    @Autowired
    private CdnService cdnService;

    public void execute() {
        pushHotLessons();
        pushMenus();
    }

    private void pushHotLessons() {
        final List<HotLessonDto> hotLessons = learnWebService.getHotLessons();
        final String jsonData = convertToJson(hotLessons);
        cdnService.uploadFile(jsonData.getBytes(), FileName.HOT_LESSONS, FileFormat.JSON);
    }

    private void pushMenus() {
        final List<MenuDto> menus = learnWebService.getMenus();
        final String jsonData = convertToJson(menus);
        cdnService.uploadFile(jsonData.getBytes(), FileName.MENU, FileFormat.JSON);
    }


    private String convertToJson(final Object object) {
        return JsonUtil.parseObjToJson(object);
    }

}
