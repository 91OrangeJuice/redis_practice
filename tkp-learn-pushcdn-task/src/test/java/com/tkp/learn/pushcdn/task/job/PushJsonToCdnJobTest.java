package com.tkp.learn.pushcdn.task.job;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by itw_wangshuai01 on 2020/6/15.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class PushJsonToCdnJobTest {

    @Autowired
    private PushJsonToCdnJob pushJsonToCdnJob;

    @Test
    public void execute() throws Exception {
        pushJsonToCdnJob.execute();
    }

}