package mai.linh.project.api;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;

/**
 * Default Service implementation
 */
@RequestScoped
public class DefaultService implements Service
{
    static private Logger logger = Logger.getLogger(DefaultService.class.getName());

    @Override
    public void saySomething() {
        logger.log(Level.SEVERE, "[CDI] Thanks for using our DEFAULT service");
    }
}