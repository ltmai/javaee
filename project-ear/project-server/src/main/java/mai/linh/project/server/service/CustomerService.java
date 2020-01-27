package mai.linh.project.server.service;

import java.util.List;

import mai.linh.project.entities.Customer;

/**
 * Customer service!
 */
public interface CustomerService {
    /**
     * Lookup URL to current implementation used by remote clients
     */
    String JNDI = "java:global/project-ear/project-server/CustomerServiceBean";

    List<Customer> getAllCustomers(); 
}
