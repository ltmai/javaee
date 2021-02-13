package mai.linh.project.server;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.resource.ResourceException;

import mai.linh.project.connector.api.TcpConnection;
import mai.linh.project.connector.api.TcpConnectionFactory;
import mai.linh.project.connector.api.TcpConnectionRequestInfo;

/**
 * Startup EJB
 */
@Singleton
@Startup
public class StartupBean {
    private static Logger logger = Logger.getLogger(StartupBean.class.getName());

    private static final int FIXED_DELAY = 30;

    private static final int INITIAL_DELAY = 10;

    @Resource
    private ManagedScheduledExecutorService scheduledExecutorService;    

    @Resource(mappedName = "java:/eis/TcpConnectionFactory")
    private TcpConnectionFactory tcpConnectionFactory;

    private TcpConnection connection;

    private TcpConnectionRequestInfo cxRequestInfo;
    
    private ScheduledFuture<?> future;

    @PostConstruct
    public void init() {
        logger.info(()->"Project-Server starting ...");
        cxRequestInfo = new TcpConnectionRequestInfo("localhost", Integer.valueOf(5000));
        future = scheduledExecutorService.scheduleWithFixedDelay(this::execute, INITIAL_DELAY, FIXED_DELAY, TimeUnit.SECONDS);
    }

    @PreDestroy
    public void cleanup() 
    {
        future.cancel(true);
        logger.info(()->"Goodbye!");
    }

    /**
     * Periodically executed method that sends current time to target.
     * Connection can also be initialized once in init() and used here 
     * without closing (release handle to physical connection). We do
     * as follows to demonstrate that we always get the same physical
     * connection every time.
     */
    private void execute() {
        try {
            connection = tcpConnectionFactory.getConnection(this.cxRequestInfo);
            String date = String.valueOf(new Date());
            connection.write(date.getBytes(), 0, date.getBytes().length);    
        } 
        catch (ResourceException e) {
            e.printStackTrace();
        } 
        finally {
            connection.close();
        } 
    }
}
