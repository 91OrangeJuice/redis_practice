package com.tkp.learn.web.service.serviceImpl;

import com.tkp.learn.web.actuator.exception.CheckException;
import com.tkp.learn.web.actuator.model.ErrorCode;
import com.tkp.learn.web.dao.LearnDefCategoryMapper;
import com.tkp.learn.web.model.bo.CategoryBo;
import com.tkp.learn.web.model.bo.CategoryDetailBo;
import com.tkp.learn.web.model.bo.TreeBo;
import com.tkp.learn.web.model.enums.OneLevelCategoryEnum;
import com.tkp.learn.web.model.vo.FilterVo;
import com.tkp.learn.web.model.vo.LabelVo;
import com.tkp.learn.web.service.CategoryService;
import com.tkp.learn.web.service.LabelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author: itw_lixg01
 * date: 2020-06-05 11:58
 * description: 课程分类
 **/
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private LearnDefCategoryMapper learnDefCategoryMapper;
    @Autowired
    private LabelService labelService;

    private static final String ROOT_CATEGORY_CODE = "ROOT";
    private static final String CATEGORY_CODE_ALL = "all";
    private static final String CATEGORY_NAME_ALL = "全部";
    private static final String CATEGORY_ICON_PATH_ALL = null;
    private static final int CATEGORY_MULTI_SELECT_ALL = 0;
    private static final int CATEGORY_SORT_NO_ALL = -1;

    @Override
    public List<CategoryBo> getProjectFlowCategories() {
        return getCategoriesByUpperId(OneLevelCategoryEnum.PROJECT_FLOW.getCode());
    }

    @Override
    public List<CategoryBo> getTopCategories() {
        List<CategoryBo> topCategories = learnDefCategoryMapper.getTopCategories();
        if (topCategories == null) {
            return new ArrayList<>();
        }

        topCategories.add(0, createCategoryBo());

        return topCategories;
    }

    @Override
    public FilterVo getFilterItems() {
        List<TreeBo> categoryTree = getCategoryTree();
        List<LabelVo> labels = labelService.getLabels();

        FilterVo vo = new FilterVo();
        vo.setCategoryTree(categoryTree);
        vo.setLabels(labels);
        return vo;
    }

    private CategoryBo createCategoryBo() {
        CategoryBo categoryBo = new CategoryBo();
        categoryBo.setCategoryCode(CATEGORY_CODE_ALL);
        categoryBo.setCategoryName(CATEGORY_NAME_ALL);
        categoryBo.setIconPath(CATEGORY_ICON_PATH_ALL);
        categoryBo.setMultiSelect(CATEGORY_MULTI_SELECT_ALL);
        categoryBo.setSortNo(CATEGORY_SORT_NO_ALL);

        return categoryBo;
    }

    private List<TreeBo> getCategoryTree() {
        List<CategoryDetailBo> allCategories = learnDefCategoryMapper.getNonBusinessCategories();
        Map<String, List<CategoryDetailBo>> categoryMap = getCategoryMap(allCategories);

        return createTree(ROOT_CATEGORY_CODE, categoryMap);
    }

    private Map<String, List<CategoryDetailBo>> getCategoryMap(List<CategoryDetailBo> allCategories) {
        Map<String, List<CategoryDetailBo>> categoryMap = new HashMap<>();
        if (ObjectUtils.isEmpty(allCategories)) {
            return categoryMap;
        }

        for (CategoryDetailBo category : allCategories) {
            List<CategoryDetailBo> categories = categoryMap.getOrDefault(category.getUpperCategoryCode(), new ArrayList<>());
            categories.add(category);
            categoryMap.put(category.getUpperCategoryCode(), categories);
        }
        return categoryMap;
    }

    private List<TreeBo> createTree(String upperCategoryCode, Map<String, List<CategoryDetailBo>> categoryMap) {

        List<TreeBo> children = new ArrayList<>();
        List<CategoryDetailBo> categoryList = categoryMap.get(upperCategoryCode);
        if (ObjectUtils.isEmpty(categoryList)) {
            return children;
        }

        for (CategoryDetailBo bo : categoryList) {
            TreeBo node = createTreeNode(bo, categoryMap);
            children.add(node);
        }
        return children;
    }

    private TreeBo createTreeNode(CategoryDetailBo categoryDetailBo, Map<String, List<CategoryDetailBo>> categoryMap) {
        TreeBo treeNode = new TreeBo();
        treeNode.setCode(categoryDetailBo.getCategoryCode());
        treeNode.setName(categoryDetailBo.getCategoryName());
        treeNode.setUpperCode(categoryDetailBo.getUpperCategoryCode());
        treeNode.setIconPath(categoryDetailBo.getIconPath());
        treeNode.setLevel(categoryDetailBo.getLevel());
        treeNode.setMultiSelect(categoryDetailBo.getMultiSelect());
        treeNode.setSortNo(categoryDetailBo.getSortNo());
        treeNode.setChildren(createTree(categoryDetailBo.getCategoryCode(), categoryMap));

        return treeNode;
    }

    private List<CategoryBo> getCategoriesByUpperId(String upperId) {
        LOGGER.info("根据父分类编码【{}】获取子分类", upperId);
        if (StringUtils.isEmpty(upperId)) {
            throw new CheckException("父分类编码不能为空", ErrorCode.PARAM_ERROR);
        }
        return learnDefCategoryMapper.getCategoriesByUpperId(upperId);
    }
}
