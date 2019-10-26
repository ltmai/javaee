package mai.linh.project.server;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.logging.log4j.Logger;

import mai.linh.project.entities.Customer;
import mai.linh.project.server.database.DBManager;
import mai.linh.project.server.interceptor.Logged;

/**
 * ServiceBean EJB
 */
@Stateless
public class CustomerServiceBean implements CustomerService
{
	@Inject
    private DBManager db;

    @Resource
    private SessionContext context;

    @Inject
    private Logger logger;

    @Override
    @Logged(name = "Find all customers")    
    public List<Customer> getAllCustomers() 
    {
        logger.info("Caller name: " + context.getCallerPrincipal().getName());
        return db.getAllCustomers();
    }
}