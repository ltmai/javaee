package mai.linh.webapp.demo;

import javax.ejb.Stateless;

import mai.linh.webapp.demo.ServiceSelection.ServiceType;

/**
 * EJB Implementation with qualifier
 */
@Stateless
@ServiceSelection(type=ServiceType.EXTENDED)
public class ServiceExtBean implements Service {
    public String getMessage() 
    {
        return "[EJB] Hello World (ServiceExtBean)";
    }
}
