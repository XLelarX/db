package com.lelar.services;

import com.lelar.tables.Product;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class ProductService {
    private EntityManager em = Persistence.createEntityManagerFactory("postgres").createEntityManager();

    public void add(Product product) {
        em.getTransaction().begin();
        em.merge(product);
        em.getTransaction().commit();
    }

    public void delete(long id) {
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public Product get(long id) {
        return em.find(Product.class, id);
    }

    public void update(Product product) {
        em.getTransaction().begin();
        em.merge(product);
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
