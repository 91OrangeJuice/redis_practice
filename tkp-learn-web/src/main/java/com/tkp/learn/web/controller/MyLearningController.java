package com.tkp.learn.web.controller;

import com.tkp.learn.web.actuator.model.ErrorCode;
import com.tkp.learn.web.actuator.model.ViewBean;
import com.tkp.learn.web.model.vo.*;
import com.tkp.learn.web.model.vo.common.PageVo;
import com.tkp.learn.web.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/5/29
 * @version: 1.0
 */
@RestController
@RequestMapping("/myLearning")
@Slf4j
public class MyLearningController {

    @Autowired
    private LearnHistoryService learnHistoryService;
    @Autowired
    private LessonLearnViewService lessonLearnViewService;

    /**
     * @return
     * @description:获取我的学习详情
     * @author: itw_wangsc01
     */
    @GetMapping("/getMyLearningDetail.auth")
    public ViewBean<MyLearningDetailVo> getMyLearningDetail() {
        return ViewBean.createSuccess(lessonLearnViewService.getMyLearningDetail());
    }

    /**
     * @param param 查询条件
     * @return 学习历史
     */
    @PostMapping("/getMyLearnHistory.auth")
    public ViewBean<PageVo<LessonItemVo>> getLearnHistory(@RequestBody @Validated PageParamVo<LearnHistoryCondition> param, BindingResult binding) {
        if (binding.hasErrors()) {
            for (ObjectError error : binding.getAllErrors()) {
                LOGGER.info("课程列表请求参数有误:{}", error.getDefaultMessage());
            }
            return ViewBean.createFail("课程列表请求参数有误", ErrorCode.PARAM_ERROR);
        }
        return ViewBean.createSuccess(learnHistoryService.getLearnHistory(param));
    }

    /**
     * @param rankingListPage 为null默认为本年本月
     * @return
     * @description:获取当前用户排名情况及月榜列表
     * @author: itw_wangsc01
     */
    @PostMapping("/getRankingListMonth.auth")
    public ViewBean<RankingListVo> getRankingListMonth(@RequestBody @Validated PageParamVo<RankingListPage> rankingListPage, BindingResult binding) {
        if (binding.hasErrors()) {
            for(ObjectError error : binding.getAllErrors()) {
                LOGGER.info("获取当前用户排名情况及月榜列表请求参数有误:{}", error.getDefaultMessage());
            }
            return ViewBean.createFail("获取当前用户排名情况及月榜列表请求参数有误", ErrorCode.PARAM_ERROR);
        }
        return ViewBean.createSuccess(lessonLearnViewService.getRankingListMonth(rankingListPage));
    }

    /**
     * @param rankingListPage 格式YYYY(传null则默认当前年)
     * @return
     * @description:获取当前用户排名情况及年榜列表
     * @author: itw_wangsc01
     */
    @PostMapping("/getRankingListYear.auth")
    public ViewBean<RankingListVo> getRankingListYear(@RequestBody @Validated PageParamVo<RankingListPage> rankingListPage, BindingResult binding) {
        if (binding.hasErrors()) {
            for(ObjectError error : binding.getAllErrors()) {
                LOGGER.info("获取当前用户排名情况及年榜列表请求参数有误:{}", error.getDefaultMessage());
            }
            return ViewBean.createFail("获取当前用户排名情况及年榜列表请求参数有误", ErrorCode.PARAM_ERROR);
        }
        return ViewBean.createSuccess(lessonLearnViewService.getRankingListYear(rankingListPage));
    }

    /**
     * @param month 月
     * @param year  年
     * @param type  榜单类型
     * @return
     * @description:榜单导出接口
     * @author: itw_wangsc01
     */
    @GetMapping("/exportRankingList")
    public ViewBean<RankingListVo> exportRankingList(@RequestParam("month") String month, @RequestParam("year") String year, @RequestParam("type") String type) {
        //TODO
        return ViewBean.createSuccess(null);
    }

}
