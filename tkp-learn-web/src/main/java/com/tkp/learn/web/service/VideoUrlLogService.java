package com.tkp.learn.web.service;

/**
 * author: itw_lixg01
 * date: 2020-04-30 08:51
 * description: 视频链接日志记录
 **/
public interface VideoUrlLogService {

    /**
     * 异步记录盟主视频URL
     * @param fileUuid 当前课程ID
     * @param userCode 用户唯一标识
     * @param url 视频链接
     * @param clueId 线索id
     */
    void logVideoUrl(String fileUuid, String userCode, String url, String clueId);
}
