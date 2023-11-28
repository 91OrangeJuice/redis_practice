package com.tkp.learn.web.service;

import com.tkp.learn.web.model.bo.CategoryBo;
import com.tkp.learn.web.model.vo.FilterVo;

import java.util.List;

/**
 * author: itw_lixg01
 * date: 2020-06-05 11:56
 * description: 课程分类
 **/
public interface CategoryService {

    List<CategoryBo> getProjectFlowCategories();

    List<CategoryBo> getTopCategories();

    FilterVo getFilterItems();
}
