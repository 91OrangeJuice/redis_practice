package com.tkp.learn.web.service.serviceImpl;

import com.tkp.learn.web.dao.LearnLessonMapper;
import com.tkp.learn.web.model.bo.HotLessonBo;
import com.tkp.learn.web.model.bo.LessonLearnersNumBo;
import com.tkp.learn.web.model.bo.TagBo;
import com.tkp.learn.web.service.HotLessonService;
import com.tkp.learn.web.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * author: itw_lixg01
 * date: 2020-06-10 14:15
 * description:
 **/
@Service
public class HotLessonServiceImpl implements HotLessonService {

    @Autowired
    private LearnLessonMapper learnLessonMapper;
    @Autowired
    private TagService tagService;

    //热门课程列表最多显示5条
    private static final int HOT_LIMIT = 5;

    @Override
    public List<HotLessonBo> getHotLessons() {
        List<LessonLearnersNumBo> lessonLearnersNumBos = getTop5Lessons();
        Map<String, List<TagBo>> lessonTagMap = tagService.getLessonTagMap();
        List<HotLessonBo> hotLessons = new ArrayList<>();

        for (LessonLearnersNumBo bo : lessonLearnersNumBos) {
            String lessonId = bo.getLessonId();
            HotLessonBo lesson = learnLessonMapper.getLessonByLessonId(lessonId);
            lesson.setLearnersNum(bo.getLearnersNum());
            lesson.setTags(lessonTagMap.getOrDefault(lessonId, new ArrayList<>()));
            hotLessons.add(lesson);
        }

        return hotLessons;
    }

    private List<LessonLearnersNumBo> getTop5Lessons() {
        List<String> lessonIds = learnLessonMapper.getAllLessonIds();
        List<LessonLearnersNumBo> lessonLearnersNum = new ArrayList<>();
        Map<String, Integer> salesmanLessonLearnersNumMap = getSalesmanLessonLearnersNumMap();
        Map<String, Integer> employeeLessonLearnersNumMap = getEmployeeLessonLearnersNumMap();

        for (String lessonId : lessonIds) {
            LessonLearnersNumBo bo = new LessonLearnersNumBo();
            bo.setLessonId(lessonId);
            bo.setLearnersNum(salesmanLessonLearnersNumMap.getOrDefault(lessonId, 0) + employeeLessonLearnersNumMap.getOrDefault(lessonId, 0));
            lessonLearnersNum.add(bo);
        }
        lessonLearnersNum.sort(Comparator.comparing(LessonLearnersNumBo::getLearnersNum).reversed());

        return lessonLearnersNum.subList(0, lessonLearnersNum.size() > HOT_LIMIT ? HOT_LIMIT : lessonLearnersNum.size());
    }

    private Map<String, Integer> getSalesmanLessonLearnersNumMap() {
        List<LessonLearnersNumBo> salesmanLearnersNumList = learnLessonMapper.getSalesmanLearnersNumLast30Days();

        return salesmanLearnersNumList.stream().collect(Collectors.toMap(
                LessonLearnersNumBo::getLessonId, LessonLearnersNumBo::getLearnersNum));
    }

    private Map<String, Integer> getEmployeeLessonLearnersNumMap() {
        List<LessonLearnersNumBo> EmployeeLearnersNumList = learnLessonMapper.getEmployeeLearnersNumLast30Days();

        return EmployeeLearnersNumList.stream().collect(Collectors.toMap(
                LessonLearnersNumBo::getLessonId, LessonLearnersNumBo::getLearnersNum));
    }


}
