package com.tkp.learn.archive.task.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class BeanFactory implements ApplicationContextAware {
    private static final Logger LOGGER = LoggerFactory.getLogger(BeanFactory.class);

    private ApplicationContext applicationContext;


    /**
     * 根据Service名称获取具体的业务处理逻辑类
     *
     * @param name
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T getServiceImpl(String name) {
        T bean;
        try {
            bean = (T) applicationContext.getBean(name);
        } catch (NoSuchBeanDefinitionException e) {
            LOGGER.error("不存在的bean实例!", e);
            throw new RuntimeException("不存在的bean实例!",e);
        }
        return bean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
