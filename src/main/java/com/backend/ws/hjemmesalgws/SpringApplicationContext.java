package com.backend.ws.hjemmesalgws;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringApplicationContext implements ApplicationContextAware {
    private static ApplicationContext CONTEXT;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CONTEXT = applicationContext;
    }

    /**
     * Metoden anvendes til at returnere en bean der allerede kreeret af Spring frameworket.
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName){
        return CONTEXT.getBean(beanName);
    }
}
