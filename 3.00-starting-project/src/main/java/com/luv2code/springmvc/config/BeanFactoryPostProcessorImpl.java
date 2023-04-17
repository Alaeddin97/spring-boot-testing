package com.luv2code.springmvc.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class BeanFactoryPostProcessorImpl implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
  /*      BeanDefinition beanDefinition = beanFactory.getBeanDefinition("com.luv2code.springmvc.models.CollegeStudent");
        System.out.println(beanDefinition);*/
    }
}
