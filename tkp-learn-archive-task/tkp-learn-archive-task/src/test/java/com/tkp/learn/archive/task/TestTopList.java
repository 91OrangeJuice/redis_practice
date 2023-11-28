package com.tkp.learn.archive.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.tkp.learn.archive.task.dao.LogTaskLearnDataMapper;
import com.tkp.learn.archive.task.dao.TaskTopListMapper;
import com.tkp.learn.archive.task.model.po.TaskTopListPo;
import com.tkp.learn.archive.task.model.po.VideoBehaviorHisPo;
import com.tkp.learn.archive.task.service.HandleDayTaskService;
import com.tkp.learn.archive.task.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/6/5
 * @version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class TestTopList {
    @Autowired
    private LogTaskLearnDataMapper logTaskLearnDataMapper;
    @Autowired
    private HandleDayTaskService handleDayTaskService;
    @Autowired
    private TaskTopListMapper taskTopListMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testTaskTolListService() throws Exception {
//        handleDayTaskService.handleDayTask();
        List<VideoBehaviorHisPo> bos = taskTopListMapper.queryVideoBehaviorHisBakPoPage(0, 500);
        Assert.assertNotNull(bos);
    }

    @Test
    public void testDateAddUtil() {
        Date now = DateUtils.addDate(DateUtils.getDateByString("2020-01-31"), 30, Calendar.DAY_OF_MONTH);
        LOGGER.error(DateUtils.getStringByDate(now));
    }

    @Test
    public void testHandleTopListTask() throws Exception {
        ObjectMapper json = new ObjectMapper();
        List<TaskTopListPo> pos = new ArrayList<>();
        TaskTopListPo po = new TaskTopListPo();
        po.setYear(2020);
        po.setMonth(1);
        pos.add(po);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/task/handleTopListTask")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.writeValueAsString(pos))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        LOGGER.info(mvcResult.getResponse().getContentAsString());
        Assert.assertNotNull(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testInitDayTask() throws Exception {
        ObjectMapper json = new ObjectMapper();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/task/initDayTask")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        LOGGER.info(mvcResult.getResponse().getContentAsString());
        Assert.assertNotNull(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void initDayTask() {
        try {
            Gson json=new Gson();
            List<VideoBehaviorHisPo> bos=taskTopListMapper.queryVideoBehaviorHisBakPoPage(0, 500);
            LOGGER.error(json.toJson(bos));
            Assert.assertNotNull(bos);
        } catch (Exception e) {
            LOGGER.error("异常啦", e);
        }
    }

}
