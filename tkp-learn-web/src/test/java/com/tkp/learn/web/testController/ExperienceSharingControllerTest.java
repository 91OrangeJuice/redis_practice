package com.tkp.learn.web.testController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tkp.learn.web.model.enums.IdentityEnum;
import com.tkp.learn.web.model.vo.AddExperienceSharingVo;
import com.tkp.learn.web.model.vo.ExperienceSharingVo;
import com.tkp.learn.web.model.vo.OrderVo;
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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
@Slf4j
public class ExperienceSharingControllerTest {
    @Autowired
    private JwtTokenProvider jwtToken;
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();


    @Test
    public void testAddExperienceSharing() throws Exception {
        String jwt = jwtToken.generateJWT("mengqg", IdentityEnum.EMPLOYEE.getCode());//SALESMAN  EMPLOYEE  mengqg  TK00043186

        AddExperienceSharingVo experienceSharingRequest = new AddExperienceSharingVo();
        experienceSharingRequest.setContent("yes");
        experienceSharingRequest.setLessonId("OTO5GTwo");

        System.out.println("传入参数：" + objectMapper.writeValueAsString(experienceSharingRequest));
        ResultActions reaction =this.mockMvc.perform(MockMvcRequestBuilders.post("/experiencesharing/addExperienceSharing.auth")
                .header("Authorization", jwt)
                .contentType(MediaType.APPLICATION_JSON)//请求体时json
                .content(objectMapper.writeValueAsString(experienceSharingRequest)));
        reaction.andExpect(MockMvcResultMatchers.status().isOk());
        MvcResult mvcResult =reaction.andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testGetExperienceSharingList() throws Exception {
        String jwt = jwtToken.generateJWT("mengqg", IdentityEnum.EMPLOYEE.getCode());//SALESMAN  EMPLOYEE  mengqg  TK00043186

        PageParamVo<ExperienceSharingVo> pageParamVo = new PageParamVo<>();
        pageParamVo.setPageNum(1);
        pageParamVo.setPageSize(2);

        ArrayList<OrderVo> arrayList = new ArrayList<>();
        arrayList.add(new OrderVo("publish_time",""));
        ExperienceSharingVo experienceSharingVo = new ExperienceSharingVo();
        experienceSharingVo.setSearchCondition("myOrg");
        experienceSharingVo.setOrderByCondition("newly");
        experienceSharingVo.setLessonId("OTO5GTwo");
        pageParamVo.setOrders(arrayList);
        pageParamVo.setCondition(experienceSharingVo);

        System.out.println("传入参数：" + objectMapper.writeValueAsString(pageParamVo));
        ResultActions reaction =this.mockMvc.perform(MockMvcRequestBuilders.post("/experiencesharing/getExperienceSharingList.auth")
                .header("Authorization", jwt)
                .contentType(MediaType.APPLICATION_JSON)//请求体时json
                .content(objectMapper.writeValueAsString(pageParamVo)));
        reaction.andExpect(MockMvcResultMatchers.status().isOk());
        MvcResult mvcResult =reaction.andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

/*    @Test
    public void testAddPraises() throws Exception {
        String jwt = jwtToken.generateJWT("zhangmin", IdentityEnum.EMPLOYEE.getCode());//SALESMAN  EMPLOYEE  mengqg  TK00043186
        ExperienceSharingVo experienceSharingRequest = new ExperienceSharingVo();
        //experienceSharingRequest.setCourseExperienceId("6CC19D0394A24E75B936E5F5CA4893ED");
        System.out.println("传入参数：" + objectMapper.writeValueAsString(experienceSharingRequest));
        ResultActions reaction =this.mockMvc.perform(MockMvcRequestBuilders.post("/experiencesharing/addPraises.auth")
                .header("Authorization", jwt)
                .contentType(MediaType.APPLICATION_JSON)//请求体时json
                .content(objectMapper.writeValueAsString(experienceSharingRequest)));
        reaction.andExpect(MockMvcResultMatchers.status().isOk());
        MvcResult mvcResult =reaction.andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }*/

    @Test
    public void testAddPraises() throws Exception {
        String jwt = jwtToken.generateJWT("zhangmin", IdentityEnum.EMPLOYEE.getCode());//SALESMAN  EMPLOYEE  mengqg  TK00043186
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/experiencesharing/addPraises.auth").header("Authorization", jwt)
                .param("courseExperienceId","6CC19D0394A24E75B936E5F5CA4893ED")
                .accept(MediaType.APPLICATION_JSON))

                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        LOGGER.info(mvcResult.getResponse().getContentAsString());
        Assert.assertNotNull(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testDeleteExperienceSharing() throws Exception {
        String jwt = jwtToken.generateJWT("mengqg", IdentityEnum.EMPLOYEE.getCode());//SALESMAN  EMPLOYEE  mengqg  TK00043186
        ExperienceSharingVo experienceSharingRequest = new ExperienceSharingVo();
        //experienceSharingRequest.setCourseExperienceId("4C66DEBEBDC246A8A341135AC9353B47");
        System.out.println("传入参数：" + objectMapper.writeValueAsString(experienceSharingRequest));
        ResultActions reaction =this.mockMvc.perform(MockMvcRequestBuilders.post("/experiencesharing/deleteExperienceSharing.auth")
                .header("Authorization", jwt)
                .contentType(MediaType.APPLICATION_JSON)//请求体时json
                .content(objectMapper.writeValueAsString(experienceSharingRequest)));
        reaction.andExpect(MockMvcResultMatchers.status().isOk());
        MvcResult mvcResult =reaction.andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testGetMyLearnDataLessonExperienceList() throws Exception {
        String jwt = jwtToken.generateJWT("mengqg", IdentityEnum.EMPLOYEE.getCode());//SALESMAN  EMPLOYEE  mengqg  TK00043186

        PageParamVo pageParamVo = new PageParamVo<>();
        pageParamVo.setPageNum(1);
        pageParamVo.setPageSize(5);
        System.out.println("传入参数：" + objectMapper.writeValueAsString(pageParamVo));
        ResultActions reaction =this.mockMvc.perform(MockMvcRequestBuilders.post("/experiencesharing/getMyLearnDataLessonExperienceList.auth")
                .header("Authorization", jwt)
                .contentType(MediaType.APPLICATION_JSON)//请求体时json
                .content(objectMapper.writeValueAsString(pageParamVo)));
        reaction.andExpect(MockMvcResultMatchers.status().isOk());
        MvcResult mvcResult =reaction.andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void updatePraisesStatusByUserId() throws Exception {
        String jwt = jwtToken.generateJWT("mengqg", IdentityEnum.EMPLOYEE.getCode());//SALESMAN  EMPLOYEE  mengqg  TK00043186
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/experiencesharing/updatePraisesStatusByUserId.auth").header("Authorization", jwt)

                .accept(MediaType.APPLICATION_JSON))

                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        LOGGER.info(mvcResult.getResponse().getContentAsString());
        Assert.assertNotNull(mvcResult.getResponse().getContentAsString());
    }
    @Test
    public void getThisLearnDataLessonExperience() throws Exception {
        String jwt = jwtToken.generateJWT("mengqg", IdentityEnum.EMPLOYEE.getCode());//SALESMAN  EMPLOYEE  mengqg  TK00043186
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/experiencesharing/getThisLearnDataLessonExperience.auth").header("Authorization", jwt)
                .param("courseExperienceId","6CC19D0394A24E75B936E5F5CA4893ED")
                .accept(MediaType.APPLICATION_JSON))

                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        LOGGER.info(mvcResult.getResponse().getContentAsString());
        Assert.assertNotNull(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void getColumnFileDetail() throws Exception {
        String jwt = jwtToken.generateJWT("mengqg", IdentityEnum.EMPLOYEE.getCode());//SALESMAN  EMPLOYEE  mengqg  TK00043186
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/column/getFileDetail.auth").header("Authorization", jwt)
                .param("id","OTO5G36")
                .accept(MediaType.APPLICATION_JSON))

                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        LOGGER.info(mvcResult.getResponse().getContentAsString());
        Assert.assertNotNull(mvcResult.getResponse().getContentAsString());
    }
}
