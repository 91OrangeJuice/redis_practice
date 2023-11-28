package com.tkp.learn.admin.service.serviceImpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tkp.learn.admin.actuator.model.ViewBean;
import com.tkp.learn.admin.dao.RepairDataDetailMapper;
import com.tkp.learn.admin.model.bo.RepairDataDetailBo;
import com.tkp.learn.admin.model.common.PageParamVo;
import com.tkp.learn.admin.model.common.PageVo;
import com.tkp.learn.admin.model.enums.ErrorMessageEnum;
import com.tkp.learn.admin.model.enums.IdentityEnum;
import com.tkp.learn.admin.model.enums.OperationStatusEnum;
import com.tkp.learn.admin.model.po.RepaireDataPo;
import com.tkp.learn.admin.model.vo.HandleDataResultVo;
import com.tkp.learn.admin.model.vo.QueryDataParamVo;
import com.tkp.learn.admin.model.vo.RepaireDataVo;
import com.tkp.learn.admin.remote.dto.tkpop.TkopUserDto;
import com.tkp.learn.admin.service.HandleDataService;
import com.tkp.learn.admin.service.RepairDataService;
import com.tkp.learn.admin.service.SaveOperationService;
import com.tkp.learn.admin.service.factory.StrategyContext4Business;
import com.tkp.learn.admin.util.CopyUtils;
import com.tkp.learn.admin.util.PageUtils;
import com.tkp.learn.admin.util.SecurityUtils;
import com.tkp.learn.admin.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/7/24
 * @version: 1.0
 */
@Service
public class RepairDataServiceImpl implements RepairDataService {

    @Autowired
    private StrategyContext4Business strategyContext4Business;
    @Autowired
    private SaveOperationService saveOperationService;
    @Autowired
    private RepairDataDetailMapper repairDataDetailMapper;


    @Override
    public PageVo<RepairDataDetailBo> queryFeedbackRepairDate(PageParamVo<QueryDataParamVo> pageParamVo) {
        QueryDataParamVo condition = pageParamVo.getCondition();
        String branchCode = "";
        if (condition != null) {
            branchCode = condition.getBranchCode();
        }
        if (ObjectUtils.isEmpty(branchCode)) {
            TkopUserDto loginUser = SecurityUtils.getLoginUser();
            branchCode = loginUser.getBranchCode();

        }
        branchCode = branchCode.equals("%") ? "" : branchCode;
        condition.setBranchCode(branchCode);
        ArrayList<OperationStatusEnum> operationStatusEnums = new ArrayList<>();
        operationStatusEnums.add(OperationStatusEnum.COMMIT);
        operationStatusEnums.add(OperationStatusEnum.REJECT);
        operationStatusEnums.add(OperationStatusEnum.RECOVERED);
        operationStatusEnums.add(OperationStatusEnum.COMPLETED);
        condition.setOperationType(operationStatusEnums);

        Page<RepairDataDetailBo> Page = new Page<>(pageParamVo.getPageNum(), pageParamVo.getPageSize());
        IPage<RepairDataDetailBo> repairDataDetailSearchList = repairDataDetailMapper.getRepairDataDetailSearchList(Page, condition);
        return PageUtils.buildPageVo(repairDataDetailSearchList);
    }

    public PageVo<RepairDataDetailBo> queryCommitRepairData(PageParamVo<QueryDataParamVo> pageParamVo) {

        QueryDataParamVo queryDataParamVo = new QueryDataParamVo();
        TkopUserDto loginUser = SecurityUtils.getLoginUser();
        String branchCode = loginUser.getBranchCode();
        branchCode = branchCode.equals("%") ? "" : branchCode;
        queryDataParamVo.setBranchCode(branchCode);

        ArrayList<OperationStatusEnum> operationStatusEnums = new ArrayList<>();
        operationStatusEnums.add(OperationStatusEnum.SAVE);
        operationStatusEnums.add(OperationStatusEnum.REJECT);
        queryDataParamVo.setOperationType(operationStatusEnums);

        Page<RepairDataDetailBo> Page = new Page<>(pageParamVo.getPageNum(), pageParamVo.getPageSize());
        IPage<RepairDataDetailBo> repairDataDetailSearchList = repairDataDetailMapper.getRepairDataDetailSearchList(Page, queryDataParamVo);
        return PageUtils.buildPageVo(repairDataDetailSearchList);
    }

    public PageVo<RepairDataDetailBo> queryApproveRepairData(PageParamVo<QueryDataParamVo> pageParamVo) {
        QueryDataParamVo queryDataParamVo = new QueryDataParamVo();
        TkopUserDto loginUser = SecurityUtils.getLoginUser();
        String branchCode = loginUser.getBranchCode();
        branchCode = branchCode.equals("%") ? "" : branchCode;
        queryDataParamVo.setBranchCode(branchCode);

        ArrayList<OperationStatusEnum> operationStatusEnums = new ArrayList<>();
        operationStatusEnums.add(OperationStatusEnum.COMMIT);
        queryDataParamVo.setOperationType(operationStatusEnums);

        Page<RepairDataDetailBo> Page = new Page<>(pageParamVo.getPageNum(), pageParamVo.getPageSize());
        IPage<RepairDataDetailBo> repairDataDetailSearchList = repairDataDetailMapper.getRepairDataDetailSearchList(Page, queryDataParamVo);
        return PageUtils.buildPageVo(repairDataDetailSearchList);
    }

    @Override
    @Transactional
    public ViewBean<HandleDataResultVo> saveRepairData(List<RepaireDataVo> params) {
        if (CollectionUtils.isEmpty(params)) {
            return new ViewBean<HandleDataResultVo>();
        }
        List<RepaireDataVo> employeeData = new ArrayList<>();
        List<RepaireDataVo> salesManData = new ArrayList<>();
        HandleDataResultVo handleDataResultVo = new HandleDataResultVo();
        HandleDataResultVo relEmpVo = null;
        HandleDataResultVo relSalVo = null;
        employeeData = params.stream().filter(repaireDataVo -> IdentityEnum.EMPLOYEE.getCode().equals(repaireDataVo.getUserType())).collect(Collectors.toList());
        salesManData = params.stream().filter(repaireDataVo -> IdentityEnum.SALESMAN.getCode().equals(repaireDataVo.getUserType())).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(employeeData)) {
            HandleDataService handleDataEmpService = (HandleDataService) strategyContext4Business.getStrategy(IdentityEnum.EMPLOYEE.getCode(), HandleDataService.class);
            relEmpVo = handleDataEmpService.checkData(employeeData);
        }
        if (!CollectionUtils.isEmpty(salesManData)) {
            HandleDataService handleDataSalService = (HandleDataService) strategyContext4Business.getStrategy(IdentityEnum.SALESMAN.getCode(), HandleDataService.class);
            relSalVo = handleDataSalService.checkData(salesManData);
        }
        buildHandleDataResultVo(handleDataResultVo, relEmpVo, relSalVo);
        saveData(handleDataResultVo);
        return ViewBean.createSuccess(handleDataResultVo);
    }

    @Override
    @Transactional
    public HandleDataResultVo handleRepairData(List<String> ids, String context) {
        if (CollectionUtils.isEmpty(ids)) {
            return null;
        }
        List<RepaireDataPo> repaireDataPos = repairDataDetailMapper.queryRepairDataDetail(ids);
        List<RepaireDataPo> employeeData = new ArrayList<>();
        List<RepaireDataPo> salesManData = new ArrayList<>();
        HandleDataResultVo handleDataResultVo = new HandleDataResultVo();
        HandleDataResultVo relEmpVo = null;
        HandleDataResultVo relSalVo = null;
        employeeData = repaireDataPos.stream().filter(repaireDataVo -> IdentityEnum.EMPLOYEE.getCode().equals(repaireDataVo.getUserType())).collect(Collectors.toList());
        salesManData = repaireDataPos.stream().filter(repaireDataVo -> IdentityEnum.SALESMAN.getCode().equals(repaireDataVo.getUserType())).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(employeeData)) {
            HandleDataService handleDataEmpService = (HandleDataService) strategyContext4Business.getStrategy(IdentityEnum.EMPLOYEE.getCode(), HandleDataService.class);
            relEmpVo = handleDataEmpService.handleData(employeeData,context);
        }
        if (!CollectionUtils.isEmpty(salesManData)) {
            HandleDataService handleDataSalService = (HandleDataService) strategyContext4Business.getStrategy(IdentityEnum.SALESMAN.getCode(), HandleDataService.class);
            relSalVo = handleDataSalService.handleData(salesManData,context);
        }
        buildHandleDataResultVo(handleDataResultVo, relEmpVo, relSalVo);
        List<RepaireDataVo> vos = handleDataResultVo.queryAll();
        List<RepaireDataPo> pos = new ArrayList<>();
        CopyUtils.copyBeanForList(vos, pos, RepaireDataPo.class);
        //更新处理成功数据的状态
        repairDataDetailMapper.updateRepairDataDetail(pos);
        //保存日志
        saveOperationService.saveOperation(pos, context);
        return handleDataResultVo;
    }

    @Override
    @Transactional
    public HandleDataResultVo commitRepairData(List<String> ids, String context) {
        changeStatus4RejectAndCommit(ids, null, OperationStatusEnum.COMMIT);
        HandleDataResultVo vo = new HandleDataResultVo();
        vo.setMessage(ErrorMessageEnum.COMMITSUCCESS.getName());
        return vo;
    }

    @Override
    @Transactional
    public HandleDataResultVo rejectRepairData(List<String> ids, String context) {
        changeStatus4RejectAndCommit(ids, context, OperationStatusEnum.REJECT);
        HandleDataResultVo vo = new HandleDataResultVo();
        vo.setMessage(ErrorMessageEnum.REJECTSUCCESS.getName());
        return vo;
    }

    private void changeStatus4RejectAndCommit(List<String> ids, String context, OperationStatusEnum operationStatusEnum) {
        TkopUserDto tkopUserDto = SecurityUtils.getLoginUser();
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }
        List<RepaireDataPo> updatePos = new ArrayList<>();
        for (String id : ids) {
            RepaireDataPo repaireDataPo = new RepaireDataPo();
            repaireDataPo.setId(id);
            repaireDataPo.setUpdateTime(LocalDateTime.now());
            repaireDataPo.setUpdateUser(tkopUserDto.getLoginId());
            if (OperationStatusEnum.REJECT.equals(operationStatusEnum)) {
                repaireDataPo.setApproveTime(repaireDataPo.getUpdateTime());
                repaireDataPo.setApprover(repaireDataPo.getUpdateUser());
                repaireDataPo.setApproverContext(context);
            } else {
                repaireDataPo.setLastCommitTime(repaireDataPo.getUpdateTime());
                repaireDataPo.setLastCommitUser(repaireDataPo.getUpdateUser());
            }
            repaireDataPo.setCurrentStatus(operationStatusEnum.getCode());
            updatePos.add(repaireDataPo);
        }
        repairDataDetailMapper.updateRepairDataDetail(updatePos);
        saveOperationService.saveOperation(updatePos, context);
    }

    private void saveData(HandleDataResultVo relVo) {
        TkopUserDto loginUser = SecurityUtils.getLoginUser();
        List<RepaireDataPo> insertPos = new ArrayList<>();
        List<RepaireDataPo> updatePos = new ArrayList<>();
        List<RepaireDataPo> allPos = new ArrayList<>();
        List<RepaireDataVo> sucessVos = relVo.getSuccessRepaireDataVos();
        List<RepaireDataPo> sucessPos = new ArrayList<>();
        CopyUtils.copyBeanForList(sucessVos, sucessPos, RepaireDataPo.class);
        for (RepaireDataPo po : sucessPos) {
            if (StringUtils.isEmpty(po.getId())) {
                po.setId(UUIDUtil.generatedUUID());
                po.setCreateTime(LocalDateTime.now());
                po.setCreateUser(loginUser.getLoginId());
                po.setLastCommitUser(loginUser.getLoginId());
                po.setLastCommitTime(LocalDateTime.now());
                po.setCurrentStatus(OperationStatusEnum.SAVE.getCode());
                insertPos.add(po);
            } else {
                po.setUpdateTime(LocalDateTime.now());
                po.setUpdateUser(loginUser.getLoginId());
                po.setLastCommitUser(loginUser.getLoginId());
                po.setLastCommitTime(LocalDateTime.now());
                po.setCurrentStatus(OperationStatusEnum.SAVE.getCode());
                updatePos.add(po);
            }
        }
        if (!CollectionUtils.isEmpty(insertPos)) {
            repairDataDetailMapper.insertRepairDataDetail(insertPos);
            allPos.addAll(insertPos);
        }
        if (!CollectionUtils.isEmpty(updatePos)) {
            repairDataDetailMapper.updateRepairDataDetail(updatePos);
            allPos.addAll(updatePos);
        }
        if (!CollectionUtils.isEmpty(allPos)) {
            saveOperationService.saveOperation(allPos, null);
        }
    }

    private void buildHandleDataResultVo(HandleDataResultVo handleDataResultVo, HandleDataResultVo relEmpVo, HandleDataResultVo relSalVo) {
        if (!ObjectUtils.isEmpty(relEmpVo)) {
            if (!CollectionUtils.isEmpty(relEmpVo.getSuccessRepaireDataVos())) {
                handleDataResultVo.setSuccessRepaireDataVos(relEmpVo.getSuccessRepaireDataVos());
            }
            if (!CollectionUtils.isEmpty(relEmpVo.getErrorRepaireDataVos())) {
                handleDataResultVo.setErrorRepaireDataVos(relEmpVo.getErrorRepaireDataVos());
            }
        }
        if (!ObjectUtils.isEmpty(relSalVo)) {
            if (!CollectionUtils.isEmpty(relSalVo.getSuccessRepaireDataVos())) {
                if (!CollectionUtils.isEmpty(handleDataResultVo.getSuccessRepaireDataVos())) {
                    handleDataResultVo.getSuccessRepaireDataVos().addAll(relSalVo.getSuccessRepaireDataVos());
                } else {
                    handleDataResultVo.setSuccessRepaireDataVos(relSalVo.getSuccessRepaireDataVos());
                }
            }
            if (!CollectionUtils.isEmpty(relSalVo.getErrorRepaireDataVos())) {
                if (!CollectionUtils.isEmpty(handleDataResultVo.getErrorRepaireDataVos())) {
                    handleDataResultVo.getErrorRepaireDataVos().addAll(relSalVo.getErrorRepaireDataVos());
                } else {
                    handleDataResultVo.setErrorRepaireDataVos(relSalVo.getErrorRepaireDataVos());
                }
            }
        }
        int sucess = handleDataResultVo.querySuccessSize();
        int error = handleDataResultVo.queryErrorSize();
        handleDataResultVo.setSuccessCount(sucess);
        handleDataResultVo.setErrorCount(error);
        handleDataResultVo.setTotal(sucess + error);
    }

}
