package com.tkp.learn.pushcdn.task.actuator.aspect;


import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DisableLog {

    boolean doBefore() default false;

    boolean doAfterReturning() default true;

}
