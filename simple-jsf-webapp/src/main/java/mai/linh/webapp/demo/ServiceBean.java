package mai.linh.webapp.demo;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import mai.linh.webapp.demo.ServiceSelection.ServiceType;

/**
 * EJB Default implementation
 */
@Stateless
@ServiceSelection(type=ServiceType.DEFAULT)
public class ServiceBean implements Service {
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String getMessage() {
        return "[EJB] Hello World";
    }
}
