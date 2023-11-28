package com.tkp.learn.pushcdn.task.service;

import com.tkp.learn.pushcdn.task.actuator.exception.RemoteException;
import com.tkp.learn.pushcdn.task.actuator.model.ErrorCode;
import com.tkp.learn.pushcdn.task.config.CDNConfig;
import com.tkp.learn.pushcdn.task.model.enums.FileFormat;
import com.tkp.learn.pushcdn.task.model.enums.FileName;
import com.tkp.learn.pushcdn.task.remote.api.CDNServiceApi;
import com.tkp.learn.pushcdn.task.remote.dto.UploadFileRequest;
import com.tkp.learn.pushcdn.task.remote.dto.UploadFileResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by itw_wangshuai01 on 2020/6/9.
 */

@Service
public class CdnService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CdnService.class);

    @Autowired
    private CDNServiceApi cdnServiceApi;
    @Autowired
    private CDNConfig cdnConfig;

    public Boolean uploadFile(final byte[] data, final FileName fileName, final FileFormat format) {
        final UploadFileResponse response;
        final UploadFileRequest request = createUploadFileRequest(data, fileName, format);
        try {
            response = cdnServiceApi.upload(request);
        } catch (Exception e) {
            LOGGER.error("调用cdn上传文件接口异常！", e);
            throw new RemoteException("文件上传异常，请稍后再试!", ErrorCode.REMOTE_EXCEPTION);
        }
        if (!response.getSuccess()) {
            LOGGER.error("调用cdn上传文件接口错误，错误原因：[{}]", response.getErrorMsg());
            throw new RemoteException(response.getErrorMsg(), ErrorCode.REMOTE_DATA_ERROR);
        }
        return response.getData();
    }

    private UploadFileRequest createUploadFileRequest(final byte[] data, final FileName fileName, final FileFormat format) {
        final UploadFileRequest request = new UploadFileRequest();
        final String path = genFilePath();
        LOGGER.info("上传文件的名称：【{}】", fileName);
        request.setFilepath(path);
        request.setBytes(data);
        request.setFilename(fileName.getCode());
        request.setFileproperty(format.getCode());
        request.setSource(cdnConfig.getSource());
        request.setIscover(cdnConfig.isCover());
        return request;
    }

    private String genFilePath() {
        return cdnConfig.getRelativePath();
    }

}
