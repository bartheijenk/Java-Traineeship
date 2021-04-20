package jpa.dao;

import jpa.entity.Job;
import jpa.entity.Person;
import jpa.util.Dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;


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

    public List<Job> getJobs(Long id) {
        Query query = em.createQuery("SELECT p.jobs from Person p where p.id= :id");

        query.setParameter("id", id);
        return query.getResultList();
    }
}
