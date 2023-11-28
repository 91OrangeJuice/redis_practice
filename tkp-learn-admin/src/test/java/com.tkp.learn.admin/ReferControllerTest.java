package com.tkp.learn.admin;

import com.alibaba.fastjson.JSON;
import com.tkp.learn.admin.model.common.PageParamVo;
import com.tkp.learn.admin.model.enums.IdentityEnum;
import com.tkp.learn.admin.model.enums.LearningStyleEnum;
import com.tkp.learn.admin.model.enums.OperationStatusEnum;
import com.tkp.learn.admin.model.vo.QueryDataParamVo;
import com.tkp.learn.admin.model.vo.RepaireDataVo;
import com.tkp.learn.admin.security.JwtTokenProvider;
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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/7/27
 * @version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
@Slf4j
public class ReferControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JwtTokenProvider jwtToken;
    @Test
    public void getOrgList() throws Exception {
        //String jwt = jwtToken.generateJWT("TK00042522");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/refer/getOrgList")//.header("Authorization", jwt)
                //.param("OrgId","%")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        LOGGER.info(mvcResult.getResponse().getContentAsString());
        Assert.assertNotNull(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void getSalesmanName() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/refer/getSalesmanName")
                    .param("saleNo","TK00042522")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        LOGGER.info(mvcResult.getResponse().getContentAsString());
        Assert.assertNotNull(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void getLessonList() throws Exception {
        //List<RepaireDataVo> param=new ArrayList<>();
        //String jwt = jwtToken.generateJWT("yinjm03");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/refer/getLessonList")
                //.header("Authorization", jwt)
                .contentType(MediaType.APPLICATION_JSON)
                //.content(JSON.toJSON(param).toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        LOGGER.info(mvcResult.getResponse().getContentAsString());
        Assert.assertNotNull(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void queryFeedbackRepairData() throws Exception {
        String jwt = jwtToken.generateJWT("yinjm03");

        PageParamVo<QueryDataParamVo> pageParamVo = new PageParamVo<>();
        pageParamVo.setPageNum(1);
        pageParamVo.setPageSize(10);

        QueryDataParamVo queryDataParamVo = new QueryDataParamVo();
        queryDataParamVo.setBranchCode("%");
        //queryDataParamVo.setLessonId("OTO5G54");
        //queryDataParamVo.setName("于佳");
        pageParamVo.setCondition(queryDataParamVo);

        System.out.println("传入参数：" + JSON.toJSON(pageParamVo).toString());
        ResultActions reaction =this.mockMvc.perform(MockMvcRequestBuilders.post("/main/queryFeedbackRepairData.auth")
                .header("Authorization", jwt)
                .contentType(MediaType.APPLICATION_JSON)//请求体时json
                .content(JSON.toJSON(pageParamVo).toString()));
        reaction.andExpect(MockMvcResultMatchers.status().isOk());
        MvcResult mvcResult =reaction.andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void queryCommitRepairData() throws Exception {
        String jwt = jwtToken.generateJWT("TK00039340");

        PageParamVo<QueryDataParamVo> pageParamVo = new PageParamVo<>();
        pageParamVo.setPageNum(1);
        pageParamVo.setPageSize(10);

        System.out.println("传入参数：" + JSON.toJSON(pageParamVo).toString());
        ResultActions reaction =this.mockMvc.perform(MockMvcRequestBuilders.post("/main/queryCommitRepairData.auth")
                .header("Authorization", jwt)
                .contentType(MediaType.APPLICATION_JSON)//请求体时json
                .content(JSON.toJSON(pageParamVo).toString()));
        reaction.andExpect(MockMvcResultMatchers.status().isOk());
        MvcResult mvcResult =reaction.andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }
    @Test
    public void queryApproveRepairData() throws Exception {
        String jwt = jwtToken.generateJWT("TK00039340");

        PageParamVo<QueryDataParamVo> pageParamVo = new PageParamVo<>();
        pageParamVo.setPageNum(1);
        pageParamVo.setPageSize(10);

        System.out.println("传入参数：" + JSON.toJSON(pageParamVo).toString());
        ResultActions reaction =this.mockMvc.perform(MockMvcRequestBuilders.post("/main/queryApproveRepairData.auth")
                .header("Authorization", jwt)
                .contentType(MediaType.APPLICATION_JSON)//请求体时json
                .content(JSON.toJSON(pageParamVo).toString()));
        reaction.andExpect(MockMvcResultMatchers.status().isOk());
        MvcResult mvcResult =reaction.andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void getIdentity() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/refer/getIdentity")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        LOGGER.info(mvcResult.getResponse().getContentAsString());
        Assert.assertNotNull(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void getdictLearningStyles() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/refer/getdictLearningStyles")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        LOGGER.info(mvcResult.getResponse().getContentAsString());
        Assert.assertNotNull(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void aaa() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/refer/aaa")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        LOGGER.info(mvcResult.getResponse().getContentAsString());
        Assert.assertNotNull(mvcResult.getResponse().getContentAsString());
    }
}
