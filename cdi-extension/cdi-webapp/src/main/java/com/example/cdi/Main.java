package com.example.cdi;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

@Singleton
@Startup
public class Main {
 
    @Inject
    @Any
    private Instance<Service> serviceInstance;

    @PostConstruct
    public void init() {
        serviceInstance.forEach(bean -> bean.say());
    }
}