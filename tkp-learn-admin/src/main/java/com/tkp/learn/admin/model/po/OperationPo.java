package com.tkp.learn.admin.model.po;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/8/3
 * @version: 1.0
 */
@Data
public class OperationPo {

    private String id;
    private String detailId ;
    private String operationType;
    private String operationContent ;
    private String operationUser;
    private LocalDateTime operationTime;
    private String errorMessage ;

}
