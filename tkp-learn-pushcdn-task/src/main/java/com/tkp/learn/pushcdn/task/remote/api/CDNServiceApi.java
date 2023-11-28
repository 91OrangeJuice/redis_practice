package com.tkp.learn.pushcdn.task.remote.api;

import com.tkp.learn.pushcdn.task.remote.dto.UploadFileRequest;
import com.tkp.learn.pushcdn.task.remote.dto.UploadFileResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by itw_wangshuai01 on 2020/6/9.
 */
@FeignClient(name = "CDN", url = "${config.cdn.host}")
public interface CDNServiceApi {

    @PostMapping("/grp/picture/uploadfiles")
    UploadFileResponse upload(@RequestBody UploadFileRequest request);
}
