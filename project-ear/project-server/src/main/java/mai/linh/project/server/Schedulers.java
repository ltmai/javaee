package mai.linh.project.server;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.ejb.AccessTimeout;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * Example of Singleton with container managed concurrency (demonstration only).
 * 
 * The singleton methods are by default annotated with @Lock(LockType.WRITE) so the singleton is 
 * locked to other clients while a client is calling that method.
 * 
 * The following example simulates two scheduled long running tasks. If can be seen that without 
 * explicit @Lock(READ) the second task must wait for the first one to complete because the method 
 * is by default @Lock(WRITE).
 * 
 * The waiting client can try again but not longer than a specific time of 5 seconds before it 
 * throws a javax.ejb.ConcurrentAccessTimeoutException. This duration can be explicitly set as
 * follows @AccessTimeout(value=20, unit=TimeUnit.SECONDS).
 * 
 * Example of ConcurrentAccessTimeoutException:
 * 23:57:30,001 ERROR [org.jboss.as.ejb3.timer] (EJB default - 6) 
 * WFLYEJB0020: Error invoking timeout for timer: [id=7a1f6e30-d6d1-426b-a566-9444f49e1470 
 * timedObjectId=project-ear.project-server.Schedulers auto-timer?:true persistent?:false 
 * timerService=org.jboss.as.ejb3.timerservice.TimerServiceImpl@63509c40 initialExpiration=null 
 * intervalDuration(in milli sec)=0 nextExpiration=Fri Jan 24 23:57:30 CET 2020 timerState=IN_TIMEOUT 
 * info=null]: javax.ejb.ConcurrentAccessTimeoutException: WFLYEJB0241: EJB 3.1 PFD2 4.8.5.5.1 
 * concurrent access timeout on Schedulers - could not obtain lock within 20SECONDS
 * 
 * If the scheduled task execution takes long, it should be done asynchronously. 
 */
@Startup
@Singleton
@AccessTimeout(value=20, unit=TimeUnit.SECONDS) 
public class Schedulers {

    /**
     * This method is scheduled to run every 10 seconds, but it actually takes
     * longer to complete so we expect to get an warning that a previous execution
     * of timer is still in progress, and the method invocation is skipped.
     */
    @Lock(LockType.READ)
    @Schedule(hour = "*", minute = "*", second = "*/10", persistent = false)
    public void doSomethingExpensive() {
        try {
            System.out.println("!!!!!!!!!!!!!!! invoked every 10 seconds !!!!!!!!!!!!!!!");
            TimeUnit.SECONDS.sleep(11);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Lock(LockType.READ)
    @Schedule(hour = "*", minute = "*", second = "*/2", persistent = false)
    public void doSomethingLessExpensive() {
        try {
            System.out.println("************** invoked every 2 seconds **************");
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}