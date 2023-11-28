package com.tkp.learn.web.actuator.aspect;

import java.lang.annotation.*;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/6/1
 * @version: 1.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ParamsValidated {
    boolean before() default false;
}
