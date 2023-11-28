package com.tkp.learn.pushcdn.task.service.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class StrategyFactory<T> {

    @Autowired
    private StrategyRegister strategyRegister;

    public T createService(String name) {
        if(StringUtils.isEmpty(name)){
            return null;
        }
        return strategyRegister.getServiceImpl(name);
    }

}