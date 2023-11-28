package com.tkp.learn.pushcdn.task.start;

import com.tkp.learn.pushcdn.task.job.PushJsonToCdnJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class PushJsonToCdnJobStart {

    private static final Logger LOGGER = LoggerFactory.getLogger(PushJsonToCdnJobStart.class);

    @Autowired
    private PushJsonToCdnJob pushJsonToCdnJob;

    @Scheduled(fixedDelayString = "${config.job.taskTimeInterval}")
    public void pushJsonToCdnJobStart() {
        try {
            pushJsonToCdnJob.execute();
        } catch (Exception e) {
            LOGGER.error("推送json数据到cdn服务时发生异常，异常原因：", e);
        }
    }
}