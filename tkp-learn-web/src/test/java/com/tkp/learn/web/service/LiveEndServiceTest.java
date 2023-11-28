package com.tkp.learn.web.service;

import com.tkp.learn.web.model.po.LearnLessonPo;
import com.tkp.learn.web.model.vo.LiveEndVideoDataVo;
import com.tkp.learn.web.model.vo.WebActivityVo;
import com.tkp.learn.web.service.serviceImpl.LiveEndService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@Slf4j
public class LiveEndServiceTest {
    @Autowired
   private LiveEndService liveEndService;
   @Test
   @Transactional
   public void updateLiveLesson(){
       LiveEndVideoDataVo liveEndVideoDataVo = new LiveEndVideoDataVo();
       WebActivityVo webActivityVo=new WebActivityVo();
       webActivityVo.setUrl("http://www.zmengzhu.com/play/10001291");
       liveEndVideoDataVo.setWebinar(webActivityVo);
       int count=liveEndService.updateLiveLesson(liveEndVideoDataVo);
       System.out.println(count);
   }
   @Transactional
   @Test
   public void setTurnOn(){
       LearnLessonPo base=new LearnLessonPo();
       base.setUuid("1");
       liveEndService.setTurnOn(base);
   }

}
