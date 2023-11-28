package com.tkp.learn.web.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * author: itw_lixg01
 * date: 2020-06-05 15:09
 * description:
 **/
@Setter
@Getter
@ToString
public class CategoryTagVo {

    private String categoryId;
    private String categoryName;
    private int multiSelect;
    private List<TagVo> data;
}
