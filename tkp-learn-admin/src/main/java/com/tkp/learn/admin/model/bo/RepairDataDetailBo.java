package com.tkp.learn.admin.model.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author itw_liupeng01
 * @since 2020-07-31
 */
@Data
public class RepairDataDetailBo implements Serializable {


    private String id;

    private String branchCode;

    private String branchName;

    private String userName;

    private String userNo;

    private String userType;

    private String userTypeName;

    private String whitePhone;

    private String lessonId;

    private String lessonName;

    private String learningStyle;

    private String learningStyleName;

    private String questionDescript;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime learnedTime;

    private String lastCommitUser;

    private String lastCommitUserName;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime lastCommitTime;

    private String approver;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime approveTime;

    private String currentStatus;

    private String repairName;

    private String operationContent;



}
