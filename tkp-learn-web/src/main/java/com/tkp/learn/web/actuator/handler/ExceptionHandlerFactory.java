package com.tkp.learn.web.actuator.handler;


import com.tkp.learn.web.service.factory.StrategyContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ExceptionHandlerFactory {

    @Autowired
    private StrategyContext<ExceptionHandler> throwableHandlerContext;

    private ExceptionHandlerFactory() {
    }

    public ExceptionHandler getHandler(final String exceptionHandlerName) {
        return throwableHandlerContext.getStrategy(exceptionHandlerName);
    }
}