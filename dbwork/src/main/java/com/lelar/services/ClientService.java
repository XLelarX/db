package com.lelar.services;

import com.lelar.tables.Client;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class ClientService {
    private EntityManager em = Persistence.createEntityManagerFactory("postgres").createEntityManager();

    public void add(Client client) {
        em.getTransaction().begin();
        em.merge(client);
        em.getTransaction().commit();
    }

    public void delete(long id) {
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public Client get(long id) {
        return em.find(Client.class, id);
    }

    public void update(Client details) {
        em.getTransaction().begin();
        em.merge(details);
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
