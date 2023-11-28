package com.tkp.learn.admin.model.vo;

import lombok.Data;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/7/31
 * @version: 1.0
 */
@Data
public class RepaireDataParamVo {
    @NotNull
    private List<String> ids;
    private String context;
}
