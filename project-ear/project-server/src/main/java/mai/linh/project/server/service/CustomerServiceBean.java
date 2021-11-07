package mai.linh.project.server.service;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
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

    @PostConstruct
    public void postConstruct()
    {
        logger.info("CustomerServiceBean::postConstruct");
    }

    @PreDestroy
    public void preDestroy()
    {
        logger.info("CustomerServiceBean::preDestroy");
    }

    /**
     * Pitfall when using instance variable in EJB:
     * The container might not (re)initialize the instance variables 
     * at every injection. So if you have instance variable in EJB
     * make sure to (re)initialize it yourself or it may still hold
     * the old value from last injection. 
     */
    private Date initDate = new Date();

    @Override
    @Logged(name = "Find all customers")    
    public List<Customer> getAllCustomers() 
    {
        logger.info("Service created at " + initDate.toString());
        logger.info("Caller name: " + context.getCallerPrincipal().getName());
        logger.info("CustomerServiceBean - Transaction status: " + txReg.getTransactionStatus());
        return db.getAllCustomers();
    }
}