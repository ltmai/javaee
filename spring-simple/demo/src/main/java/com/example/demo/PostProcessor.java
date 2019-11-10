package com.example.demo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * PostProcessor
 * The following must be added to beans.xml to register this bean to container:
 * <bean class="com.example.demo.PostProcessor"/>
 */
public class PostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("[BeanPostProcessor] Before initializing " + beanName);
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
    
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("[BeanPostProcessor] After initializing " + beanName);
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }
    
}