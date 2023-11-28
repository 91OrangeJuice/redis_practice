package com.tkp.learn.web.service.serviceImpl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tkp.learn.web.actuator.aspect.ParamsValidated;
import com.tkp.learn.web.constant.Constant;
import com.tkp.learn.web.dao.LearnVideoMonthTopListMapper;
import com.tkp.learn.web.model.bo.LearnVideoTopListBo;
import com.tkp.learn.web.model.enums.DurationUintEnum;
import com.tkp.learn.web.model.enums.OrderEnum;
import com.tkp.learn.web.model.po.LearnVideoMonthTopListPo;
import com.tkp.learn.web.model.vo.PageParamVo;
import com.tkp.learn.web.model.vo.RankingListPage;
import com.tkp.learn.web.model.vo.RankingListVo;
import com.tkp.learn.web.model.vo.RankingVo;
import com.tkp.learn.web.model.vo.common.PageVo;
import com.tkp.learn.web.service.LearnVideoMonthTopListService;
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
public class LearnVideoMonthTopListServiceImpl implements LearnVideoMonthTopListService {
    @Autowired
    private LearnVideoMonthTopListMapper learnVideoMonthTopListMapper;

    @Override
    @ParamsValidated
    public LearnVideoTopListBo getCurrentMonthRankByUserId(@NotNull String userId) {
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;
        LearnVideoMonthTopListPo po = learnVideoMonthTopListMapper.getUserMonthRankByUserYearMonth(userId, year, month, DurationUintEnum.MINUTES.getCode());
        LearnVideoTopListBo bo = new LearnVideoTopListBo();
        if (null != po) {
            CopyUtils.copyBean(po, bo);
        }
        return bo;
    }

    @Override
    public List<LearnVideoTopListBo> getCurrentMonthTopList() {
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;
        List<LearnVideoMonthTopListPo> pos = learnVideoMonthTopListMapper.getMonthTopListByYearMonth(year, month, 0, 200, OrderEnum.ASC.getCode(), DurationUintEnum.MINUTES.getCode());
        List<LearnVideoTopListBo> bos = new ArrayList<>();
        CopyUtils.copyBeanForList(pos, bos, LearnVideoTopListBo.class);
        return bos;
    }

    @Override
    public RankingListVo getCurrentMonthTopPage(PageParamVo<RankingListPage> rankingListPage) {
        RankingListVo rankingListVo = new RankingListVo();
        int year = rankingListPage.getCondition().getYear();
        int month = rankingListPage.getCondition().getMonth();
        if (year == 0 || month == 0) {
            Calendar now = Calendar.getInstance();
            year = now.get(Calendar.YEAR);
            month = now.get(Calendar.MONTH) + 1;
        }
        Page<LearnVideoMonthTopListPo> poPage = learnVideoMonthTopListMapper.getMonthTopPageByYearMonth(PageUtils.getPage(rankingListPage), year, month, OrderEnum.ASC.getCode(), DurationUintEnum.MINUTES.getCode());
        if (null != poPage && !CollectionUtils.isEmpty(poPage.getRecords())) {
            List<RankingVo> rankingVos = new ArrayList<>();
            learnVideoMonthTopListPoTORankingVo(poPage.getRecords(), rankingVos);
            PageVo<RankingVo> rankingVoPage = PageUtils.buildPageVo(poPage, rankingVos);
            rankingListVo.setRankingList(rankingVoPage);
        }
        return rankingListVo;
    }

    @Override
    public LearnVideoTopListBo getMonthRankByUserIdDate(String userId, int year, int month) {
        LearnVideoMonthTopListPo po = learnVideoMonthTopListMapper.getUserMonthRankByUserYearMonth(userId, year, month, DurationUintEnum.MINUTES.getCode());
        LearnVideoTopListBo bo = new LearnVideoTopListBo();
        if (null != po) {
            CopyUtils.copyBean(po, bo);
        }
        return bo;
    }

    private void learnVideoMonthTopListPoTORankingVo(List<LearnVideoMonthTopListPo> pos, List<RankingVo> rankingVos) {
        for (LearnVideoMonthTopListPo po : pos) {
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
