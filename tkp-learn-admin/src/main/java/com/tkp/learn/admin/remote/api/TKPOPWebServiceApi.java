package com.tkp.learn.admin.remote.api;

import com.tkp.learn.admin.remote.dto.tkpop.TkopUserDto;
import com.tkp.learn.admin.remote.dto.tkpop.TkopViewBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description:
 * @ClassName: TKPOPWebServiceApi
 * @author: itw_wangsc01
 * @date: 2020/7/28
 */
@FeignClient(name = "TKPOP", url = "${config.tkop.ssoUser}")
public interface TKPOPWebServiceApi {

    @GetMapping("/zuul/server/auth/sso/userInfo")
    TkopViewBean<TkopUserDto> verifyUser(@RequestHeader("secretCode") String secretCode,
                                         @RequestParam("tempToken") String tempToken);

    @GetMapping("/zuul/server/auth/sso/userInfo/one")
    TkopViewBean<TkopUserDto> getUser(@RequestHeader("secretCode") String secretCode,
                                      @RequestParam("loginId") String loginId);

}
