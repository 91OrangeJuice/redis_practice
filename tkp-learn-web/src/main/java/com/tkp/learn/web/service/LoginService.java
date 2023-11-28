package com.tkp.learn.web.service;

import com.tkp.learn.web.model.enums.IdentityEnum;
import com.tkp.learn.web.remote.dto.tkpes.EmployeeDto;
import com.tkp.learn.web.remote.dto.tkpes.SalesmanDto;
import com.tkp.learn.web.remote.dto.tkpes.TKPESIdentityEnum;
import com.tkp.learn.web.security.JwtTokenProvider;
import com.tkp.learn.web.service.tkpes.TkpesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @ClassName: LoginService
 * @author: itw_wangshuai01
 * @date: 2019/7/17
 */

@Service
public class LoginService {

    @Autowired
    private TkpesService tkpesService;
    @Autowired
    private UserHeadImgService userHeadImgService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public String login(final String token) {
        final SalesmanDto salesmanDto = tkpesService.verifyTempToken(token);
        return generateJwt(salesmanDto);
    }

    private String generateJwt(SalesmanDto salesmanDto) {
        if (TKPESIdentityEnum.OA.equals(salesmanDto.getUserIdentity())) {
            final EmployeeDto employeeDto = salesmanDto.getEmployeeDto();
            final String accountNo = employeeDto.getAccountNo();
            userHeadImgService.saveUserHeadImg(accountNo, salesmanDto.getHeadImgUrl());
            return jwtTokenProvider.generateJWT(accountNo, IdentityEnum.EMPLOYEE.getCode());
        }
        final String saleNo = salesmanDto.getSaleNo();
        userHeadImgService.saveUserHeadImg(saleNo, salesmanDto.getHeadImgUrl());
        return jwtTokenProvider.generateJWT(saleNo, IdentityEnum.SALESMAN.getCode());
    }
}
