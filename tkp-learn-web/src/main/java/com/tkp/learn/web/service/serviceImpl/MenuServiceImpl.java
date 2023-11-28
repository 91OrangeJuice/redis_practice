package com.tkp.learn.web.service.serviceImpl;

import com.tkp.learn.web.dao.LearnMenuMapper;
import com.tkp.learn.web.model.bo.MenuBo;
import com.tkp.learn.web.model.bo.TreeBo;
import com.tkp.learn.web.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author: itw_lixg01
 * date: 2020-06-12 15:57
 * description:
 **/
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private LearnMenuMapper learnMenuMapper;

    private static final String ROOT_CODE = "ROOT";

    @Override
    public List<MenuBo> getMenus() {
        return learnMenuMapper.getMenus();
    }

    public List<TreeBo> getMenuTree() {
        List<MenuBo> menus = getMenus();
        Map<String, List<MenuBo>> menuMap = getMenuMap(menus);

        return createTree(ROOT_CODE, menuMap);
    }

    private List<TreeBo> createTree(String rootCode, Map<String,List<MenuBo>> menuMap) {
        List<TreeBo> children = new ArrayList<>();
        List<MenuBo> categoryList = menuMap.get(rootCode);
        if (ObjectUtils.isEmpty(categoryList)) {
            return children;
        }

        for (MenuBo bo : categoryList) {
            TreeBo node = createTreeNode(bo, menuMap);
            children.add(node);
        }
        return children;
    }

    private TreeBo createTreeNode(MenuBo menu, Map<String, List<MenuBo>> menuMap) {
        TreeBo treeNode = new TreeBo();
        treeNode.setCode(menu.getMenuCode());
        treeNode.setName(menu.getMenuName());
        treeNode.setUpperCode(menu.getUpperCode());
        treeNode.setIconPath(menu.getIconPath());
        treeNode.setLevel(menu.getLevel());
        treeNode.setMultiSelect(0);
        treeNode.setSortNo(menu.getSortNo());
        treeNode.setChildren(createTree(menu.getMenuCode(), menuMap));

        return treeNode;
    }

    private Map<String, List<MenuBo>> getMenuMap(List<MenuBo> menus) {
        Map<String, List<MenuBo>> menuMap = new HashMap<>();
        if (ObjectUtils.isEmpty(menus)) {
            return menuMap;
        }

        for (MenuBo menu : menus) {
            List<MenuBo> children = menuMap.getOrDefault(menu.getUpperCode(), new ArrayList<>());
            children.add(menu);
            menuMap.put(menu.getUpperCode(), children);
        }
        return menuMap;
    }

}
