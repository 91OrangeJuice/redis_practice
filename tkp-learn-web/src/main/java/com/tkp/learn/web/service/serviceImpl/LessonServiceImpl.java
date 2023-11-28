package com.tkp.learn.web.service.serviceImpl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tkp.learn.web.dao.LearnLessonMapper;
import com.tkp.learn.web.model.bo.LessonLearnDurationBo;
import com.tkp.learn.web.model.bo.TagBo;
import com.tkp.learn.web.model.vo.LessonDetailVo;
import com.tkp.learn.web.model.vo.LessonItemVo;
import com.tkp.learn.web.model.vo.LessonListCondition;
import com.tkp.learn.web.model.vo.PageParamVo;
import com.tkp.learn.web.model.vo.common.PageVo;
import com.tkp.learn.web.security.SecurityUser;
import com.tkp.learn.web.service.*;
import com.tkp.learn.web.service.access.MengZhuAccessService;
import com.tkp.learn.web.service.factory.StrategyContext;
import com.tkp.learn.web.utils.PageUtils;
import com.tkp.learn.web.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * author: itw_lixg01
 * date: 2020-06-08 15:59
 * description:
 **/
@Service
@Slf4j
public class LessonServiceImpl implements LessonService {

    @Autowired
    private LearnLessonMapper learnLessonMapper;
    @Autowired
    private EmployeeLearnService employeeLearnService;
    @Autowired
    private SalesmanLearnService salesmanLearnService;
    @Autowired
    private TagService tagService;
    @Autowired
    private StrategyContext<LessonLearnService> context;
    @Autowired
    private MengZhuAccessService mengZhuAccessService;

    @Override
    public PageVo<LessonItemVo> getLessonList(PageParamVo<LessonListCondition> param) {
        Page<LessonItemVo> lessonPage = getLessonsByPage(param);

        setProperties(lessonPage.getRecords());

        return PageUtils.buildPageVo(lessonPage);
    }

    @Override
    public LessonDetailVo getLessonDetail(String lessonId) {
        SecurityUser loginUser = SecurityUtils.getLoginUser();
        String identityCode = loginUser.getIdentity().getCode();
        LessonLearnService strategy = context.getStrategy(identityCode);
        LessonDetailVo lessonDetail = strategy.getLessonDetailByLessonIdAndWorkNo(lessonId, loginUser.getWorkNo());

        List<TagBo> tags = tagService.getTagsByLessonId(lessonId);
        int employeeLearnersNum = employeeLearnService.getLessonLearnersNumByLessonId(lessonId);
        int salesmanLearnersNum = salesmanLearnService.getLessonLearnersNumByLessonId(lessonId);
        String lessonUrl = mengZhuAccessService.getAccessUrl(lessonDetail.getLessonUrl(), lessonId);

        lessonDetail.setTags(tags);
        lessonDetail.setLearnersNum(employeeLearnersNum + salesmanLearnersNum);
        lessonDetail.setLessonUrl(lessonUrl);

        return lessonDetail;
    }

    private void setProperties(List<LessonItemVo> records) {
        if (ObjectUtils.isEmpty(records)) {
            return;
        }

        Map<String, Integer> employeeLearnersNumMap = employeeLearnService.getLessonLearnersNumMap();
        Map<String, Integer> salesmanLearnersNumMap = salesmanLearnService.getLessonLearnersNumMap();
        Map<String, List<TagBo>> lessonTagsMap = tagService.getLessonTagMap();
        Map<String, LessonLearnDurationBo> lessonLearnDurationMap = getLessonLearnDurationMap();

        for (LessonItemVo lesson : records) {
            String lessonId = lesson.getLessonId();
            lesson.setLearnersNum(employeeLearnersNumMap.getOrDefault(lessonId, 0) + salesmanLearnersNumMap.getOrDefault(lessonId, 0));
            lesson.setTags(lessonTagsMap.getOrDefault(lessonId, new ArrayList<>()));

            LessonLearnDurationBo lessonLearnDurationBo = lessonLearnDurationMap.get(lessonId);
            if (lessonLearnDurationBo != null) {
                lesson.setLearnDuration(lessonLearnDurationBo.getDuration());
                lesson.setStatus(lessonLearnDurationBo.getStatus());
                lesson.setBeginTime(lessonLearnDurationBo.getBeginTime());
                lesson.setFinishTime(lessonLearnDurationBo.getFinishTime());
                lesson.setLastTime(lessonLearnDurationBo.getLastTime());
            }
        }
    }

    private Map<String, LessonLearnDurationBo> getLessonLearnDurationMap() {
        SecurityUser loginUser = SecurityUtils.getLoginUser();
        LessonLearnService strategy = context.getStrategy(loginUser.getIdentity().getCode());
        return strategy.getLessonLearnDurationMap(loginUser.getWorkNo());
    }

    /**
     * 获取课程列表
     *
     * @param param 查询参数
     * @return
     */
    private Page<LessonItemVo> getLessonsByPage(PageParamVo<LessonListCondition> param) {
        LessonListCondition condition = param.getCondition();

        Page<LessonItemVo> page = new Page<>(param.getPageNum(), param.getPageSize());
        if (ObjectUtils.isEmpty(condition.getCategoryIds())) {
            return learnLessonMapper.getLessonsByNameAndLabelCode(page, condition);
        }
        return learnLessonMapper.getLessonsBy(page, condition);
    }
}
