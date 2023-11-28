package com.tkp.learn.archive.task.utils;

import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.SystemException;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/6/3
 * @version: 1.0
 */
@Slf4j
public class CopyUtils {

    public static <T,F> void copyBean(T source, F target) {
        if(null!=source){
            BeanUtils.copyProperties((T) source, (F) target);
        }
    }

    public static <T,F> void copyBeanForList(List<T> sources, List<F> targets, Class<F> targetClass){
        if (!CollectionUtils.isEmpty(sources)) {
            for (T source : sources) {
                try {
                    F tg = targetClass.newInstance();
                    BeanUtils.copyProperties((T) source, (F) tg);
                    targets.add(tg);
                } catch (Exception e) {
                    LOGGER.error("bean复制异常：", e);
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
