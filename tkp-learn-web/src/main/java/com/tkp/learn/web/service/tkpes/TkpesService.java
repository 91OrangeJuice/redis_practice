package com.tkp.learn.web.service.tkpes;


import com.tkp.learn.web.actuator.exception.RemoteException;
import com.tkp.learn.web.actuator.model.ErrorCode;
import com.tkp.learn.web.remote.api.TKPESWebServiceApi;
import com.tkp.learn.web.remote.dto.tkpes.SalesmanDto;
import com.tkp.learn.web.remote.dto.tkpes.TKPESBaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TkpesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TkpesService.class);
    @Autowired
    private TKPESWebServiceApi tkpesWebServiceApi;

    public SalesmanDto verifyTempToken(final String tempToken) {
        final TKPESBaseResponse<SalesmanDto> response;
        try {
            response = tkpesWebServiceApi.verifyTempToken(tempToken);
        } catch (Exception e) {
            LOGGER.error("调用tkpes验证tempToken接口异常！", e);
            throw new RemoteException("登录异常，请稍后再试!", ErrorCode.REMOTE_EXCEPTION);
        }
        if (!response.isSuccess()) {
            LOGGER.error("调用tkpes验证tempToken接口错误，错误原因：[{}]", response.getErrorMessage());
            throw new RemoteException(response.getErrorMessage(), ErrorCode.REMOTE_DATA_ERROR);
        }
        return response.getContent();
    }

}
