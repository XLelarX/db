package com.lelar;


import javax.persistence.EntityManager;
import javax.persistence.Persistence;

class TableManager {

    public EntityManager em = Persistence.createEntityManagerFactory("postgres").createEntityManager();


    TableManager() {
    }

    public void add(Class clazz) {
        em.getTransaction().begin();

    }


}
