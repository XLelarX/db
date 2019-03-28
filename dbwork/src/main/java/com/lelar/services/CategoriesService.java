package com.lelar.services;

import com.lelar.tables.Categories;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class CategoriesService {
    private EntityManager em = Persistence.createEntityManagerFactory("postgres").createEntityManager();

    public Categories add(Categories categories) {
        em.getTransaction().begin();
        Categories carFromDB = em.merge(categories);
        em.getTransaction().commit();
        return carFromDB;
    }

    public void delete(long id) {
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public Categories get(long id) {
        return em.find(Categories.class, id);
    }

    public void update(Categories car) {
        em.getTransaction().begin();
        em.merge(car);
        em.getTransaction().commit();
    }

//    public List<Categories> getAll() {
//        TypedQuery<Categories> namedQuery = em.createNamedQuery("Categories.getAll", Categories.class);
//        return namedQuery.getResultList();
//    }
}
