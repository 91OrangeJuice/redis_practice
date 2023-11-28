package com.tkp.learn.admin.service.serviceImpl;

import com.tkp.learn.admin.dao.RepairDataDetailMapper;
import com.tkp.learn.admin.dao.SalesmanMapper;
import com.tkp.learn.admin.model.bo.LessonBo;
import com.tkp.learn.admin.model.bo.StatisticsDataBo;
import com.tkp.learn.admin.model.constants.Constant;
import com.tkp.learn.admin.model.enums.ErrorMessageEnum;
import com.tkp.learn.admin.model.enums.OperationStatusEnum;
import com.tkp.learn.admin.model.enums.StudyStatusEnum;
import com.tkp.learn.admin.model.po.RepaireDataPo;
import com.tkp.learn.admin.model.po.SalesmanPo;
import com.tkp.learn.admin.model.po.VideoBehaviorPo;
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
import org.springframework.util.StringUtils;

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
@Service("HandleDataServiceSALESMAN")
public class HandleDataServiceSalesmanImpl implements HandleDataService {

    @Autowired
    private SalesmanMapper salesmanMapper;
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
                StatisticsDataBo statisticsDataBo = repairDataDetailMapper.getSaleStatistics(repaireDataVo.getLessonId(), repaireDataVo.getUserNo());
                VideoBehaviorPo videoBehaviorPo = new VideoBehaviorPo();
                buildVideoBehaviorPo(videoBehaviorPo, lessonBo, statisticsDataBo, repaireDataVo, context);
                if (!ObjectUtils.isEmpty(videoBehaviorPo.getId())) {
                    repairDataDetailMapper.inserVideoBehavior(videoBehaviorPo);
                }
                successRepaireDataVos.add(repaireDataVo);
            }
        }
        handleDataResultVo.setErrorRepaireDataVos(errorRepaireDataVos);
        handleDataResultVo.setSuccessRepaireDataVos(successRepaireDataVos);
        return handleDataResultVo;
    }

    private void buildVideoBehaviorPo(VideoBehaviorPo videoBehaviorPo, LessonBo lessonBo,
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
        videoBehaviorPo.setId(UUIDUtil.generatedUUID());
        videoBehaviorPo.setSalesmanId(repaireDataVo.getUserNo());
        videoBehaviorPo.setColumnId(Constant.OTO5GCOURSES);
        videoBehaviorPo.setCreateTime(LocalDateTime.now());
        videoBehaviorPo.setRetryTimes(0);
        videoBehaviorPo.setErrorMessage(Constant.HANDREPAIR);
        videoBehaviorPo.setDuration(duration);
        videoBehaviorPo.setLessonId(lessonBo.getUuid());
        videoBehaviorPo.setFirstPlayAt(firstPlayAt);
        videoBehaviorPo.setLastStopAt(lastStopAt);
        videoBehaviorPo.setReceiveTime(LocalDateTime.now());
        repaireDataVo.setCurrentStatus(OperationStatusEnum.RECOVERED.getCode());
        repaireDataVo.setUpdateUser(loginUser.getLoginId());
        repaireDataVo.setUpdateTime(LocalDateTime.now());
        repaireDataVo.setApprover(loginUser.getLoginId());
        repaireDataVo.setApproveTime(LocalDateTime.now());
        repaireDataVo.setApproverContext(context);
        repaireDataVo.setBehaviorId(videoBehaviorPo.getId());
    }

    @Override
    public HandleDataResultVo checkData(List<RepaireDataVo> param) {
        HandleDataResultVo handleDataResultVo = new HandleDataResultVo();
        List<RepaireDataVo> successRepaireDataVos = new ArrayList<>();
        List<RepaireDataVo> errorRepaireDataVos = new ArrayList<>();
        TkopUserDto loginUser = SecurityUtils.getLoginUser();
        String branchCode = loginUser.getBranchCode();
        List<String> userNoList = param.stream().map(RepaireDataVo::getUserNo).collect(Collectors.toList());
        List<SalesmanPo> salesmanPos = salesmanMapper.querySalesmanByUserIds(userNoList);
        Map<String, SalesmanPo> map = salesmanPos.stream().collect(Collectors.toMap(bean -> bean.getSaleNo(), bean -> bean));
        for (RepaireDataVo repaireDataVo : param) {
            String userId = repaireDataVo.getUserNo();
            StringBuffer errorMsg = new StringBuffer();
            if (map.containsKey(userId)) {
                SalesmanPo salesmanPo = map.get(userId);
                if (!ObjectUtils.isEmpty(repaireDataVo.getBranchCode())) {
                    if (!repaireDataVo.getBranchCode().equals(salesmanPo.getBranchCode())) {
                        errorMsg.append(ErrorMessageEnum.COMPANYNOTMATCH.getName());
                    } else if (!branchCode.equals(salesmanPo.getBranchCode())) {
                        if (!branchCode.equals(Constant.HEADOFFICE)) {
                            errorMsg.append(ErrorMessageEnum.NOPERMISSION.getName());
                        }
                    }
                } else {
                    errorMsg.append(ErrorMessageEnum.NONECOMPANY.getName());
                }
                if (!ObjectUtils.isEmpty(repaireDataVo.getUserName())) {
                    if (!repaireDataVo.getUserName().equals(salesmanPo.getName())) {
                        errorMsg.append(ErrorMessageEnum.NAMENOTMATCH.getName());
                    }
                } else {
                    errorMsg.append(ErrorMessageEnum.NONENAME.getName());
                }
            } else {
                errorMsg.append(ErrorMessageEnum.NONEUSER.getName());
            }
            if (StringUtils.isEmpty(repaireDataVo.getLessonId())) {
                errorMsg.append(ErrorMessageEnum.NOMATCHLESSON.getName());
            }
            if (ObjectUtils.isEmpty(repaireDataVo.getLearnedTime())) {
                errorMsg.append(ErrorMessageEnum.NONELEARNDATE.getName());
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
