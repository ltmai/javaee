package mai.linh.project.logic;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import mai.linh.project.api.Service;
import mai.linh.project.api.ServiceSelector;
import mai.linh.project.api.ServiceType;

@Singleton
@Startup
public class StartupBean 
{
    @Inject
    @ServiceType(ServiceType.Type.DEFAULT)
    private Service defaultService;

    @Inject @Any
    Instance<Service> services;

    @PostConstruct
    public void init() {
        System.out.println("Initializing ...");
        defaultService.saySomething();

        Service specialService = services.select(new ServiceSelector(ServiceType.Type.SPECIAL)).get();
        specialService.saySomething();
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("Goodbye ...");
    }
}

