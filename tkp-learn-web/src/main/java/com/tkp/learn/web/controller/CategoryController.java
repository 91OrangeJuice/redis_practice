package com.tkp.learn.web.controller;

import com.tkp.learn.web.actuator.model.ViewBean;
import com.tkp.learn.web.model.bo.CategoryBo;
import com.tkp.learn.web.model.vo.FilterVo;
import com.tkp.learn.web.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * author: itw_lixg01
 * date: 2020-06-10 19:37
 * description:
 **/
@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getProjectFlowCategories")
    public ViewBean<List<CategoryBo>> getProjectFlowCategories() {
        return ViewBean.createSuccess(categoryService.getProjectFlowCategories());
    }

    @GetMapping("/getTopCategories")
    public ViewBean<List<CategoryBo>> getTopCategories() {
        return ViewBean.createSuccess(categoryService.getTopCategories());
    }

    @GetMapping("/getFilterItems")
    public ViewBean<FilterVo> getFilterItems() {
        return ViewBean.createSuccess(categoryService.getFilterItems());
    }
}
