package com.lelar.services;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Service<T> {
    private EntityManager em = Persistence.createEntityManagerFactory("postgres").createEntityManager();

    private Class<T> type;

    public Service(Class<T> type) {
        this.type = type;
    }

    public void add(T t) {
        em.getTransaction().begin();
        em.merge(t);
        em.getTransaction().commit();
    }

    public T getId(long id) {
        em.getTransaction().begin();
        T t = get(id);
        em.getTransaction().commit();
        return t;
    }

    public void delete(long id) {
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    private T get(long id) {
        return em.find(type, id);
    }

    public void update(T t) {
        em.getTransaction().begin();
        em.merge(t);
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
