package com.tkp.learn.admin.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tkp.learn.admin.dao.*;
import com.tkp.learn.admin.model.bo.IdentityBo;
import com.tkp.learn.admin.model.bo.LessonBo;
import com.tkp.learn.admin.model.common.LoginUser;
import com.tkp.learn.admin.model.enums.IdentityEnum;
import com.tkp.learn.admin.model.enums.TkopUserTypeEnum;
import com.tkp.learn.admin.model.po.DictBranchPo;
import com.tkp.learn.admin.model.po.DictLearningStylePo;
import com.tkp.learn.admin.remote.dto.tkpop.TkopUserDto;
import com.tkp.learn.admin.service.ReferService;
import com.tkp.learn.admin.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: itw_liupeng01
 * @createDate: 2020/7/24
 * @version: 1.0
 */
@Service
public class ReferServiceImpl implements ReferService {

    @Autowired
    private DictBranchSalesmanMapper dictBranchSalesmanMapper;
    @Autowired
    private SalesmanMapper salesmanMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private DictLearningStyleMapper dictLearningStyleMapper;

    @Autowired
    private LearnLessonMapper learnLessonMapper;

    @Override
    public List<DictBranchPo> getOrgList(String OrgId) {
        QueryWrapper<DictBranchPo> dictBranchWrapper = new QueryWrapper<>();
        if(ObjectUtils.isEmpty(OrgId)){
            TkopUserDto loginUser = SecurityUtils.getLoginUser();
            OrgId = loginUser.getOrg();
        }
        if(OrgId.equals("%")){
            dictBranchWrapper.eq("level","1").or().eq("level","2");
        }else{
            dictBranchWrapper.eq("id",OrgId.substring(0,2));
        }

        return dictBranchSalesmanMapper.selectList(dictBranchWrapper);
    }

    @Override
    public String getSalesmanName(String saleNo,String userType) {
        String message = "";
        LoginUser loginUser;
        if(userType.equals(TkopUserTypeEnum.SALESMAN.getCode())){
            loginUser = salesmanMapper.selectBySaleNo(saleNo);
        }else{
            loginUser = employeeMapper.selectByOaNo(saleNo);
        }
        if(ObjectUtils.isEmpty(loginUser)){
            message = "无此用户！";
        }else{
            message = loginUser.getName();
        }

        return message;
    }

    @Override
    public List<LessonBo> getLessonList() {
        return learnLessonMapper.getLessonList();
    }
    @Override
    public List<IdentityBo> getIdentity(){
        ArrayList<IdentityBo> IdentityBos = new ArrayList<>();
        IdentityEnum[] values = IdentityEnum.values();
        for (int i = 0; i < values.length; i++) {
            IdentityEnum value = values[i];
            IdentityBo identityBo = new IdentityBo();
            identityBo.setCode(value.getCode());
            identityBo.setName(value.getName());
            IdentityBos.add(identityBo);
        }
        return IdentityBos;
    }

    @Override
    public List<DictLearningStylePo> getdictLearningStyles(){
        return dictLearningStyleMapper.selectList(null);
    }


}
