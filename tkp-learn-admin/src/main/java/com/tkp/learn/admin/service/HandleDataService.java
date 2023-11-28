package com.tkp.learn.admin.service;

import com.tkp.learn.admin.model.po.RepaireDataPo;
import com.tkp.learn.admin.model.vo.HandleDataResultVo;
import com.tkp.learn.admin.model.vo.RepaireDataVo;

import java.util.List;


/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/7/24
 * @version: 1.0
 */
public interface HandleDataService {

    HandleDataResultVo handleData(List<RepaireDataPo> param, String context);

    HandleDataResultVo checkData(List<RepaireDataVo> param);

}
