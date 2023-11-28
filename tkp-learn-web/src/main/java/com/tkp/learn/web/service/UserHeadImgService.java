package com.tkp.learn.web.service;

/**
 * author: itw_lixg01
 * date: 2020-06-04 10:07
 * description:
 **/
public interface UserHeadImgService {

    /**
     * 保存用户头像信息
     * @param workNo 外勤TK号或内勤OA号
     * @param headImgUrl 头像地址
     * @return 受影响的条目数。大于0代表保存成功
     */
    int saveUserHeadImg(String workNo, String headImgUrl);
}
