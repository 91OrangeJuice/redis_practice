package com.tkp.learn.admin.model.vo;

import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/7/24
 * @version: 1.0
 */
@Data
public class HandleDataResultVo {

    private List<RepaireDataVo> successRepaireDataVos;

    private List<RepaireDataVo> errorRepaireDataVos;

    private int successCount;
    private int errorCount;
    private int total;

    private String message;

    public List<RepaireDataVo> queryAll(){
        List<RepaireDataVo> all=new ArrayList<>();
        if(!CollectionUtils.isEmpty(this.successRepaireDataVos)){
            all.addAll(this.successRepaireDataVos);
        }
        if(!CollectionUtils.isEmpty(this.errorRepaireDataVos)){
            all.addAll(this.errorRepaireDataVos);
        }
        return all;
    }

    public int querySuccessSize(){
        if(!CollectionUtils.isEmpty(this.successRepaireDataVos)){
            return this.successRepaireDataVos.size();
        }
        return 0;
    }

    public int queryErrorSize(){
        if(!CollectionUtils.isEmpty(this.errorRepaireDataVos)){
            return this.errorRepaireDataVos.size();
        }
        return 0;
    }

}
