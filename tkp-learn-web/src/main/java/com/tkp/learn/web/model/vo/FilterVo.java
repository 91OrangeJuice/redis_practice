package com.tkp.learn.web.model.vo;

import com.tkp.learn.web.model.bo.TreeBo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * author: itw_lixg01
 * date: 2020-06-12 11:09
 * description:
 **/

@Getter
@Setter
@ToString
public class FilterVo {
    private List<TreeBo> categoryTree;
    private List<LabelVo> labels;
}
