package com.tkp.learn.admin.actuator.aspect;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.tkp.learn.admin.actuator.exception.SystemException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/6/1
 * @version: 1.0
 */
@Aspect
@Component
public class ParamsCheckAspect {

    private static Validator validator;
    static {
        validator = Validation.byDefaultProvider().configure()
                .messageInterpolator(new ResourceBundleMessageInterpolator(
                        new PlatformResourceBundleLocator("validationMessages")))
                .buildValidatorFactory().getValidator();
    }

    @Pointcut("@annotation(com.tkp.learn.admin.actuator.aspect.ParamsValidated))")
    private void validateMethod() {
    }

    @Before("validateMethod()")
    public void before(JoinPoint joinPoint) throws SystemException {
        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Set<ConstraintViolation<Object>> constraintViolations = validator.forExecutables().validateParameters(joinPoint.getThis(), signature.getMethod(), args);
        List<String> messages = Lists.newArrayList();
        for (ConstraintViolation<Object> error : constraintViolations) {
            messages.add(error.getPropertyPath()+error.getMessage());
        }
        if(!messages.isEmpty()){
            throw new SystemException(JSONObject.toJSONString(messages));
        }
    }

}
