package com.tkp.learn.pushcdn.task.actuator.handler;


import com.tkp.learn.pushcdn.task.actuator.exception.SystemException;

public interface ExceptionHandler {

    Object dutyHandler(SystemException e);
}
