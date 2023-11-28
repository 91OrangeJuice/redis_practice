package com.tkp.learn.web.service.serviceImpl;

import com.tkp.learn.web.dao.UserHeadImgMapper;
import com.tkp.learn.web.model.po.UserHeadImgPo;
import com.tkp.learn.web.service.UserHeadImgService;
import com.tkp.learn.web.utils.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

/**
 * author: itw_lixg01
 * date: 2020-06-04 10:21
 * description: 用户头像处理
 **/
@Service
@Slf4j
public class UserHeadImgServiceImpl implements UserHeadImgService {

    @Autowired
    private UserHeadImgMapper userHeadImgMapper;

    @Override
    @Transactional
    @Async
    public int saveUserHeadImg(String workNo, String headImgUrl) {
        LOGGER.info("开始保存用户【{}】的头像信息【{}】", workNo, headImgUrl);

        UserHeadImgPo userHeadImgPo = userHeadImgMapper.findByWorkNo(workNo);
        if (ObjectUtils.isEmpty(userHeadImgPo)) {
            return insertUserHeadImg(workNo, headImgUrl);
        }
        return updateUserHeadImg(userHeadImgPo, headImgUrl);
    }

    private int insertUserHeadImg(String workNo, String headImgUrl) {
        UserHeadImgPo po = buildUserHeadImgPo(workNo, headImgUrl);
        return userHeadImgMapper.insert(po);
    }

    private int updateUserHeadImg(UserHeadImgPo userHeadImgPo, String headImgUrl) {
        userHeadImgPo.setHeadImgUrl(headImgUrl);
        userHeadImgPo.setUpdateTime(LocalDateTime.now());
        return userHeadImgMapper.updateById(userHeadImgPo);
    }

    private UserHeadImgPo buildUserHeadImgPo(String workNo, String headImgUrl){
        UserHeadImgPo po = new UserHeadImgPo();
        po.setId(UUIDUtil.generatedUUID());
        po.setWorkNo(workNo);
        po.setHeadImgUrl(headImgUrl);
        po.setCreateTime(LocalDateTime.now());

        return po;
    }
}
