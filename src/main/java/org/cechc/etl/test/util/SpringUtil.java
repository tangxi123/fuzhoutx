package org.cechc.etl.test.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringUtil implements ApplicationContextAware{
    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;

    }

    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    public static void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws BeanDefinitionStoreException {
        AutowireCapableBeanFactory factory = applicationContext.getAutowireCapableBeanFactory();
        BeanDefinitionRegistry registry = (BeanDefinitionRegistry) factory;
        registry.registerBeanDefinition(beanName,beanDefinition);
    }

    public static void removeRegisterBean(String beanName){
        AutowireCapableBeanFactory factory = applicationContext.getAutowireCapableBeanFactory();
        BeanDefinitionRegistry registry = (BeanDefinitionRegistry) factory;
        registry.removeBeanDefinition(beanName);
    }

    public static <T> T getBean(Class<T> clazz){
        if(applicationContext==null){
            System.out.println("applicationContext是空的");
        }else{
            System.out.println("applicationContext不是空的");
        }
        return applicationContext.getBean(clazz);
    }
}
