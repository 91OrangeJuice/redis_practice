package com.tkp.learn.web.controller;

import com.tkp.learn.web.actuator.model.ViewBean;
import com.tkp.learn.web.model.bo.TreeBo;
import com.tkp.learn.web.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * author: itw_lixg01
 * date: 2020-06-12 15:34
 * description:
 **/

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/tree")
    public ViewBean<List<TreeBo>> getMenuTree() {
        return ViewBean.createSuccess(menuService.getMenuTree());
    }
}
