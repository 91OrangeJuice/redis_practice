package com.tkp.learn.web.controller;

import com.tkp.learn.web.actuator.model.ViewBean;
import com.tkp.learn.web.model.vo.BannerVo;
import com.tkp.learn.web.service.BannerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * author: itw_lixg01
 * date: 2020-06-11 17:11
 * description:
 **/

@RestController
@RequestMapping("/banner")
@Slf4j
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @GetMapping("/list")
    public ViewBean<List<BannerVo>> getBannerList() {
        return ViewBean.createSuccess(bannerService.getBannerList());
    }

    @GetMapping("/get-banner-target-url.auth")
    public ViewBean<String> getTargetUrl(@RequestParam("code") String code) {
        return ViewBean.createSuccess(bannerService.getTargetUrl(code));
    }
}
