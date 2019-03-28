package com.lelar.services;

import com.lelar.tables.Details;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class DetailsSevice {
    private EntityManager em = Persistence.createEntityManagerFactory("postgres").createEntityManager();

    public Details add(Details details) {
        em.getTransaction().begin();
        Details detailsFromDB = em.merge(details);
        em.getTransaction().commit();
        return detailsFromDB;
    }

    public void delete(long id) {
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public Details get(long id) {
        return em.find(Details.class, id);
    }

    public void update(Details details) {
        em.getTransaction().begin();
        em.merge(details);
        em.getTransaction().commit();
    }

//    public List<Details> getAll() {
//        TypedQuery<Details> namedQuery = em.createNamedQuery("Details.getAll", Details.class);
//        return namedQuery.getResultList();
//    }
}
