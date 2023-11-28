package com.tkp.learn.web.service.serviceImpl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tkp.learn.web.constant.Constant;
import com.tkp.learn.web.dao.LearnVideoYearTopListMapper;
import com.tkp.learn.web.model.bo.LearnVideoTopListBo;
import com.tkp.learn.web.model.enums.DurationUintEnum;
import com.tkp.learn.web.model.enums.OrderEnum;
import com.tkp.learn.web.model.po.LearnVideoYearTopListPo;
import com.tkp.learn.web.model.vo.PageParamVo;
import com.tkp.learn.web.model.vo.RankingListPage;
import com.tkp.learn.web.model.vo.RankingListVo;
import com.tkp.learn.web.model.vo.RankingVo;
import com.tkp.learn.web.model.vo.common.PageVo;
import com.tkp.learn.web.service.LearnVideoYearTopListService;
import com.tkp.learn.web.utils.CopyUtils;
import com.tkp.learn.web.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/6/3
 * @version: 1.0
 */
@Service
public class LearnVideoYearTopListServiceImpl implements LearnVideoYearTopListService {

    @Autowired
    private LearnVideoYearTopListMapper learnVideoYearTopListMapper;

    @Override
    public LearnVideoTopListBo getCurrentYearRankByUserId(@NotNull String userId) {
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        LearnVideoYearTopListPo po = learnVideoYearTopListMapper.getUserYearRankByUserYearMonth(userId, year, DurationUintEnum.MINUTES.getCode());
        LearnVideoTopListBo bo = new LearnVideoTopListBo();
        if (null != po) {
            CopyUtils.copyBean(po, bo);
        }
        return bo;
    }

    @Override
    public LearnVideoTopListBo getYearRankByUserIdDate(String userId, int year) {
        LearnVideoYearTopListPo po = learnVideoYearTopListMapper.getUserYearRankByUserYearMonth(userId, year, DurationUintEnum.MINUTES.getCode());
        LearnVideoTopListBo bo = new LearnVideoTopListBo();
        if (null != po) {
            CopyUtils.copyBean(po, bo);
        }
        return bo;
    }

    @Override
    public List<LearnVideoTopListBo> getCurrentYearTopList() {
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        List<LearnVideoYearTopListPo> pos = learnVideoYearTopListMapper.getYearTopListByYearMonth(year, 0, 200, OrderEnum.ASC.getCode(), DurationUintEnum.MINUTES.getCode());
        List<LearnVideoTopListBo> bos = new ArrayList<>();
        CopyUtils.copyBeanForList(pos, bos, LearnVideoTopListBo.class);
        return bos;
    }

    @Override
    public RankingListVo getCurrentYearTopPage(PageParamVo<RankingListPage> rankingListPage) {
        RankingListVo rankingListVo = new RankingListVo();
        int year = rankingListPage.getCondition().getYear();
        if (year == 0) {
            Calendar now = Calendar.getInstance();
            year = now.get(Calendar.YEAR);
        }
        Page<LearnVideoYearTopListPo> poPage = learnVideoYearTopListMapper.getYearTopPageByYearMonth(PageUtils.getPage(rankingListPage), year, OrderEnum.ASC.getCode(), DurationUintEnum.MINUTES.getCode());
        if (null != poPage && !CollectionUtils.isEmpty(poPage.getRecords())) {
            List<RankingVo> rankingVos = new ArrayList<>();
            learnVideoYearTopListPoTORankingVo(poPage.getRecords(), rankingVos);
            PageVo<RankingVo> rankingVoPage = PageUtils.buildPageVo(poPage, rankingVos);
            rankingListVo.setRankingList(rankingVoPage);
        }
        return rankingListVo;
    }

    private void learnVideoYearTopListPoTORankingVo(List<LearnVideoYearTopListPo> pos, List<RankingVo> rankingVos) {
        for (LearnVideoYearTopListPo po : pos) {
            RankingVo vo = new RankingVo();
            vo.setUserName(po.getName());
            vo.setOrgShortName(po.getShortName());
            String iconPath = StringUtils.isEmpty(po.getHeadImgUrl()) ? Constant.DEFAULT_HEAD_IMG_URL : po.getHeadImgUrl();
            vo.setIconPath(iconPath);
            vo.setRank(po.getRanking());
            vo.setStudyDuration(po.getDuration());
            rankingVos.add(vo);
        }
    }

}
