package com.tkp.learn.admin.service;

import com.tkp.learn.admin.model.bo.IdentityBo;
import com.tkp.learn.admin.model.bo.LessonBo;
import com.tkp.learn.admin.model.po.DictBranchPo;
import com.tkp.learn.admin.model.po.DictLearningStylePo;

import java.util.List;

/**
 * @description:
 * @author: itw_liupeng01
 * @createDate: 2020/7/24
 * @version: 1.0
 */
public interface ReferService {

    List<DictBranchPo> getOrgList(String OrgId);

    String getSalesmanName(String saleNo, String userType);

    List<LessonBo> getLessonList();

    List<IdentityBo> getIdentity();

    List<DictLearningStylePo> getdictLearningStyles();
}
