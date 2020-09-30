package mai.linh.webapp.demo.ejb;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 * EJB Default implementation
 */
@Stateless
@ServiceSelection
public class ServiceBean implements Service {
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String getMessage() {
        return "[EJB] Hello World";
    }
}
