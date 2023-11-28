package com.tkp.learn.web.service.serviceImpl;

import com.tkp.learn.web.dao.LearnDefCategoryTagMapper;
import com.tkp.learn.web.model.bo.LessonTagBo;
import com.tkp.learn.web.model.bo.TagBo;
import com.tkp.learn.web.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * author: itw_lixg01
 * date: 2020-06-05 17:15
 * description: 课程标签
 **/
@Service
@Slf4j
public class TagServiceImpl implements TagService {

    @Autowired
    private LearnDefCategoryTagMapper learnDefCategoryTagMapper;

    private List<LessonTagBo> getLessonTags() {
        return learnDefCategoryTagMapper.getLessonTags();
    }

    @Override
    public Map<String, List<TagBo>> getLessonTagMap() {
        List<LessonTagBo> lessonTags = getLessonTags();
        Map<String, List<TagBo>> tagMap = new HashMap<>();
        if (ObjectUtils.isEmpty(lessonTags)) {
            return tagMap;
        }

        for (LessonTagBo bo : lessonTags) {
            String lessonId = bo.getLessonId();
            List<TagBo> tags = tagMap.getOrDefault(lessonId, new ArrayList<>());
            tags.add(buildTagBo(bo));
            tagMap.put(lessonId, tags);
        }

        sort(tagMap);

        return tagMap;
    }

    private void sort(Map<String, List<TagBo>> tagMap) {
        tagMap.forEach((lessonId, tags) -> {
            List<TagBo> sortedTags = tags.stream().sorted(Comparator.comparing(TagBo::getSortNo)).collect(Collectors.toList());
            tagMap.put(lessonId, sortedTags);
        });
    }

    @Override
    public List<TagBo> getTagsByLessonId(String lessonId) {
        return learnDefCategoryTagMapper.getLessonTagsByLessonId(lessonId);
    }

    private TagBo buildTagBo(LessonTagBo lessonTagBo) {
        TagBo tagBo = new TagBo();
        tagBo.setTagCode(lessonTagBo.getTagCode());
        tagBo.setTagName(lessonTagBo.getTagName());
        tagBo.setSortNo(lessonTagBo.getSortNo());
        return tagBo;
    }
}
