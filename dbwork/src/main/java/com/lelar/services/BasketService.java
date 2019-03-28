package com.lelar.services;

import com.lelar.tables.Basket;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class BasketService {
    private EntityManager em = Persistence.createEntityManagerFactory("postgres").createEntityManager();

    public void add(Basket basket) {
        em.getTransaction().begin();
        em.merge(basket);
        em.getTransaction().commit();
    }

    public void delete(long id) {
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public Basket get(long id) {
        return em.find(Basket.class, id);
    }

    public void update(Basket basket) {
        em.getTransaction().begin();
        em.merge(basket);
        em.getTransaction().commit();
    }


    public void clear() {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM clients");
        em.getTransaction().commit();
    }


    public void end() {
        em.close();
    }

//    public List<Clients> getAll() {
//        TypedQuery<Clients> namedQuery = em.createNamedQuery("Clients.getAll", Clients.class);
//        return namedQuery.getResultList();
//    }
}
