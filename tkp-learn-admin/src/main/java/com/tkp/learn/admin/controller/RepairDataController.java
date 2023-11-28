package com.tkp.learn.admin.controller;

import com.tkp.learn.admin.actuator.model.ErrorCode;
import com.tkp.learn.admin.actuator.model.ViewBean;
import com.tkp.learn.admin.model.bo.RepairDataDetailBo;
import com.tkp.learn.admin.model.common.PageParamVo;
import com.tkp.learn.admin.model.common.PageVo;
import com.tkp.learn.admin.model.enums.ErrorMessageEnum;
import com.tkp.learn.admin.model.vo.*;
import com.tkp.learn.admin.service.RepairDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/7/24
 * @version: 1.0
 */
@RestController
@RequestMapping("/main")
@Slf4j
public class RepairDataController {

    @Autowired
    private RepairDataService repairDataService;

    @PostMapping("/queryFeedbackRepairData.auth")
    public ViewBean<PageVo<RepairDataDetailBo>> queryFeedbackRepairData(@RequestBody PageParamVo<QueryDataParamVo> param) {
        return ViewBean.createSuccess(repairDataService.queryFeedbackRepairDate(param));

    }

    @PostMapping("/queryCommitRepairData.auth")
    public ViewBean<PageVo<RepairDataDetailBo>> queryCommitRepairData(@RequestBody PageParamVo<QueryDataParamVo> param) {
        return ViewBean.createSuccess(repairDataService.queryCommitRepairData(param));

    }

    @PostMapping("/queryApproveRepairData.auth")
    public ViewBean<PageVo<RepairDataDetailBo>> queryApproveRepairData(@RequestBody PageParamVo<QueryDataParamVo> param) {
        return ViewBean.createSuccess(repairDataService.queryApproveRepairData(param));

    }

    /**
     * 提交
     *
     * @param param
     * @return
     */
    @PostMapping("/commitRepairData.auth")
    public ViewBean<HandleDataResultVo> commitRepairData(@RequestBody @Validated RepaireDataParamVo param, BindingResult binding) {
        if (binding.hasErrors()) {
            for (ObjectError error : binding.getAllErrors()) {
                LOGGER.info("提交请求参数有误:{}", error.getDefaultMessage());
            }
            return ViewBean.createFail(ErrorMessageEnum.NONEDATA.getName(), ErrorCode.PARAM_ERROR);
        }
        return ViewBean.createSuccess(repairDataService.commitRepairData(param.getIds(), param.getContext()));
    }

    /**
     * 驳回
     *
     * @param param
     * @return
     */
    @PostMapping("/rejectRepairData.auth")
    public ViewBean<HandleDataResultVo> rejectRepairData(@RequestBody @Validated RejectDataParamVo param, BindingResult binding) {
        if (binding.hasErrors()) {
            for (ObjectError error : binding.getAllErrors()) {
                LOGGER.info("驳回请求参数有误:{}", error.getDefaultMessage());
            }
            return ViewBean.createFail(ErrorMessageEnum.NONEDATA.getName(), ErrorCode.PARAM_ERROR);
        }
        return ViewBean.createSuccess(repairDataService.rejectRepairData(param.getIds(), param.getContext()));
    }

    /**
     * 保存修复数据接口
     *
     * @param param
     * @return
     */
    @PostMapping("/saveRepairData.auth")
    public ViewBean<HandleDataResultVo> saveRepairData(@RequestBody @Validated List<RepaireDataVo> param, BindingResult binding) {
        if (binding.hasErrors()) {
            for (ObjectError error : binding.getAllErrors()) {
                LOGGER.info("保存请求参数有误:{}", error.getDefaultMessage());
            }
            return ViewBean.createFail(ErrorMessageEnum.NONEDATA.getName(), ErrorCode.PARAM_ERROR);
        }
        return repairDataService.saveRepairData(param);
    }

    /**
     * 审批并处理修复数据接口
     *
     * @param param
     * @return
     */
    @PostMapping("/handleRepairData.auth")
    public ViewBean<HandleDataResultVo> handleRepairData(@RequestBody @Validated RepaireDataParamVo param, BindingResult binding) {
        if (binding.hasErrors()) {
            for (ObjectError error : binding.getAllErrors()) {
                LOGGER.info("审批处理请求参数有误:{}", error.getDefaultMessage());
            }
            return ViewBean.createFail(ErrorMessageEnum.NONEDATA.getName(), ErrorCode.PARAM_ERROR);
        }
        HandleDataResultVo vo = repairDataService.handleRepairData(param.getIds(), param.getContext());
        return ViewBean.createSuccess(vo);
    }

}
