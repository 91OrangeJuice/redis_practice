package com.tkp.learn.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tkp.learn.web.model.bo.MenuBo;
import com.tkp.learn.web.model.po.MenuPo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author: itw_lixg01
 * date: 2020-06-12 15:54
 * description:
 **/

@Mapper
@Repository
public interface LearnMenuMapper extends BaseMapper<MenuPo> {

    List<MenuBo> getMenus();
}
