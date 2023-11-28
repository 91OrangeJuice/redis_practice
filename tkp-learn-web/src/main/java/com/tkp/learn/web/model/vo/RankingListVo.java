package com.tkp.learn.web.model.vo;

import com.tkp.learn.web.model.vo.common.PageVo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @description: 排序列表实体
 * @author: itw_wangsc01
 * @createDate: 2020/5/27
 * @version: 1.0
 */
@Getter
@Setter
@ToString
public class RankingListVo {

    private String userName;
    private String orgShortName;
    private int myRank;
    private int studyDuration;
    private PageVo<RankingVo> rankingList;
    /**
     * 头像图标
     */
    private String iconPath;

}
