package com.tkp.learn.admin;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.tkp.learn.admin.model.enums.IdentityEnum;
import com.tkp.learn.admin.model.enums.LearningStyleEnum;
import com.tkp.learn.admin.model.vo.RepaireDataParamVo;
import com.tkp.learn.admin.model.vo.RepaireDataVo;
import com.tkp.learn.admin.security.JwtTokenProvider;
import com.tkp.learn.admin.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
public class TestRepairDataController {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JwtTokenProvider jwtToken;

    @Test
    public void testSaveRepairData() throws Exception {
        List<RepaireDataVo> param=new ArrayList<>();
        RepaireDataVo vo=new RepaireDataVo();
        vo.setId("5ADE5CA7DB0A4D2098A4538A3CBB02F3");
        vo.setUserNo("TK00036296");
        vo.setUserName("于佳");
        vo.setBranchCode("83");
        vo.setUserType(IdentityEnum.SALESMAN.getCode());
        vo.setWhitePhone("18716971111");
        vo.setLessonName("课程23期云夜话");
        vo.setLessonId("OTO5G62");
        vo.setLearningStyle(LearningStyleEnum.WECHAT.getCode());
        vo.setQuestionDescript("1.已学完，但课程列表中还是显示学习中;");
        vo.setLearnedTime(new Date());

        RepaireDataVo vo3=new RepaireDataVo();
        vo3.setId("43533FCEFCE445E18542EC1CC73F5574");
        vo3.setUserNo("TK00039340");
        vo3.setUserName("金京");
        vo3.setBranchCode("67");
        vo3.setUserType(IdentityEnum.SALESMAN.getCode());
        vo3.setWhitePhone("18716932222");
        vo3.setLessonName("课程23期云夜话");
        vo3.setLessonId("OTO5G61");
        vo3.setLearningStyle(LearningStyleEnum.OTHERS.getCode());
        vo3.setQuestionDescript("1.已学完，但课程列表中还是显示学习中;");
        vo3.setLearnedTime(new Date());

        RepaireDataVo vo4=new RepaireDataVo();
        vo4.setId("13649770EADB4C9D8C23DF52544E45E2");
        vo4.setUserNo("tianhx08");
        vo4.setUserName("田贺欣");
        vo4.setBranchCode("71");
        vo4.setUserType(IdentityEnum.EMPLOYEE.getCode());
        vo4.setWhitePhone("18716913333");
        vo4.setLessonName("课程23期云夜话");
        vo4.setLessonId("OTO5G61");
        vo4.setLearningStyle(LearningStyleEnum.COMPUTER.getCode());
        vo4.setQuestionDescript("1.已学完，但课程列表中还是显示学习中;");
        vo4.setLearnedTime(new Date());

        RepaireDataVo vo2=new RepaireDataVo();
        vo2.setId("EAF7B76FA7384DE39E735F864AFF549F");
        vo2.setUserNo("liyan238");
        vo2.setUserName("李妍");
        vo2.setBranchCode("66");
        vo2.setUserType(IdentityEnum.EMPLOYEE.getCode());
        vo2.setWhitePhone("18710004444");
        vo2.setLessonId("OTO5G62");
        vo2.setLessonName("课程23期云夜话");
        vo2.setLearningStyle(LearningStyleEnum.CLIENT.getCode());
        vo2.setQuestionDescript("1.已学完，但课程列表中还是显示学习中;");
        vo2.setLearnedTime(new Date());

        param.add(vo);
       /* param.add(vo2);
        param.add(vo3);
        param.add(vo4);*/
        String jwt = jwtToken.generateJWT("yinjm03");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/main/saveRepairData.auth")
                .header("Authorization", jwt)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSON(param).toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        LOGGER.info(mvcResult.getResponse().getContentAsString());
        Assert.assertNotNull(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testCommitRepairData() throws Exception{
        RepaireDataParamVo vo=new RepaireDataParamVo();
        List<String> ids=new ArrayList<>();
        ids.add("13649770EADB4C9D8C23DF52544E45E2");
        ids.add("43533FCEFCE445E18542EC1CC73F5574");
        ids.add("5ADE5CA7DB0A4D2098A4538A3CBB02F3");
        ids.add("EAF7B76FA7384DE39E735F864AFF549F");
        vo.setIds(ids);

        String jwt = jwtToken.generateJWT("yinjm03");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/main/commitRepairData.auth")
                .header("Authorization", jwt)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSON(vo).toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        LOGGER.info(mvcResult.getResponse().getContentAsString());
        Assert.assertNotNull(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testRejectRepairData() throws Exception{
        RepaireDataParamVo vo=new RepaireDataParamVo();
        List<String> ids=new ArrayList<>();
        ids.add("EAF7B76FA7384DE39E735F864AFF549F");
        vo.setIds(ids);
        vo.setContext("不同意");
        String jwt = jwtToken.generateJWT("yinjm03");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/main/rejectRepairData.auth")
                .header("Authorization", jwt)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSON(vo).toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        LOGGER.info(mvcResult.getResponse().getContentAsString());
        Assert.assertNotNull(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testHandleRepairData() throws Exception{
        RepaireDataParamVo vo=new RepaireDataParamVo();
        List<String> ids=new ArrayList<>();
        ids.add("13649770EADB4C9D8C23DF52544E45E2");
        ids.add("43533FCEFCE445E18542EC1CC73F5574");
        ids.add("5ADE5CA7DB0A4D2098A4538A3CBB02F3");
        vo.setIds(ids);
        vo.setContext("同意");

        String jwt = jwtToken.generateJWT("yinjm03");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/main/handleRepairData.auth")
                .header("Authorization", jwt)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSON(vo).toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        LOGGER.info(mvcResult.getResponse().getContentAsString());
        Assert.assertNotNull(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void test(){
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now=LocalDateTime.now();
        String strDate1 = dtf2.format(now);
        LocalDateTime end=now.minusSeconds(120);
        String strDate2 = dtf2.format(end);
        LOGGER.info("aaaaaaaaa{}aaaaaaaaa{}",strDate1,strDate2);
    }


    @Test
    public void testLogin() throws Exception {
        String tempToken="IVRLUF9PUEAjeWluam0wMzE1OTU5MDE1MzgzMjVTc28jMTIzNTQ2NTQz";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/login")
                .param("tempToken",tempToken)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        LOGGER.info(mvcResult.getResponse().getContentAsString());
        Assert.assertNotNull(mvcResult.getResponse().getContentAsString());
    }

    /*@Test
    public void test() {
        Gson json = new Gson();
        Workbook wb = null;
        Sheet sheet = null;
        Row row = null;
        List<Map<String, String>> list = null;
        String cellData = null;
        String filePath = "C:\\Users\\itw_wangsc01\\Desktop\\易学习学习时长修复_7-6~7-10 - 副本.xlsx";
        String columns[] = {"saleNo", "name", "lessonCode", "lessonName", "lastStopDate"};
        wb = readExcel(filePath);
        if (wb != null) {
            //用来存放表中数据
            list = new ArrayList<Map<String, String>>();
            //获取第一个sheet
            sheet = wb.getSheetAt(0);
            //获取最大行数
            int rownum = sheet.getPhysicalNumberOfRows();
            //获取最大列数
            int colnum = columns.length;
            for (int i = 1; i < rownum; i++) {
                Map<String, String> map = new LinkedHashMap<String, String>();
                row = sheet.getRow(i);
                if (row != null) {
                    for (int j = 0; j < colnum; j++) {
                        cellData = (String) getCellFormatValue(row.getCell(j));
                        map.put(columns[j], cellData);
                    }
                } else {
                    break;
                }
                list.add(map);
            }
        }
        System.out.println(json.toJson(list));
    }

    //读取excel
    public static Workbook readExcel(String filePath) {
        Workbook wb = null;
        if (filePath == null) {
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            if (".xls".equals(extString)) {
                return wb = new HSSFWorkbook(is);
            } else if (".xlsx".equals(extString)) {
                return wb = new XSSFWorkbook(is);
            } else {
                return wb = null;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return wb;
    }

    public static Object getCellFormatValue(Cell cell){
        Object cellValue = null;
        if (cell != null) {
            //判断cell类型
            CellType cellType = cell.getCellType();
            if (cellType.compareTo(CellType.NUMERIC) == 0) {
                cellValue = String.valueOf(cell.getNumericCellValue());
            }
            if (cellType.compareTo(CellType.FORMULA) == 0) {
                //判断cell是否为日期格式
                if (DateUtil.isCellDateFormatted(cell)) {
                    //转换为日期格式YYYY-mm-dd
                    cellValue = cell.getDateCellValue();
                } else {
                    //数字
                    cellValue = String.valueOf(cell.getNumericCellValue());
                }
            }
            if (cellType.compareTo(CellType.STRING) == 0) {
                cellValue = cell.getRichStringCellValue().getString();
            }
        }
        return cellValue;
    }
*/
}
