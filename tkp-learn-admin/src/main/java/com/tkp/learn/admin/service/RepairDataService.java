package com.tkp.learn.admin.service;

import com.tkp.learn.admin.actuator.model.ViewBean;
import com.tkp.learn.admin.model.bo.RepairDataDetailBo;
import com.tkp.learn.admin.model.common.PageParamVo;
import com.tkp.learn.admin.model.common.PageVo;
import com.tkp.learn.admin.model.vo.HandleDataResultVo;
import com.tkp.learn.admin.model.vo.QueryDataParamVo;
import com.tkp.learn.admin.model.vo.RepaireDataVo;

import java.util.List;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/7/24
 * @version: 1.0
 */
public interface RepairDataService {

    PageVo<RepairDataDetailBo> queryFeedbackRepairDate(PageParamVo<QueryDataParamVo> param);

    PageVo<RepairDataDetailBo> queryCommitRepairData(PageParamVo<QueryDataParamVo> param);

    PageVo<RepairDataDetailBo> queryApproveRepairData(PageParamVo<QueryDataParamVo> param);

    ViewBean<HandleDataResultVo> saveRepairData(List<RepaireDataVo> param);

    HandleDataResultVo handleRepairData(List<String> ids, String context);

    HandleDataResultVo commitRepairData(List<String> ids, String context);

    HandleDataResultVo rejectRepairData(List<String> ids, String context);

}
