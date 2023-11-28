package com.tkp.learn.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tkp.learn.web.model.bo.LessonTagBo;
import com.tkp.learn.web.model.bo.TagBo;
import com.tkp.learn.web.model.po.LearnDefCategoryTagPo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author: itw_lixg01
 * date: 2020-06-05 16:17
 * description: 标签
 **/
@Mapper
@Repository
public interface LearnDefCategoryTagMapper extends BaseMapper<LearnDefCategoryTagPo> {
    List<LessonTagBo> getLessonTags();

    List<TagBo> getLessonTagsByLessonId(String lessonId);
}
