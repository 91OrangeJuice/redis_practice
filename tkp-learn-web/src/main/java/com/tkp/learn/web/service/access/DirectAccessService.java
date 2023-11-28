package com.tkp.learn.web.service.access;

import com.tkp.learn.web.utils.UrlReplaceUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by itw_wangshuai01 on 2019/3/20.
 */
@Service("DIRECT")
public class DirectAccessService implements IAccessService {

    @Override
    public String getAccessUrl(final String url, final String lessonId) {
        final Map<String, Object> datas = new HashMap<>();
        return UrlReplaceUtil.replaceVariables(url, datas);
    }
}
