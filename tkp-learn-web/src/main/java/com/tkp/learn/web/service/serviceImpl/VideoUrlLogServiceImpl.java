package com.tkp.learn.web.service.serviceImpl;

import com.tkp.learn.web.dao.VideoUrlLogMapper;
import com.tkp.learn.web.model.po.VideoUrlLogPo;
import com.tkp.learn.web.service.VideoUrlLogService;
import com.tkp.learn.web.utils.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * author: itw_lixg01
 * date: 2020-04-30 08:54
 * description:
 **/
@Service
@Slf4j
public class VideoUrlLogServiceImpl implements VideoUrlLogService {
    @Autowired
    private VideoUrlLogMapper videoUrlLogMapper;
    private static final String PREFIX = "OTO5G";


    @Override
    @Async
    public void logVideoUrl(String fileUuid, String userCode, String url, String clueId) {
        if (!fileUuid.startsWith(PREFIX)) {
            return;
        }

        VideoUrlLogPo po = new VideoUrlLogPo();
        po.setId(UUIDUtil.generatedUUID());
        po.setFileUuid(fileUuid);
        po.setUserCode(userCode);
        po.setClueId(clueId);
        po.setUrl(url);
        po.setCreateTime(LocalDateTime.now());

        videoUrlLogMapper.insert(po);
    }
}
