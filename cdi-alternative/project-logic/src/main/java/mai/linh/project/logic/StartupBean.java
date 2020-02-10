package mai.linh.project.logic;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import mai.linh.project.api.Handler;

@Singleton
@Startup
public class StartupBean 
{
    @Inject
    private Handler handler;

    @PostConstruct
    public void init() {
        System.out.println("Hello ...");
        handler.say();
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("Goodbye ...");
    }
}

