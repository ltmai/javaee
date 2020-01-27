package mai.linh.project.server.repository;

import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import mai.linh.project.entities.Customer;

/**
 * This class implements JPA work on Customer entity 
 */
@Stateless
public class CustomerRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Customer> getAllCustomers()
    {
        TypedQuery<Customer> query = em.createNamedQuery("Customer.GetAllCustomers", Customer.class);
        return query.getResultList();
    }

    public void createUser(Customer customer) 
    {
        em.persist(customer);
    }

    public Optional<Customer> findCustomer(final Long id) 
    {
        Customer customer = em.find(Customer.class, id);
        return Optional.of(customer);
    }

    public void updateCustomer(List<Customer> users) 
    {
        users.forEach(em::merge);
    }

    public void deleteCustomer(Customer customer) 
    {
        if (em.contains(customer))
        {
            em.remove(customer);
        }
        else
        {
            Customer managedCustomer = em.find(Customer.class, customer.getId());
            if (managedCustomer != null) 
            {
                em.remove(customer);
            }
        }
    }
}