package com.tkp.learn.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tkp.learn.web.model.po.UserHeadImgPo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * author: itw_lixg01
 * date: 2020-06-03 19:04
 * description: 用户头像
 **/

@Mapper
@Repository
public interface UserHeadImgMapper extends BaseMapper<UserHeadImgPo> {
    UserHeadImgPo findByWorkNo(String workNo);
}
