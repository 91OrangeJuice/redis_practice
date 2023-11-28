package com.tkp.learn.web.service.serviceImpl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tkp.learn.web.actuator.exception.CheckException;
import com.tkp.learn.web.actuator.model.ErrorCode;
import com.tkp.learn.web.dao.SalesmanMapper;
import com.tkp.learn.web.model.bo.LessonLearnDurationBo;
import com.tkp.learn.web.model.bo.LessonLearnersNumBo;
import com.tkp.learn.web.model.vo.LearnHistoryCondition;
import com.tkp.learn.web.model.vo.LessonDetailVo;
import com.tkp.learn.web.model.vo.LessonItemVo;
import com.tkp.learn.web.model.vo.common.PageVo;
import com.tkp.learn.web.service.LessonLearnService;
import com.tkp.learn.web.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * author: itw_lixg01
 * date: 2020-06-09 09:32
 * description:
 **/
@Service
@Slf4j
public class SalesmanLearnService implements LessonLearnService {

    @Autowired
    private SalesmanMapper salesmanMapper;

    @Override
    public Map<String, Integer> getLessonLearnersNumMap() {
        List<LessonLearnersNumBo> salesmanLearnersNumList = getLessonLearnNumList();
        if (ObjectUtils.isEmpty(salesmanLearnersNumList)) {
            return new HashMap<>();
        }

        return salesmanLearnersNumList.stream().collect(Collectors.toMap(
                LessonLearnersNumBo::getLessonId, LessonLearnersNumBo::getLearnersNum));
    }

    @Override
    public Map<String, LessonLearnDurationBo> getLessonLearnDurationMap(String saleNo) {
        List<LessonLearnDurationBo> salesmanLessonLearnersNumBos = getLessonLearnedDurationList(saleNo);
        if (ObjectUtils.isEmpty(salesmanLessonLearnersNumBos)) {
            return new HashMap<>();
        }

        return salesmanLessonLearnersNumBos.stream().collect(Collectors.toMap(
                LessonLearnDurationBo::getLessonId, Function.identity(), (key1, key2) -> key2));
    }

    @Override
    public PageVo<LessonItemVo> getLearnHistory(Page<LessonItemVo> page, LearnHistoryCondition condition) {
        Page<LessonItemVo> learnHistory = salesmanMapper.getLearnHistory(page, condition);
        return PageUtils.buildPageVo(learnHistory);
    }

    @Override
    public int getLessonLearnersNumByLessonId(String lessonId) {
        return salesmanMapper.getSalesmanLessonLearnersNumByLessonId(lessonId);
    }

    @Override
    public LessonDetailVo getLessonDetailByLessonIdAndWorkNo(String lessonId, String saleNo) {
        LessonDetailVo lessonDetail = salesmanMapper.getLessonDetailByLessonIdAndSaleNo(lessonId, saleNo);
        if (ObjectUtils.isEmpty(lessonDetail)) {
            LOGGER.error("该课程【】不存在", lessonId);
            throw new CheckException("该课程不存在", ErrorCode.PARAM_ERROR);
        }

        return lessonDetail;
    }

    private List<LessonLearnersNumBo> getLessonLearnNumList() {
        return salesmanMapper.getSalesmanLessonLearnersNum();
    }

    private List<LessonLearnDurationBo> getLessonLearnedDurationList(String saleNo) {
        return salesmanMapper.getSalesmanLearnedDuration(saleNo);
    }

}
