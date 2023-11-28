package com.tkp.learn.admin.service.serviceImpl;

import com.tkp.learn.admin.dao.RepairDataDetailMapper;
import com.tkp.learn.admin.model.po.OperationPo;
import com.tkp.learn.admin.model.po.RepaireDataPo;
import com.tkp.learn.admin.remote.dto.tkpop.TkopUserDto;
import com.tkp.learn.admin.service.SaveOperationService;
import com.tkp.learn.admin.util.SecurityUtils;
import com.tkp.learn.admin.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/7/30
 * @version: 1.0
 */
@Service
public class SaveOperationServiceImpl implements SaveOperationService {

    @Autowired
    private RepairDataDetailMapper repairDataDetailMapper;

    @Override
    @Transactional
    public void saveOperation(List<RepaireDataPo> datas, String context) {
        if (!CollectionUtils.isEmpty(datas)) {
            List<OperationPo> operationPos = new ArrayList<>();
            for (RepaireDataPo repaireDataPo : datas) {
                OperationPo operationPo = new OperationPo();
                buildOperationPo(operationPo, repaireDataPo, context);
                operationPos.add(operationPo);
            }
            if (!CollectionUtils.isEmpty(operationPos)) {
                repairDataDetailMapper.saveOperation(operationPos);
            }
        }
    }

    private void buildOperationPo(OperationPo operationPo, RepaireDataPo repaireDataPo, String context) {
        TkopUserDto loginUser = SecurityUtils.getLoginUser();
        operationPo.setId(UUIDUtil.generatedUUID());
        operationPo.setDetailId(repaireDataPo.getId());
        operationPo.setOperationType(repaireDataPo.getCurrentStatus());
        operationPo.setOperationUser(ObjectUtils.isEmpty(repaireDataPo.getUpdateUser()) ? loginUser.getLoginId() : repaireDataPo.getUpdateUser());
        operationPo.setOperationTime(ObjectUtils.isEmpty(repaireDataPo.getUpdateTime()) ? LocalDateTime.now() : repaireDataPo.getUpdateTime());
        operationPo.setOperationContent(context);
        operationPo.setErrorMessage(repaireDataPo.getErrorMsg());
    }
}
