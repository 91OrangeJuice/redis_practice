package com.tkp.learn.web.service.serviceImpl;

import com.tkp.learn.web.actuator.aspect.ParamsValidated;
import com.tkp.learn.web.dao.LearnDataPraisesMapper;
import com.tkp.learn.web.model.enums.PraisesStatusEnum;
import com.tkp.learn.web.service.LearnDataPraisesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/6/3
 * @version: 1.0
 */
@Service
public class LearnDataPraisesServiceImpl implements LearnDataPraisesService {

    @Autowired
    private LearnDataPraisesMapper learnDataPraisesMapper;

    @Override
    @ParamsValidated
    public int getCurrentUserNewPraiseCountByUserId(@NotNull String userId) {
        return learnDataPraisesMapper.getCurrentUserNewPraiseCountByUserId(userId, PraisesStatusEnum.UNREAD.getCode());
    }
}
