package com.tkp.learn.admin.service.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @description: 策略规则为你的service类名+你的身份标识
 * @author: itw_wangsc01
 * @createDate: 2020/7/28
 * @version: 1.0
 */
@Service
public class StrategyContext4Business<T> {
    @Autowired
    private StrategyFactory<T> strategyFactory;

    public T getStrategy(String strategyName,Class classz){
        String name=classz.getSimpleName()+strategyName;
        return strategyFactory.createService(name);
    }

}
