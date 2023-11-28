package com.tkp.learn.web.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * author: itw_lixg01
 * date: 2020-06-10 20:25
 * description:
 **/

@Setter
@Getter
@ToString
public class LearnHistoryCondition {
    private String workNo;
    private List<String> learnStatus;
    private List<String> labelCodes;
}
