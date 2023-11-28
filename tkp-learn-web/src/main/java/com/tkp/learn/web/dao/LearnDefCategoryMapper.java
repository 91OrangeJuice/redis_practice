package com.tkp.learn.web.dao;

import com.tkp.learn.web.model.bo.CategoryBo;
import com.tkp.learn.web.model.bo.CategoryDetailBo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author: itw_lixg01
 * date: 2020-06-05 11:19
 * description: 课程分类
 **/
@Mapper
@Repository
public interface LearnDefCategoryMapper {
    List<CategoryBo> getTopCategories();

    List<CategoryBo> getCategoriesByUpperId(String upperId);

    List<CategoryDetailBo> getNonBusinessCategories();
}
