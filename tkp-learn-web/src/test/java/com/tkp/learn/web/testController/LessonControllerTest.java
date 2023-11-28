package com.tkp.learn.web.testController;

import com.alibaba.fastjson.JSON;
import com.tkp.learn.web.model.enums.IdentityEnum;
import com.tkp.learn.web.model.vo.LessonListCondition;
import com.tkp.learn.web.model.vo.PageParamVo;
import com.tkp.learn.web.security.JwtTokenProvider;
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
 * author: itw_lixg01
 * date: 2020-06-04 10:21
 * description:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
@Slf4j
public class LessonControllerTest {

    @Autowired
    private JwtTokenProvider jwtToken;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getLessonList() throws Exception {
        PageParamVo<LessonListCondition> paramVo = new PageParamVo<>();
        LessonListCondition condition = new LessonListCondition();

        List<String> categoryIds = new ArrayList<>();
        categoryIds.add("business_001");
        categoryIds.add("business_002");

        condition.setName("云夜话");
        condition.setCategoryIds(categoryIds);
        condition.setLabelCode("REQUIRED");

        paramVo.setPageNum(1);
        paramVo.setPageSize(10);
        paramVo.setCondition(condition);

//        String jwt = jwtToken.generateJWT("chenx", IdentityEnum.EMPLOYEE.getCode());
        String jwt = jwtToken.generateJWT("TK00041447", IdentityEnum.SALESMAN.getCode());
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/lesson/list.auth").header("Authorization", jwt)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(paramVo))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        LOGGER.info(mvcResult.getResponse().getContentAsString());
        Assert.assertNotNull(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void getLessonDetail() throws Exception {
        String lessonId = "videoTest03";
//        String jwt = jwtToken.generateJWT("chenx", IdentityEnum.EMPLOYEE.getCode());
        String jwt = jwtToken.generateJWT("TK00041447", IdentityEnum.SALESMAN.getCode());
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/lesson/getLessonDetail.auth").header("Authorization", jwt)
                .param("lessonId",lessonId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        LOGGER.info(mvcResult.getResponse().getContentAsString());
        Assert.assertNotNull(mvcResult.getResponse().getContentAsString());
    }

}

