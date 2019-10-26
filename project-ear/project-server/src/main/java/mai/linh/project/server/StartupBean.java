package mai.linh.project.server;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.apache.logging.log4j.Logger;

/**
 * Startup EJB
 */
@Singleton
@Startup
public class StartupBean 
{
    private static final int FIXED_DELAY = 30;

    private static final int INITIAL_DELAY = 10;

    @Resource
    private ManagedScheduledExecutorService scheduledExecutorService;

    /**
     * Instance allows the application to dynamically obtain instances of beans
     */
    @Inject
    private Instance<CustomerService> serviceInstance;

    @Inject
    private Logger logger;
    
    private ScheduledFuture<?> futureDbCleaner;

    @PostConstruct
    public void init() 
    {
        logger.info("Project-Server starting ...");
        
        futureDbCleaner = scheduledExecutorService.scheduleWithFixedDelay(this::runDbCleaner, INITIAL_DELAY, FIXED_DELAY, TimeUnit.SECONDS);
    }

    @PreDestroy
    public void cleanup() 
    {
        futureDbCleaner.cancel(true);
        logger.info("Goodbye!");
    }

    /**
     * Scheduled task
     */
    private void runDbCleaner() 
    {
        try 
        {
            CustomerService customerService = serviceInstance.get();

            customerService.getAllCustomers().forEach(logger::info);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}
