package com.tkp.learn.pushcdn.task.remote.api;

import com.tkp.learn.pushcdn.task.remote.dto.HotLessonDto;
import com.tkp.learn.pushcdn.task.remote.dto.LearnWebResponse;
import com.tkp.learn.pushcdn.task.remote.dto.MenuDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Created by itw_wangshuai01 on 2020/6/15.
 */

@FeignClient(name = "learnWeb", url = "${config.learnWeb.host}")
public interface LearnWebServiceApi {

    @GetMapping("/hotLesson/list")
    LearnWebResponse<List<HotLessonDto>> getHotLessons();

    @GetMapping("/menu/tree")
    LearnWebResponse<List<MenuDto>> getMenus();
}
