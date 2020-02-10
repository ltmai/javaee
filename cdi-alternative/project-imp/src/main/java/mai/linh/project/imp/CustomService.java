package mai.linh.project.imp;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Priority;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Alternative;
import javax.interceptor.Interceptor;

import mai.linh.project.api.Service;

/**
 * Our custom implementation of Service which will be used instead of the default
 * implementation provided by API module (thanks @Priority) 
 */
@RequestScoped
@Alternative    
@Priority(Interceptor.Priority.APPLICATION)
public class CustomService implements Service
{
    static private Logger logger = Logger.getLogger(CustomService.class.getName());

    @Override
    public void saySomething() {
        logger.log(Level.SEVERE, "[CDI] Thanks for using our CUSTOM service");
    }
}