package com.tkp.learn.pushcdn.task.service;

import com.tkp.learn.pushcdn.task.actuator.model.ViewBean;
import com.tkp.learn.pushcdn.task.model.enums.FileFormat;
import com.tkp.learn.pushcdn.task.model.enums.FileName;
import com.tkp.learn.pushcdn.task.utils.JsonUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by itw_wangshuai01 on 2020/6/10.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class CdnServiceTest {

    @Autowired
    private CdnService cdnService;

    @Test
    public void uploadFile() throws Exception {
        final ViewBean<String> viewBean = ViewBean.createSuccess("wangshuai");
        final String jsonData = JsonUtil.parseObjToJson(viewBean);
        final Boolean result = cdnService.uploadFile(jsonData.getBytes(), FileName.HOT_LESSONS, FileFormat.JSON);
        Assert.assertTrue(result);
    }

}