package com.tkp.learn.web.model.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * author: itw_lixg01
 * date: 2020-06-04 13:59
 * description:
 **/
@Getter
@Setter
@ToString
public class DictBranchBo {
    private String saleNo;
    private String orgCode;
    private String orgName;
    private String orgShortName;
    private int level;
}
