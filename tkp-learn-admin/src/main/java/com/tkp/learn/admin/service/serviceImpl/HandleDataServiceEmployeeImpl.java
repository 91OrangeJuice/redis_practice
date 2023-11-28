package com.tkp.learn.admin.service.serviceImpl;

import com.tkp.learn.admin.dao.EmployeeMapper;
import com.tkp.learn.admin.dao.RepairDataDetailMapper;
import com.tkp.learn.admin.model.bo.LessonBo;
import com.tkp.learn.admin.model.bo.StatisticsDataBo;
import com.tkp.learn.admin.model.constants.Constant;
import com.tkp.learn.admin.model.enums.ErrorMessageEnum;
import com.tkp.learn.admin.model.enums.OperationStatusEnum;
import com.tkp.learn.admin.model.enums.StudyStatusEnum;
import com.tkp.learn.admin.model.po.EmployeePo;
import com.tkp.learn.admin.model.po.EmployeeVideoBehaviorPo;
import com.tkp.learn.admin.model.po.RepaireDataPo;
import com.tkp.learn.admin.model.vo.HandleDataResultVo;
import com.tkp.learn.admin.model.vo.RepaireDataVo;
import com.tkp.learn.admin.remote.dto.tkpop.TkopUserDto;
import com.tkp.learn.admin.service.HandleDataService;
import com.tkp.learn.admin.util.CopyUtils;
import com.tkp.learn.admin.util.DateUtils;
import com.tkp.learn.admin.util.SecurityUtils;
import com.tkp.learn.admin.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/7/24
 * @version: 1.0
 */
@Service("HandleDataServiceEMPLOYEE")
public class HandleDataServiceEmployeeImpl implements HandleDataService {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private RepairDataDetailMapper repairDataDetailMapper;

    @Override
    public HandleDataResultVo handleData(List<RepaireDataPo> param, String context) {
        TkopUserDto loginUser = SecurityUtils.getLoginUser();
        HandleDataResultVo handleDataResultVo = new HandleDataResultVo();
        List<RepaireDataVo> successRepaireDataVos = new ArrayList<>();
        List<RepaireDataVo> errorRepaireDataVos = new ArrayList<>();
        List<RepaireDataVo> paramVo = new ArrayList<>();
        CopyUtils.copyBeanForList(param, paramVo, RepaireDataVo.class);
        if (!CollectionUtils.isEmpty(paramVo)) {
            List<String> lessonIds = paramVo.stream().map(RepaireDataVo::getLessonId).distinct().collect(Collectors.toList());
            List<LessonBo> lessonBos = repairDataDetailMapper.getLessonByIds(lessonIds);
            Map<String, LessonBo> lessonBoMap = lessonBos.stream().collect(Collectors.toMap(bean -> bean.getUuid(), bean -> bean));
            for (RepaireDataVo repaireDataVo : paramVo) {
                if (!lessonBoMap.containsKey(repaireDataVo.getLessonId())) {
                    repaireDataVo.setCurrentStatus(OperationStatusEnum.FAILED.getCode());
                    repaireDataVo.setErrorMsg(ErrorMessageEnum.NOMATCHLESSON.getName());
                    repaireDataVo.setUpdateTime(LocalDateTime.now());
                    repaireDataVo.setUpdateUser(loginUser.getLoginId());
                    errorRepaireDataVos.add(repaireDataVo);
                    continue;
                }
                LessonBo lessonBo = lessonBoMap.get(repaireDataVo.getLessonId());
                StatisticsDataBo statisticsDataBo = repairDataDetailMapper.getEmployeeStatistics(repaireDataVo.getLessonId(), repaireDataVo.getUserNo());
                EmployeeVideoBehaviorPo employeeVideoBehaviorPo = new EmployeeVideoBehaviorPo();
                buildEmployeeVideoBehaviorPo(employeeVideoBehaviorPo, lessonBo, statisticsDataBo, repaireDataVo, context);
                if (!ObjectUtils.isEmpty(employeeVideoBehaviorPo.getUuid())) {
                    repairDataDetailMapper.inserEmployeeVideoBehavior(employeeVideoBehaviorPo);
                }
                successRepaireDataVos.add(repaireDataVo);
            }
        }
        handleDataResultVo.setErrorRepaireDataVos(errorRepaireDataVos);
        handleDataResultVo.setSuccessRepaireDataVos(successRepaireDataVos);
        return handleDataResultVo;
    }

    private void buildEmployeeVideoBehaviorPo(EmployeeVideoBehaviorPo employeeVideoBehaviorPo, LessonBo lessonBo,
                                              StatisticsDataBo statisticsDataBo, RepaireDataVo repaireDataVo, String context) {
        TkopUserDto loginUser = SecurityUtils.getLoginUser();
        int duration = 0;
        if (ObjectUtils.isEmpty(statisticsDataBo)) {
            duration = lessonBo.getSize();
        } else {
            duration = lessonBo.getSize() - statisticsDataBo.getDuration();
            if (duration <= 0 || StudyStatusEnum.LEARNED.getCode().equals(statisticsDataBo.getStatus())) {
                repaireDataVo.setCurrentStatus(OperationStatusEnum.COMPLETED.getCode());
                repaireDataVo.setUpdateUser(loginUser.getLoginId());
                repaireDataVo.setUpdateTime(LocalDateTime.now());
                repaireDataVo.setApprover(loginUser.getLoginId());
                repaireDataVo.setApproveTime(LocalDateTime.now());
                repaireDataVo.setApproverContext(context);
                return;
            }
        }
        LocalDateTime firstPlayAt = null;
        LocalDateTime lastStopAt = DateUtils.getLocalDateTime4Date(repaireDataVo.getLearnedTime());
        firstPlayAt = lastStopAt.minusSeconds(duration);
        employeeVideoBehaviorPo.setUuid(UUIDUtil.generatedUUID());
        employeeVideoBehaviorPo.setOaNo(repaireDataVo.getUserNo());
        employeeVideoBehaviorPo.setColumnId(Constant.OTO5GCOURSES);
        employeeVideoBehaviorPo.setCreateTime(LocalDateTime.now());
        employeeVideoBehaviorPo.setTryTimes(0);
        employeeVideoBehaviorPo.setErrorMessage(Constant.HANDREPAIR);
        employeeVideoBehaviorPo.setDuration(duration);
        employeeVideoBehaviorPo.setLessonId(lessonBo.getUuid());
        employeeVideoBehaviorPo.setFirstPlayAt(firstPlayAt);
        employeeVideoBehaviorPo.setLastStopAt(lastStopAt);
        employeeVideoBehaviorPo.setReceiveTime(LocalDateTime.now());
        repaireDataVo.setCurrentStatus(OperationStatusEnum.RECOVERED.getCode());
        repaireDataVo.setUpdateUser(loginUser.getLoginId());
        repaireDataVo.setUpdateTime(LocalDateTime.now());
        repaireDataVo.setApprover(loginUser.getLoginId());
        repaireDataVo.setApproveTime(LocalDateTime.now());
        repaireDataVo.setApproverContext(context);
        repaireDataVo.setBehaviorId(employeeVideoBehaviorPo.getUuid());
    }

    @Override
    public HandleDataResultVo checkData(List<RepaireDataVo> param) {
        HandleDataResultVo handleDataResultVo = new HandleDataResultVo();
        List<RepaireDataVo> successRepaireDataVos = new ArrayList<>();
        List<RepaireDataVo> errorRepaireDataVos = new ArrayList<>();
        TkopUserDto loginUser = SecurityUtils.getLoginUser();
        String branchCode = loginUser.getBranchCode();
        List<String> userNoList = param.stream().map(RepaireDataVo::getUserNo).collect(Collectors.toList());
        List<EmployeePo> employeePos = employeeMapper.queryEmployeePoByUserIds(userNoList);
        Map<String, EmployeePo> map = employeePos.stream().collect(Collectors.toMap(bean -> bean.getOaNo(), bean -> bean));
        for (RepaireDataVo repaireDataVo : param) {
            String userId = repaireDataVo.getUserNo();
            StringBuffer errorMsg = new StringBuffer();
            if (map.containsKey(userId)) {
                EmployeePo employeePo = map.get(userId);
                if (!ObjectUtils.isEmpty(repaireDataVo.getBranchCode())) {
                    if (!repaireDataVo.getBranchCode().equals(employeePo.getEbaOrgCode1())) {
                        errorMsg.append(ErrorMessageEnum.COMPANYNOTMATCH.getName());
                    } else if (!branchCode.equals(employeePo.getEbaOrgCode1())) {
                        if (!branchCode.equals(Constant.HEADOFFICE)) {
                            errorMsg.append(ErrorMessageEnum.NOPERMISSION.getName());
                        }
                    }
                } else {
                    errorMsg.append(ErrorMessageEnum.NONECOMPANY.getName());
                }
                if (!ObjectUtils.isEmpty(repaireDataVo.getUserName())) {
                    if (!repaireDataVo.getUserName().equals(employeePo.getUserName())) {
                        errorMsg.append(ErrorMessageEnum.NAMENOTMATCH.getName());
                    }
                } else {
                    errorMsg.append(ErrorMessageEnum.NONENAME.getName());
                }
            } else {
                errorMsg.append(ErrorMessageEnum.NONEUSER.getName());
            }
            if (!ObjectUtils.isEmpty(errorMsg)) {
                repaireDataVo.setErrorMsg(errorMsg.toString());
                errorRepaireDataVos.add(repaireDataVo);
            } else {
                successRepaireDataVos.add(repaireDataVo);
            }
        }
        handleDataResultVo.setErrorRepaireDataVos(errorRepaireDataVos);
        handleDataResultVo.setSuccessRepaireDataVos(successRepaireDataVos);
        return handleDataResultVo;
    }
}
