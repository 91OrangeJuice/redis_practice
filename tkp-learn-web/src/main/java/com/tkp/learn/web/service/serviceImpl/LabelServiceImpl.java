package com.tkp.learn.web.service.serviceImpl;

import com.tkp.learn.web.dao.DictLabelMapper;
import com.tkp.learn.web.model.vo.LabelVo;
import com.tkp.learn.web.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author: itw_lixg01
 * date: 2020-06-12 11:07
 * description:
 **/

@Service
public class LabelServiceImpl implements LabelService {

    @Autowired
    private DictLabelMapper dictLabelMapper;

    @Override
    public List<LabelVo> getLabels() {
        return dictLabelMapper.getLabels();
    }
}
