package com.tkp.learn.web.service;

import com.tkp.learn.web.model.bo.MenuBo;
import com.tkp.learn.web.model.bo.TreeBo;

import java.util.List;

/**
 * author: itw_lixg01
 * date: 2020-06-12 15:56
 * description:
 **/

public interface MenuService {

    List<MenuBo> getMenus();

    List<TreeBo> getMenuTree();
}
