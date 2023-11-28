package com.tkp.learn.admin.model.vo;

import com.tkp.learn.admin.model.enums.OperationStatusEnum;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/7/24
 * @version: 1.0
 */
@Data
public class QueryDataParamVo {

    private String name;
    private String lessonId;
    private String branchCode;
    private List<OperationStatusEnum> operationType;

}
