package com.example.jaspic;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class UserService {
    @PersistenceContext(unitName = "H2")
    private EntityManager em;

    @PostConstruct
    public void init() {
        System.out.println("Entity Manager injected? " + (em != null));
    }

    public boolean authenticate(String username, String password) {
        User user = em.createQuery("SELECT user FROM User user WHERE username=:username AND password=:password", User.class)
                      .setParameter("username", username)
                      .setParameter("password", password)
                      .setMaxResults(1)
                      .getSingleResult();
        return (user != null);
    }
}
