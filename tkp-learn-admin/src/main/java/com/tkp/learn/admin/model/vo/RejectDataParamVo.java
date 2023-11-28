package com.tkp.learn.admin.model.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/8/4
 * @version: 1.0
 */
@Data
public class RejectDataParamVo {
    @NotNull
    private List<String> ids;
    @NotNull
    private String context;
}
