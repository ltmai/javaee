package mai.linh.project.server.interceptor;

import java.io.Serializable;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.apache.logging.log4j.Logger;

@Logged
@Interceptor
public class LoggedInterceptor implements Serializable 
{
    private static final long serialVersionUID = 1L;

    @Inject
	Event<String> txListeners;
	
	@Inject
	private Logger logger;

    @AroundInvoke
    public Object logMethodEntry(InvocationContext invocationContext) throws Exception 
    {
		try {
			logger.info("[interceptor] entering " + invocationContext.getMethod().getName());
			return invocationContext.proceed();
		}
		finally {
			/**
			 * success or failure
			 */
			logger.info("[interceptor] exiting " + invocationContext.getMethod().getName());
			txListeners.fire(invocationContext.getMethod().getAnnotation(Logged.class).name());
		}
    }
}