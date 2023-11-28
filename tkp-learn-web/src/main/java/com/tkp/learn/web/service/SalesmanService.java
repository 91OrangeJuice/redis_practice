package com.tkp.learn.web.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tkp.learn.web.actuator.aspect.ParamsValidated;
import com.tkp.learn.web.actuator.exception.ServiceException;
import com.tkp.learn.web.dao.DictBranchSalesmanMapper;
import com.tkp.learn.web.dao.LessonCountMapper;
import com.tkp.learn.web.dao.SalesmanMapper;
import com.tkp.learn.web.dao.SalesmanVideoBehaviorMapper;
import com.tkp.learn.web.model.bo.LessonLearnDurationBo;
import com.tkp.learn.web.model.bo.LoginUser;
import com.tkp.learn.web.model.bo.PersonalLearnBo;
import com.tkp.learn.web.model.bo.VideoBehaviorHisStatisticsBo;
import com.tkp.learn.web.model.enums.IdentityEnum;
import com.tkp.learn.web.model.enums.LabelEnum;
import com.tkp.learn.web.model.enums.StudyStatusEnum;
import com.tkp.learn.web.model.po.LessonCountPo;
import com.tkp.learn.web.model.po.VideoBehaviorHisPo;
import com.tkp.learn.web.model.vo.LearnHistoryCondition;
import com.tkp.learn.web.model.vo.LessonDetailVo;
import com.tkp.learn.web.model.vo.LessonItemVo;
import com.tkp.learn.web.model.vo.common.PageVo;
import com.tkp.learn.web.security.SecurityUser;
import com.tkp.learn.web.service.serviceImpl.SalesmanLearnService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.List;

import java.util.Map;

/**
 * Created by itw_wangshuai01 on 2020/5/28.
 */

@Service("salesman")
public class SalesmanService implements UserService, BranchService,LearningDurationService,LessonCountService, LessonLearnService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SalesmanService.class);

    @Autowired
    private SalesmanMapper salesmanMapper;
    @Autowired
    private DictBranchSalesmanMapper dictBranchSalesmanMapper;
    @Autowired
    private SalesmanVideoBehaviorMapper salesmanVideoBehaviorMapper;
    @Autowired
    private LessonCountMapper lessonCountMapper;
    @Autowired
    private SalesmanLearnService salesmanLearnService;

    @Override
    public SecurityUser loadUserByWorkNo(final String saleNo) {
        final LoginUser loginUser = salesmanMapper.selectBySaleNo(saleNo);
        if (loginUser == null) {
            LOGGER.error("根据工号[{}]未查询到此业务员！", saleNo);
            throw new ServiceException("业务员不存在！");
        }
        return new SecurityUser(loginUser, IdentityEnum.SALESMAN);
    }

    @Override
    public String getOrgShortName(String workNo) {
        return dictBranchSalesmanMapper.getOrgShortNameBySaleNo(workNo);
    }

    @Override
    @ParamsValidated
    public int getCurrentMonthLearningDuration(@NotNull String userId) {
        Integer rel = salesmanVideoBehaviorMapper.getMonthLearningDurationByUserId(userId);
        return null == rel ? 0 : rel;
    }

    @Override
    @ParamsValidated
    public int getTotalLearningDuration(@NotNull String userId) {
        Integer rel = salesmanVideoBehaviorMapper.getTotalLearningDurationByUserId(userId);
        return null == rel ? 0 : rel;
    }

    @Override
    public VideoBehaviorHisStatisticsBo getLearningDuration(@NotNull String userId) {
        VideoBehaviorHisStatisticsBo bo = new VideoBehaviorHisStatisticsBo();
        int monthDuration = getCurrentMonthLearningDuration(userId);
        int totalDuration = getTotalLearningDuration(userId);
        bo.setUserId(userId);
        bo.setMonthDuration(monthDuration);
        bo.setTotalDuration(totalDuration);
        return bo;
    }

    @Override
    public VideoBehaviorHisStatisticsBo getLearningDurationByDate(@NotNull String userId, int year, int month) {
        VideoBehaviorHisStatisticsBo bo = new VideoBehaviorHisStatisticsBo();
        List<VideoBehaviorHisPo> pos=salesmanVideoBehaviorMapper.getAllLearningDurationByDate(userId,0,0);
        if(!CollectionUtils.isEmpty(pos)){
            int monthDuration=0;
            int yearDuration=0;
            int totalDuration=0;
            for(VideoBehaviorHisPo po:pos){
                if(year==po.getYear()&&month==po.getMonth()){
                    monthDuration+=po.getDuration();
                }
                if(year==po.getYear()){
                    yearDuration+=po.getDuration();
                }
                totalDuration+=po.getDuration();
            }
            bo.setMonthDuration(monthDuration);
            bo.setYearDuration(yearDuration);
            bo.setTotalDuration(totalDuration);
        }
        bo.setUserId(userId);
        return bo;
    }

    @Override
    @ParamsValidated
    public PersonalLearnBo getLessonCountAll(@NotNull String userId) {
        PersonalLearnBo lcBo = new PersonalLearnBo();
        List<LessonCountPo> lessonCountPos = null;
        int requiredLessonCount = 0;
        int requiredFinishLessonCount = 0;
        int totalFinishLessonCount = 0;
        int totalLessonCount = 0;
        lessonCountPos = lessonCountMapper.getLessonCountListBySaleNo(userId);
        if (!CollectionUtils.isEmpty(lessonCountPos)) {
            for (LessonCountPo lessonCountPo : lessonCountPos) {
                //判断是必修
                if (!StringUtils.isEmpty(lessonCountPo.getLabelId()) && LabelEnum.REQUIRED.getCode().equals(lessonCountPo.getLabelId())) {
                    //判断必修已完成
                    if (StudyStatusEnum.LEARNED.getCode().equals(lessonCountPo.getStateSal())) {
                        requiredFinishLessonCount += lessonCountPo.getCount();
                    }
                    requiredLessonCount += lessonCountPo.getCount();
                }
                //总课程数
                if (StudyStatusEnum.LEARNED.getCode().equals(lessonCountPo.getStateSal())) {
                    totalFinishLessonCount += lessonCountPo.getCount();
                }
                totalLessonCount += lessonCountPo.getCount();
            }
        }
        lcBo.setFinishLessonCount(totalFinishLessonCount);
        lcBo.setRequiredFinishLessonCount(requiredFinishLessonCount);
        lcBo.setRequiredLessonCount(requiredLessonCount);
        lcBo.setTotalLessonCount(totalLessonCount);
        lcBo.setUserId(userId);
        return lcBo;
    }

    @Override
    public Map<String, Integer> getLessonLearnersNumMap() {
        return salesmanLearnService.getLessonLearnersNumMap();
    }

    @Override
    public Map<String, LessonLearnDurationBo> getLessonLearnDurationMap(String saleNo) {
        return salesmanLearnService.getLessonLearnDurationMap(saleNo);
    }

    @Override
    public PageVo<LessonItemVo> getLearnHistory(Page<LessonItemVo> page, LearnHistoryCondition condition) {
        return salesmanLearnService.getLearnHistory(page, condition);
    }

    @Override
    public int getLessonLearnersNumByLessonId(String lessonId) {
        return salesmanLearnService.getLessonLearnersNumByLessonId(lessonId);
    }

    @Override
    public LessonDetailVo getLessonDetailByLessonIdAndWorkNo(String lessonId, String saleNo) {
        return salesmanLearnService.getLessonDetailByLessonIdAndWorkNo(lessonId, saleNo);
    }
}
