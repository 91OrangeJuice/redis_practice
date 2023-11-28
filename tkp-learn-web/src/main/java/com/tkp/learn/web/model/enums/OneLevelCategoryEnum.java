package com.tkp.learn.web.model.enums;

import lombok.Getter;

/**
 * author: itw_lixg01
 * date: 2020-06-10 19:31
 * description:
 **/
@Getter
public enum OneLevelCategoryEnum {

    BUSINESS("business", "业务类别"),
    INDUSTRY_ATTRIBUTE("industryAttribute", "行业属性"),
    PROJECT_FLOW("projectFlow", "项目流程");

    private String code;
    private String name;

    OneLevelCategoryEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
