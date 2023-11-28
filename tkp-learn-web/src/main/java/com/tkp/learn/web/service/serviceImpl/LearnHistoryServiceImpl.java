package com.tkp.learn.web.service.serviceImpl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tkp.learn.web.model.bo.TagBo;
import com.tkp.learn.web.model.vo.LearnHistoryCondition;
import com.tkp.learn.web.model.vo.LessonItemVo;
import com.tkp.learn.web.model.vo.PageParamVo;
import com.tkp.learn.web.model.vo.common.PageVo;
import com.tkp.learn.web.security.SecurityUser;
import com.tkp.learn.web.service.LearnHistoryService;
import com.tkp.learn.web.service.LessonLearnService;
import com.tkp.learn.web.service.TagService;
import com.tkp.learn.web.service.factory.StrategyContext;
import com.tkp.learn.web.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * author: itw_lixg01
 * date: 2020-06-11 13:16
 * description:
 **/
@Service
public class LearnHistoryServiceImpl implements LearnHistoryService {

    @Autowired
    private StrategyContext<LessonLearnService> context;
    @Autowired
    private TagService tagService;
    @Autowired
    private EmployeeLearnService employeeLearnService;
    @Autowired
    private SalesmanLearnService salesmanLearnService;

    @Override
    public PageVo<LessonItemVo> getLearnHistory(PageParamVo<LearnHistoryCondition> param) {
        SecurityUser loginUser = SecurityUtils.getLoginUser();
        Page<LessonItemVo> page = new Page<>(param.getPageNum(), param.getPageSize());
        LearnHistoryCondition condition = param.getCondition();
        condition.setWorkNo(loginUser.getWorkNo());

        LessonLearnService lessonLearnService = context.getStrategy(loginUser.getIdentity().getCode());
        PageVo<LessonItemVo> learnHistory = lessonLearnService.getLearnHistory(page, condition);
        setTagsAndLearnersNum(learnHistory.getDatas());

        return learnHistory;
    }

    private void setTagsAndLearnersNum(List<LessonItemVo> lessons) {
        if (ObjectUtils.isEmpty(lessons)) {
            return;
        }

        Map<String, Integer> employeeLearnersNumMap = employeeLearnService.getLessonLearnersNumMap();
        Map<String, Integer> salesmanLearnersNumMap = salesmanLearnService.getLessonLearnersNumMap();
        Map<String, List<TagBo>> lessonTagsMap = tagService.getLessonTagMap();

        for (LessonItemVo lesson : lessons) {
            String lessonId = lesson.getLessonId();
            lesson.setLearnersNum(employeeLearnersNumMap.getOrDefault(lessonId, 0) + salesmanLearnersNumMap.getOrDefault(lessonId, 0));
            lesson.setTags(lessonTagsMap.getOrDefault(lessonId, new ArrayList<>()));
        }
    }
}
