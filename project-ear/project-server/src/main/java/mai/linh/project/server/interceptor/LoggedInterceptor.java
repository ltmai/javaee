package mai.linh.project.server.interceptor;

import java.io.Serializable;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.apache.logging.log4j.Logger;

import mai.linh.project.server.producer.Log4J;

@Logged
@Interceptor
public class LoggedInterceptor implements Serializable 
{
    private static final long serialVersionUID = 1L;

    @Inject
	Event<String> event;
	
	@Inject @Log4J
	private Logger logger;

    @AroundInvoke
    public Object logMethodEntry(InvocationContext invocationContext) throws Exception 
    {
		try {
			logger.info("[interceptor] entering " + invocationContext.getMethod().getName());
			return invocationContext.proceed();
		}
		finally {
			logger.info("[interceptor] exiting " + invocationContext.getMethod().getName());
			event.fire(invocationContext.getMethod().getAnnotation(Logged.class).name());
		}
    }
}