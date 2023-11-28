package com.tkp.learn.admin.service;

import com.tkp.learn.admin.model.po.RepaireDataPo;

import java.util.List;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/7/30
 * @version: 1.0
 */
public interface SaveOperationService {

    void saveOperation(List<RepaireDataPo> datas, String context);

}
