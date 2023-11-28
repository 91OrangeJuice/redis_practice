package com.tkp.learn.web.remote.api;

import com.tkp.learn.web.remote.dto.tkpes.SalesmanDto;
import com.tkp.learn.web.remote.dto.tkpes.TKPESBaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description:
 * @ClassName: TKPESWebServiceApi
 * @author: itw_wangshuai01
 * @date: 2019/7/16
 */

@FeignClient(name = "TKPES", url = "${config.tkpes.host}")
public interface TKPESWebServiceApi {

    @PostMapping(value = "/access/verifyTempTokenV2.serv")
    TKPESBaseResponse<SalesmanDto> verifyTempToken(@RequestParam("tempToken") String tempToken);
}
