package com.tkp.learn.admin.actuator.handler;


import com.tkp.learn.admin.actuator.exception.SystemException;

public interface ExceptionHandler {

    Object dutyHandler(SystemException e);
}
