package com.tkp.learn.web.service.serviceImpl;

import com.tkp.learn.web.actuator.exception.ServiceException;
import com.tkp.learn.web.actuator.model.ErrorCode;
import com.tkp.learn.web.dao.LearnLessonMapper;
import com.tkp.learn.web.dao.RelLessonMengzhuMapper;
import com.tkp.learn.web.dao.TaskLessonScanMapper;
import com.tkp.learn.web.model.po.LearnLessonPo;
import com.tkp.learn.web.model.po.TaskLessonScanPo;
import com.tkp.learn.web.model.vo.LiveEndVideoDataVo;
import com.tkp.learn.web.utils.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class LiveEndService {
    private static final  Logger LOGGER=LoggerFactory.getLogger(LiveEndService.class);
   @Autowired
  private RelLessonMengzhuMapper relLessonMengzhuMapper;
   @Autowired
   private LearnLessonMapper learnLessonMapper;
   @Autowired
   private TaskLessonScanMapper taskLessonScanMapper;
     @Transactional
   public int  updateLiveLesson(LiveEndVideoDataVo liveEndVideoDataVo){
       String url=liveEndVideoDataVo.getWebinar().getUrl();
       String videoId=url.substring(url.lastIndexOf('/')+1);
         String lessonId = getLessonId(videoId);
         LearnLessonPo baseFile = getBaseFilePo(lessonId);
         LocalDateTime liveBeginTime = getLiveBeginTime(baseFile);
         LocalDateTime liveEndTime = getLiveEndTime(baseFile);
         int duration= (int)ChronoUnit.SECONDS.between(liveBeginTime,liveEndTime);
         setTurnOn(baseFile);
         return  learnLessonMapper.updateByLessonId(lessonId,liveEndTime,duration);
   }

    private LocalDateTime getLiveEndTime(LearnLessonPo baseFile) {
        if(!ObjectUtils.isEmpty(baseFile.getLiveEndTime())){
            LOGGER.error("课程{}结束时间已设置，不能重复设置",baseFile.getUuid());
            throw new ServiceException("课程:"+baseFile.getUuid()+"结束时间已设置，不能重复设置!", ErrorCode.SERVICE_ERROR);
        }
        return LocalDateTime.now();
    }

    private LearnLessonPo getBaseFilePo(String lessonId) {
        LearnLessonPo learnLessonPo = learnLessonMapper.selectById(lessonId);
        if(ObjectUtils.isEmpty(learnLessonPo)){
            LOGGER.error("未根据映射表LessonId{}查找到base_file中的课程！",lessonId);
            throw new ServiceException("未根据映射表lessonId"+lessonId+"查找到base_file中的课程！", ErrorCode.SERVICE_ERROR);
        }
        return learnLessonPo;
    }

    private LocalDateTime getLiveBeginTime(LearnLessonPo baseFile) {
        LocalDateTime liveBeginTime=baseFile.getLiveBeginTime();
        if(ObjectUtils.isEmpty(liveBeginTime)){
            LOGGER.error("易学习：课程{}开始时间未设置",baseFile.getLiveBeginTime());
            throw new ServiceException("易学习：课程"+baseFile.getLiveBeginTime()+"开始时间未设置!", ErrorCode.SERVICE_ERROR);
        }
        return liveBeginTime;
    }

    private String getLessonId(String videoId) {
        String lessonId= relLessonMengzhuMapper.selectByVideoId(videoId);
        if(ObjectUtils.isEmpty(lessonId)){
            LOGGER.error("盟主视频{}与易学习课程之间缺少映射关系!",videoId);
            throw new ServiceException("盟主视频"+videoId+"与易学习课程缺少映射关系!", ErrorCode.SERVICE_ERROR);
        }
        return lessonId;
    }

    public  void setTurnOn(LearnLessonPo learnLessonPo) {
        String uuid=taskLessonScanMapper.selectByLessonId(learnLessonPo.getUuid());
        if(!ObjectUtils.isEmpty(uuid)){
            taskLessonScanMapper.updateEnableTrue(uuid);
            return ;
        }
        TaskLessonScanPo taskLessonScanPo=new TaskLessonScanPo();
        taskLessonScanPo.setId(UUIDUtil.generatedUUID());
        taskLessonScanPo.setCreateTime(new Date());
        taskLessonScanPo.setEnable(true);
        taskLessonScanPo.setLessonId(learnLessonPo.getUuid());
        taskLessonScanMapper.insert(taskLessonScanPo);
    }


}
