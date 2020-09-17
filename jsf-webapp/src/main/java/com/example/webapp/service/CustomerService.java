package com.example.webapp.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.webapp.entities.Customer;

@Stateless
public class CustomerService {
    
    @PersistenceContext
    private EntityManager em;

    public void update(Customer c) {
        em.merge(c);
    }

    public List<Customer> findAll(int page, int pageSize) {
     return em.createQuery("SELECT c FROM Customer c ORDER BY c.id", Customer.class)
              .setMaxResults(pageSize)
              .setFirstResult((page-1) * pageSize)
              .getResultList();
    }

	public Customer findById(long id) {
        return em.createQuery("SELECT c FROM Customer c WHERE id = :id", Customer.class)
                 .setParameter("id", id)
                 .setMaxResults(1)
                 .getSingleResult();
    }
    
    public void deleteById(final long id) {
        em.createQuery("DELETE Customer c WHERE id = :id")
          .setParameter("id", id)
          .executeUpdate();
    }

	public long findNumberOfPages(int pageSize) {
        long rowCount = (long) em.createQuery("SELECT count(*) FROM Customer c").getSingleResult();
        long pageCount = rowCount / pageSize;
        if (rowCount % pageSize > 0)
            pageCount++;
		return pageCount;
	}
}