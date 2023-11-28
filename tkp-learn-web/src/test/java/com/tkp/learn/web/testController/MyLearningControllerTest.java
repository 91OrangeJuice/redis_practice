package com.tkp.learn.web.testController;

import com.alibaba.fastjson.JSON;
import com.tkp.learn.web.model.enums.IdentityEnum;
import com.tkp.learn.web.model.vo.LearnHistoryCondition;
import com.tkp.learn.web.model.vo.PageParamVo;
import com.tkp.learn.web.model.vo.RankingListPage;
import com.tkp.learn.web.security.JwtTokenProvider;
import com.tkp.learn.web.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/5/29
 * @version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
@Slf4j
public class MyLearningControllerTest {

    @Autowired
    private JwtTokenProvider jwtToken;
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testGetMyLearningDetail() throws Exception {
        String jwt = jwtToken.generateJWT("TK00020314", IdentityEnum.SALESMAN.getCode());
//        String jwt = jwtToken.generateJWT("TK00041447", IdentityEnum.SALESMAN.getCode());
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/myLearning/getMyLearningDetail.auth").header("Authorization", jwt)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        LOGGER.info(mvcResult.getResponse().getContentAsString());
        Assert.assertNotNull(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testGetMyLearningDetailSalesman() throws Exception {
//        String jwt = jwtToken.generateJWT("mengqg", IdentityEnum.EMPLOYEE.getCode());
        String jwt = jwtToken.generateJWT("mengqg", IdentityEnum.SALESMAN.getCode());
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/myLearning/getMyLearningDetail.auth").header("Authorization", jwt)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        LOGGER.info(mvcResult.getResponse().getContentAsString());
        Assert.assertNotNull(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testDateUtil() {
        LOGGER.info(DateUtils.getYesterdayBegin());
        LOGGER.info(DateUtils.getYesterdayEnd());
    }

    @Test
    public void getRankingListMonth() throws Exception {
        String jwt = jwtToken.generateJWT("TK00020314", IdentityEnum.SALESMAN.getCode());
        PageParamVo<RankingListPage> rankingListPage=new PageParamVo<>();
        RankingListPage rankPage=new RankingListPage();
        rankPage.setYear(2020);
        rankPage.setMonth(5);
        rankingListPage.setPageNum(0);
        rankingListPage.setPageSize(10);
        rankingListPage.setCondition(rankPage);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/myLearning/getRankingListMonth.auth")
                .header("Authorization", jwt)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(rankingListPage))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        LOGGER.info(mvcResult.getResponse().getContentAsString());
        Assert.assertNotNull(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void getRankingListYear() throws Exception {
        String jwt = jwtToken.generateJWT("mengqg", IdentityEnum.EMPLOYEE.getCode());
        PageParamVo<RankingListPage> rankingListPage=new PageParamVo<>();
        RankingListPage rankPage=new RankingListPage();
        rankPage.setYear(2020);
        rankingListPage.setPageNum(0);
        rankingListPage.setPageSize(10);
        rankingListPage.setCondition(rankPage);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/myLearning/getRankingListYear.auth")
                .header("Authorization", jwt)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(rankingListPage))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        LOGGER.info(mvcResult.getResponse().getContentAsString());
        Assert.assertNotNull(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void getLearnHistory() throws Exception {
        PageParamVo<LearnHistoryCondition> paramVo = new PageParamVo<>();
        LearnHistoryCondition condition = new LearnHistoryCondition();

        List<String> statuses = new ArrayList<>();
        statuses.add("learned");
//        statuses.add("learning");
        condition.setLearnStatus(statuses);

        List<String> labelCodes = new ArrayList<>();
//        labelCodes.add("REQUIRED");
        condition.setLabelCodes(labelCodes);

        paramVo.setPageNum(1);
        paramVo.setPageSize(5);
        paramVo.setCondition(condition);

//        String jwt = jwtToken.generateJWT("chenx", IdentityEnum.EMPLOYEE.getCode());
        String jwt = jwtToken.generateJWT("TK00036072", IdentityEnum.SALESMAN.getCode());
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/myLearning/getMyLearnHistory.auth").header("Authorization", jwt)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(paramVo))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        LOGGER.info(mvcResult.getResponse().getContentAsString());
        Assert.assertNotNull(mvcResult.getResponse().getContentAsString());
    }
}

