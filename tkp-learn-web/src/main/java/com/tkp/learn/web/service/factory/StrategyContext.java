package com.tkp.learn.web.service.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by itw_wangshuai01 on 2019/2/25.
 */
@Service
public class StrategyContext<T> {
    @Autowired
    private StrategyFactory<T> strategyFactory;

    public T getStrategy(String strategyName){
        return strategyFactory.createService(strategyName);
    }
}
