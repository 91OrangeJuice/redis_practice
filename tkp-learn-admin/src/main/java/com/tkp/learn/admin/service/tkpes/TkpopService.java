package com.tkp.learn.admin.service.tkpes;


import com.tkp.learn.admin.actuator.exception.RemoteException;
import com.tkp.learn.admin.actuator.exception.ServiceException;
import com.tkp.learn.admin.actuator.model.ErrorCode;
import com.tkp.learn.admin.remote.api.TKPOPWebServiceApi;
import com.tkp.learn.admin.remote.dto.tkpop.TkopUserDto;
import com.tkp.learn.admin.remote.dto.tkpop.TkopViewBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TkpopService {

    @Autowired
    private TKPOPWebServiceApi tkpOPWebServiceApi;

    @Value("${config.tkop.secretCode}")
    private String secretCode;

    public TkopUserDto verifyTempToken(final String tempToken) {
        final TkopViewBean<TkopUserDto> viewBean;
        TkopUserDto tkopUserDto = null;
        try {
            viewBean = tkpOPWebServiceApi.verifyUser(secretCode, tempToken);
            if (viewBean.getCode() != 0) {
                LOGGER.error("调用泰运营获取用户信息接口失败【{}】", viewBean.getMessage());
                throw new ServiceException("获取登录用户信息失败", ErrorCode.NO_LOGIN);
            }
            tkopUserDto = viewBean.getData();
            LOGGER.info("泰运营系统返回的登录用户信息:{}", tkopUserDto);
            LOGGER.info("用户登录成功 tkopUser:【{}】", tkopUserDto);
        } catch (Exception e) {
            LOGGER.error("调用泰运营获取用户信息接口异常【{}】", e);
            throw new RemoteException("登录异常，请稍后再试!", ErrorCode.REMOTE_EXCEPTION);
        }

        return tkopUserDto;
    }

    public TkopUserDto getUser(final String userId) {
        final TkopViewBean<TkopUserDto> viewBean;
        TkopUserDto tkopUserDto = null;
        try {
            viewBean = tkpOPWebServiceApi.getUser(secretCode, userId);
            if (viewBean.getCode() != 0) {
                LOGGER.error("调用泰运营获取用户信息接口失败【{}】", viewBean.getMessage());
                throw new ServiceException("获取登录用户信息失败", ErrorCode.NO_LOGIN);
            }
            tkopUserDto = viewBean.getData();
        } catch (Exception e) {
            LOGGER.error("调用泰运营获取用户信息接口异常【{}】", e);
            throw new RemoteException("登录异常，请稍后再试!", ErrorCode.REMOTE_EXCEPTION);
        }

        return tkopUserDto;
    }

}
