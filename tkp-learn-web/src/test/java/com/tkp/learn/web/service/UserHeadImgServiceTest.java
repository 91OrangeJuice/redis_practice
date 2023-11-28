package com.tkp.learn.web.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * author: itw_lixg01
 * date: 2020-06-04 12:26
 * description:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@Slf4j
public class UserHeadImgServiceTest {

    @Autowired
    private UserHeadImgService userHeadImgService;

    @Test
    public void testSaveUserHeadImg() {
        String workNo = "TK00020314";
        String headImgUrl = "http://wxyl.pension.taikang.com/WX/repository/tkp-agent-web/img/headPortrait.png";
        userHeadImgService.saveUserHeadImg(workNo, headImgUrl);
    }
}
