package com.tkp.learn.web.actuator.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

import static java.lang.System.currentTimeMillis;


@Aspect
@Component
public class LogHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogHandler.class);

    @Pointcut("execution(public * com.tkp.learn.web.controller..*.*(..))")
    public void webLog() {
        // Do nothing because this is point cut.
    }

    @Pointcut("@annotation(com.tkp.learn.web.actuator.aspect.DisableLog)")
    private void disableWebLog() {
        // Do nothing because this is point cut.
    }


    @Around("webLog() || disableWebLog()")
    public Object handler(ProceedingJoinPoint joinPoint) throws Throwable {
        Object object;
        doBefore(joinPoint);
        final Long startTime = currentTimeMillis();
        object = joinPoint.proceed();
        final Long duration = System.currentTimeMillis() - startTime;
        doAfterReturning(joinPoint, object, duration);
        return object;
    }

    private void doBefore(ProceedingJoinPoint joinPoint) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (ObjectUtils.isEmpty(attributes)) {
            LOGGER.error("请求异常！无法获取到HttpServletRequest [{}]", attributes);
            return;
        }
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        if (beforeConsole(joinPoint)) {
            LOGGER.debug("请求地址 :{} ,参数 :{}", request.getRequestURL(), joinPoint.getArgs());
        }
    }

    private void doAfterReturning(ProceedingJoinPoint joinPoint, Object object, Long duration) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (ObjectUtils.isEmpty(attributes)) {
            LOGGER.error("请求异常！无法获取到HttpServletRequest [{}]", attributes);
            return;
        }
        HttpServletRequest request = attributes.getRequest();
        // 处理完请求，返回内容
        if (afterReturningConsole(joinPoint)) {
            LOGGER.debug("请求地址 :{} ,参数 :{} RESPONSE : {},exit [{}] use time:[{}ms]", request.getRequestURL(), joinPoint.getArgs(), object, joinPoint.getSignature(), duration);
        }
    }

    private boolean afterReturningConsole(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method targetMethod = methodSignature.getMethod();
        DisableLog disableLog = targetMethod.getAnnotation(DisableLog.class);
        return disableLog == null || !disableLog.doAfterReturning();
    }

    private boolean beforeConsole(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method targetMethod = methodSignature.getMethod();
        DisableLog disableLog = targetMethod.getAnnotation(DisableLog.class);
        return disableLog != null && disableLog.doBefore();
    }
}
