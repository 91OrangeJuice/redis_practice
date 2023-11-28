package com.tkp.learn.pushcdn.task.actuator.handler;

import com.tkp.learn.pushcdn.task.actuator.exception.SystemException;
import com.tkp.learn.pushcdn.task.actuator.model.ErrorCode;
import com.tkp.learn.pushcdn.task.actuator.model.ViewBean;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service("default_handler")
public class CouponExceptionHandler implements ExceptionHandler {

    @Override
    public Object dutyHandler(SystemException e) {
        final ErrorCode errorCode = e.getErrorCode();
        final String localizedMessage = e.getLocalizedMessage();
        final String errorMsg = StringUtils.isEmpty(localizedMessage) ? errorCode.getName() : localizedMessage;
        return ViewBean.createFail(errorMsg, errorCode);
    }
}
