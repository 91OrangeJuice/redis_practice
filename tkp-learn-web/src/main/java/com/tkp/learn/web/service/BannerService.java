package com.tkp.learn.web.service;

import com.tkp.learn.web.model.vo.BannerVo;

import java.util.List;

/**
 * author: itw_lixg01
 * date: 2020-06-11 17:13
 * description: 轮播图
 **/

public interface BannerService {

    List<BannerVo> getBannerList();

    String getTargetUrl(String code);
}
