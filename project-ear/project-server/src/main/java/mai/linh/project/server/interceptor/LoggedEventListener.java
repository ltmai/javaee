package mai.linh.project.server.interceptor;

import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.inject.Inject;

import org.apache.logging.log4j.Logger;

/**
 * Handle @Logged events when transaction completes or fails
 */
@Stateless
public class LoggedEventListener {

    @Inject
    private Logger logger;

	/**
	 * invoked only after the transaction completed successfully
	 * @param msg
	 */
    public void onSuccess(@Observes(during= TransactionPhase.AFTER_SUCCESS) String msg) {
        logger.info("[Event] Transaction completed: " + msg);
    }

    /**
     * invoked only after the transaction completed with failure
     * @param msg
     */
    public void onFailure(@Observes(during= TransactionPhase.AFTER_FAILURE) String msg){
        logger.info("[Event] Transaction failed: " + msg);
    }  
}
