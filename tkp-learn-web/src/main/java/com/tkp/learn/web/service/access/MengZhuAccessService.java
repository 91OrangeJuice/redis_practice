package com.tkp.learn.web.service.access;

import com.tkp.learn.web.config.mengzhu.MengZhuConfig;
import com.tkp.learn.web.model.conts.Constant;
import com.tkp.learn.web.security.SecurityUser;
import com.tkp.learn.web.utils.Base64Util;
import com.tkp.learn.web.utils.SecurityUtils;
import com.tkp.learn.web.utils.UUIDUtil;
import com.tkp.learn.web.utils.UrlReplaceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by itw_wangshuai01 on 2019/3/20.
 */
@Service(value = "MENG_ZHU")
public class MengZhuAccessService implements IAccessService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MengZhuAccessService.class);

    @Autowired
    private MengZhuConfig mengZhuConfig;

    @Override
    public String getAccessUrl(final String url, final String lessonId) {
        final SecurityUser loginUser = SecurityUtils.getLoginUser();
        final String workNo = loginUser.getWorkNo();

        final Map<String, Object> datas = getVariableDatas(workNo, lessonId);
        //替换变量
        final String targetUrl = UrlReplaceUtil.replaceVariables(url, datas);

        final String signUrl = signTargetUrl(workNo, targetUrl);
        LOGGER.info("生成跳转盟主地址：[{}]", signUrl);
        return signUrl;
    }

    private String signTargetUrl(final String workNo, final String url) {
        final Map<String, String> params = getUrlParams(url);
        //从url中获取用于生成签名的内容
        final String ext = params.get(Constant.EXTEND_FILED_KEY);
        //生成时间戳
        final String timestamp = createTimestamp();
        //按照盟主签名规则生成signValue
        final String signValue = genSignValue(ext, workNo, timestamp);
        final Map<String, Object> datas = new HashMap<>();
        datas.put(Constant.SIGN_KEY, signValue);
        datas.put(Constant.SIGNED_AT_KEY, timestamp);
        return UrlReplaceUtil.replaceVariables(url, datas);
    }

    private Map<String, Object> getVariableDatas(final String workNo, final String lessonId) {
        final Map<String, Object> datas = new HashMap<>();
        final String encryptWorkNo = Base64Util.encryptUrlSafe(workNo);
        datas.put(Constant.APPID, mengZhuConfig.getAppid());
        datas.put(Constant.T_ID, encryptWorkNo);
        datas.put(Constant.SYSTEM_CODE, marks(mengZhuConfig.getSystemCode()));
        datas.put(Constant.BUSINESS_CODE, marks(mengZhuConfig.getBusinessCode()));
        datas.put(Constant.SHARE_ID_KEY, marks(lessonId));
        datas.put(Constant.CLUE_ID_KEY, marks(createClueId()));
        return datas;
    }

    private String genSignValue(final String content, final String workNo, final String timestamp) {
        final Map<String, String> signMap = new TreeMap<>();
        signMap.put(Constant.APPID, mengZhuConfig.getAppid());
        signMap.put(Constant.SIGNED_AT_KEY, timestamp);
        signMap.put(Constant.T_ID, Base64Util.encryptUrlSafe(workNo));
        signMap.put(Constant.EXTEND_FILED_KEY, content);
        final StringBuilder kvString = new StringBuilder();
        final String secret = mengZhuConfig.getSecret();
        kvString.append(secret);
        signMap.forEach((k, v) -> {
            kvString.append(k);
            kvString.append(v);
        });
        kvString.append(secret);
        return DigestUtils.md5DigestAsHex(kvString.toString().getBytes());
    }

    private String marks(final String str) {
        return "\"" + str + "\"";
    }

    private String createClueId() {
        return UUIDUtil.generatedUUID();
    }

    private String createTimestamp() {
        return Long.toString(System.currentTimeMillis());
    }

    private Map<String, String> getUrlParams(String tarUrl) {
        Map<String, String> urlParam = new HashMap<>();
        String[] arrSplit;
        String strUrlParam = truncateUrl(tarUrl);
        if (strUrlParam == null) {
            return urlParam;
        }
        arrSplit = strUrlParam.split("[&]");
        for (String strSpilt : arrSplit) {
            String[] arrSpiltEqual;
            arrSpiltEqual = strSpilt.split("[=]");
            if (arrSpiltEqual.length > 1) {
                String value = arrSpiltEqual[1];
                urlParam.put(arrSpiltEqual[0], value);
            } else if (arrSpiltEqual[0] != "") {// 只有键名、没有键值
                urlParam.put(arrSpiltEqual[0], arrSpiltEqual[1]);
            }
        }
        return urlParam;
    }

    private String truncateUrl(String strUrl) {
        String strAllParam = null;
        String[] arrSpilt = strUrl.split("[?]");
        if (strUrl.length() > 1 && arrSpilt.length > 1) {
            for (int i = 1; i < arrSpilt.length; i++) {
                strAllParam = arrSpilt[i];
            }
        }
        return strAllParam;
    }

}
