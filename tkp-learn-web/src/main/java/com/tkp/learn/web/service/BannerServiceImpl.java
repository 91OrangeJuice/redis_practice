package com.tkp.learn.web.service;

import com.tkp.learn.web.dao.LearnSlideShowMapper;
import com.tkp.learn.web.model.po.LearnSlideShowPo;
import com.tkp.learn.web.model.vo.BannerVo;
import com.tkp.learn.web.service.access.IAccessService;
import com.tkp.learn.web.service.factory.StrategyContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author: itw_lixg01
 * date: 2020-06-11 17:22
 * description:
 **/

@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private LearnSlideShowMapper learnSlideShowMapper;
    @Autowired
    private StrategyContext<IAccessService> accessContext;

    @Override
    public List<BannerVo> getBannerList() {
        return learnSlideShowMapper.getBannerList();
    }

    public String getTargetUrl(final String code) {
        final LearnSlideShowPo learnSlideShowPo = learnSlideShowMapper.selectById(code);
        final String targetType = learnSlideShowPo.getTargetType();
        final IAccessService accessService = accessContext.getStrategy(targetType);
        final String targetUrl = learnSlideShowPo.getTargetUrl();
        final String lessonId = learnSlideShowPo.getLessonId();
        return accessService.getAccessUrl(targetUrl, lessonId);
    }

}
