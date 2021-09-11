package com.example.cdi;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.BeforeBeanDiscovery;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessBean;

public class CdiExtension implements Extension {
    
    public void beforeBeanDiscovery(@Observes BeforeBeanDiscovery bbd) { 
        System.out.println("beforeBeanDiscovery");
    }

    public void processBeanService(@Observes ProcessBean<? extends Service> pbe) { 
        System.out.println("Found Service bean " + pbe.getBean().getBeanClass().getName());
    }


}