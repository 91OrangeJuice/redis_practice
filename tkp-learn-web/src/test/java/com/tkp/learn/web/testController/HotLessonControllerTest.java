package com.tkp.learn.web.testController;

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
public class HotLessonControllerTest {

    @Autowired
    private JwtTokenProvider jwtToken;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getHotLessons() throws Exception {
//        String jwt = jwtToken.generateJWT("chenx", IdentityEnum.EMPLOYEE.getCode());
//        String jwt = jwtToken.generateJWT("TK00041447", IdentityEnum.SALESMAN.getCode());
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/hotLesson/list")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        LOGGER.info(mvcResult.getResponse().getContentAsString());
        Assert.assertNotNull(mvcResult.getResponse().getContentAsString());
    }

}

