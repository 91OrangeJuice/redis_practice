package com.tkp.learn.web.actuator.handler;

import com.tkp.learn.web.actuator.exception.SystemException;

public interface ExceptionHandler {

    Object dutyHandler(SystemException e);
}
