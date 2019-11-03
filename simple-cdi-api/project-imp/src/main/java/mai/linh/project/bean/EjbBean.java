package mai.linh.project.bean;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 * EjbBean
 */
@Stateless
public class EjbBean {
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void doSomething() {
        System.out.println("[EJB] doSomething");
    }
}