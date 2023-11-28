package com.tkp.learn.admin.service;

import com.tkp.learn.admin.model.common.LoginUser;
import com.tkp.learn.admin.model.enums.RoleEnum;
import com.tkp.learn.admin.remote.dto.tkpop.SsoRoleBo;
import com.tkp.learn.admin.remote.dto.tkpop.TkopUserDto;
import com.tkp.learn.admin.security.JwtTokenProvider;
import com.tkp.learn.admin.service.tkpes.TkpopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Description:
 * @ClassName: LoginService
 * @author: itw_wangshuai01
 * @date: 2019/7/17
 */

@Service
@Slf4j
public class LoginService {

    @Autowired
    private TkpopService tkpopService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public LoginUser login(final String token) {
        final TkopUserDto tkopUserDto = tkpopService.verifyTempToken(token);
        LoginUser user = new LoginUser();
        buildLoginUser(user, tkopUserDto);
        return user;
    }

    private void buildLoginUser(LoginUser user, TkopUserDto tkopUserDto) {
        String jwt = generateJwt(tkopUserDto);
        user.setWorkNo(tkopUserDto.getLoginId());
        user.setName(tkopUserDto.getName());
        user.setOrgCode(tkopUserDto.getOrg());
        user.setOrgName(tkopUserDto.getOrgName());
        List<SsoRoleBo> ssoRoles = tkopUserDto.getAuthorites();
        if(!CollectionUtils.isEmpty(ssoRoles)){
            for(SsoRoleBo bo:ssoRoles){
                if(RoleEnum.SUBMITTER.getCode().equals(bo.getRoleId())){
                    user.setRoleId(bo.getRoleId());
                    user.setRoleName(bo.getRoleName());
                }
                if(RoleEnum.APPROVER.getCode().equals(bo.getRoleId())){
                    user.setRoleId(bo.getRoleId());
                    user.setRoleName(bo.getRoleName());
                    break;
                }
            }
        }
        user.setAuthorization(jwt);
    }

    private String generateJwt(TkopUserDto tkopUserDto) {
        LOGGER.info("当前登录用户：【】", tkopUserDto);
        return jwtTokenProvider.generateJWT(tkopUserDto.getLoginId());
    }
}
