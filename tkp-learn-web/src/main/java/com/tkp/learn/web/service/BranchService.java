package com.tkp.learn.web.service;

/**
 * author: itw_lixg01
 * date: 2020-06-04 14:12
 * description:
 **/
public interface BranchService {

    /**
     * 根据人员工号获取所在机构简称
     * @param workNo 外勤TK号或内勤OA号
     * @return 机构简称
     */
    String getOrgShortName(String workNo);
}
