package com.tkp.learn.admin.model.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/7/30
 * @version: 1.0
 */
@Data
public class RepaireDataPo {
    private String id ;
    private String userNo;
    private String userName;
    private String branchCode;
    private String userType;
    private String whitePhone;
    private String lessonId;
    private String lessonName;
    private String learningStyle;
    private String questionDescript;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date learnedTime ;
    private String lastCommitUser ;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastCommitTime ;
    private String approver ;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime approveTime ;
    private String currentStatus ;
    private String createUser;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    private String updateUser;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    private String approverContext;

    private String errorMsg;
    private String behaviorId;
}
