package mai.linh.project.server;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.inject.Inject;
import javax.transaction.TransactionSynchronizationRegistry;

import org.apache.logging.log4j.Logger;

import mai.linh.project.server.producer.Log4J;
import mai.linh.project.server.service.CustomerService;

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

    @Inject
    private CustomerService customerService;

    @Inject @Log4J
    private Logger logger;

    @Resource
    TransactionSynchronizationRegistry txReg;
    
    private ScheduledFuture<?> futureDbCleaner;

    @PostConstruct
    public void init() 
    {
        logger.info(()->"Project-Server starting ...");
        
        futureDbCleaner = scheduledExecutorService.scheduleWithFixedDelay(this::runDbCleaner, 
                                                                          INITIAL_DELAY, 
                                                                          FIXED_DELAY, 
                                                                          TimeUnit.SECONDS);
    }

    @PreDestroy
    public void cleanup() 
    {
        futureDbCleaner.cancel(true);
        logger.info(()->"Goodbye!");
    }

    /**
     * Scheduled task executed by executor service
     */
    private void runDbCleaner() 
    {
        try 
        {
            /**
             * Check if we have active transaction here: expect no transaction (because StartupBean is a CDI bean)
             * interface javax.transaction.Status:
             * public static final int STATUS_ACTIVE          = 0;
             * public static final int STATUS_MARKED_ROLLBACK = 1;
             * public static final int STATUS_PREPARED        = 2;
             * public static final int STATUS_COMMITTED       = 3;
             * public static final int STATUS_ROLLEDBACK      = 4;
             * public static final int STATUS_UNKNOWN         = 5;
             * public static final int STATUS_NO_TRANSACTION  = 6; 
             * public static final int STATUS_PREPARING       = 7;
             * public static final int STATUS_COMMITTING      = 8;
             * public static final int STATUS_ROLLING_BACK    = 9;
             */
            logger.info(()->" Scheduled task: Transaction status=" + txReg.getTransactionStatus());

            customerService.getAllCustomers().forEach(logger::info);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}
