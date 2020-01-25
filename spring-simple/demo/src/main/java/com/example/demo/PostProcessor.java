package com.example.demo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * PostProcessor
 * The following must be added to beans.xml to register this bean to container:
 * <bean class="com.example.demo.PostProcessor"/>
 * 
 * The BeanPostProcessor interface defines callback methods that you can implement 
 * to provide your own instantiation logic, dependency-resolution logic, etc. You 
 * can also implement some custom logic after the Spring container finishes 
 * instantiating, configuring, and initializing a bean by plugging in one or more 
 * BeanPostProcessor implementations.
 * 
 * The BeanPostProcessors operate on bean (or object) instances, which means that 
 * the Spring IoC container instantiates a bean instance and then BeanPostProcessor 
 * interfaces do their work.
 */
public class PostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("[BeanPostProcessor] Before initializing " + beanName);
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
    
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("[BeanPostProcessor] After initializing " + beanName);
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }
    
}