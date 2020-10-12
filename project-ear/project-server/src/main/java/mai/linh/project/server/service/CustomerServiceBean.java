package mai.linh.project.server.service;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.TransactionSynchronizationRegistry;

import org.apache.logging.log4j.Logger;

import mai.linh.project.entities.Customer;
import mai.linh.project.server.interceptor.Logged;
import mai.linh.project.server.producer.Log4J;
import mai.linh.project.server.repository.CustomerRepository;

/**
 * Implementation of CustomerService
 */
@Stateless
public class CustomerServiceBean implements CustomerService
{
	@Inject
    private CustomerRepository db;

    @Resource
    private SessionContext context;

    @Inject @Log4J 
    private Logger logger;

    @Resource
    TransactionSynchronizationRegistry txReg;

    @Override
    @Logged(name = "Find all customers")    
    public List<Customer> getAllCustomers() 
    {
        logger.info("Caller name: " + context.getCallerPrincipal().getName());
        logger.info("CustomerServiceBean - Transaction status: " + txReg.getTransactionStatus());
        return db.getAllCustomers();
    }
}