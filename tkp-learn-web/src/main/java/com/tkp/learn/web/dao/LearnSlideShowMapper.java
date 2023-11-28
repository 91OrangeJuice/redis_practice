package com.tkp.learn.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tkp.learn.web.model.po.LearnSlideShowPo;
import com.tkp.learn.web.model.vo.BannerVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author: itw_lixg01
 * date: 2020-06-11 17:24
 * description: 轮播图
 **/

@Mapper
@Repository
public interface LearnSlideShowMapper extends BaseMapper<LearnSlideShowPo> {

    List<BannerVo> getBannerList();
}
