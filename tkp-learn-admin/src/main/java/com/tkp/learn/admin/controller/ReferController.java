package com.tkp.learn.admin.controller;

import com.tkp.learn.admin.actuator.model.ViewBean;
import com.tkp.learn.admin.model.bo.IdentityBo;
import com.tkp.learn.admin.model.bo.LessonBo;
import com.tkp.learn.admin.model.po.DictBranchPo;
import com.tkp.learn.admin.model.po.DictLearningStylePo;
import com.tkp.learn.admin.service.ReferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/7/29
 * @version: 1.0
 */
@RestController
@RequestMapping("/refer")
@Slf4j
public class ReferController {
    @Autowired
    private ReferService referService;
    @GetMapping("getOrgList")
    public ViewBean<List<DictBranchPo>> getOrgList(@RequestParam(value="OrgId",required=false) String OrgId) {
        return ViewBean.createSuccess(referService.getOrgList(OrgId));
    }
    @GetMapping("getSalesmanName")
    public ViewBean<String> getSalesmanInfo(@RequestParam("saleNo") String saleNo ,@RequestParam("userType") String userType) {
        return ViewBean.createSuccess(referService.getSalesmanName(saleNo,userType));
    }
    @GetMapping("getLessonList")
    public ViewBean<List<LessonBo>> getLessonList() {
        return ViewBean.createSuccess(referService.getLessonList());
    }

    @GetMapping("getIdentity")
    public ViewBean<List<IdentityBo>> getIdentity() {
        return ViewBean.createSuccess(referService.getIdentity());
    }

    @GetMapping("getdictLearningStyles")
    public ViewBean<List<DictLearningStylePo>> getdictLearningStyles() {
        return ViewBean.createSuccess(referService.getdictLearningStyles());
    }

}
