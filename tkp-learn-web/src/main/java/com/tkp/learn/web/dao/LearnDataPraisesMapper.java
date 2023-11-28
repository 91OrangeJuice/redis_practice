package com.tkp.learn.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tkp.learn.web.model.po.LearnDataPraisesPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/6/3
 * @version: 1.0
 */
@Repository
@Mapper
public interface LearnDataPraisesMapper extends BaseMapper<LearnDataPraisesPo> {

    int getCurrentUserNewPraiseCountByUserId(@Param("userId") String userId,@Param("status") String status);

    int updateStatusById(@Param("idList") List<String> idList, @Param("status") String status);

}
