package mai.linh.webservice;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * ProjectApplication
 */
@ApplicationPath("/ws")
public class RestApplication extends Application
{
    @Override
    public Set<Class<?>> getClasses()
    {
        return new HashSet<Class<?>>(Arrays.asList(WebServices.class));
    }       
}