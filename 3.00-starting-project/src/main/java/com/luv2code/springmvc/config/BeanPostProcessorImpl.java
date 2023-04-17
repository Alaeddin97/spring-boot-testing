package com.luv2code.springmvc.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class BeanPostProcessorImpl implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
/*
        Class obj=bean.getClass();
        String packageName =obj.getPackageName();
        if(packageName.equals("com.luv2code.springmvc.models")){
            System.out.println("In Before Initialization method: "+ beanName);
        }*/
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
