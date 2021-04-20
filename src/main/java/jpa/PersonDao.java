package jpa;

import jpa.util.Dao;

import javax.persistence.EntityManager;


public class PersonDao extends Dao<Person, Long> {

    private static PersonDao instance;

    private PersonDao(EntityManager em) {
        super(em);
    }

    public static PersonDao instance(EntityManager em) {
        if (instance == null) {
            instance = new PersonDao(em);
        }
        return instance;
    }

    public void updateName(Person p, String name) {
        em.getTransaction().begin();
        find(p.getId()).setName(name);
        em.getTransaction().commit();
    }
}
