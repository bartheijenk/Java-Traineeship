package jpa.dao;

import jpa.entity.Job;
import jpa.entity.Person;
import jpa.util.Dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import jakarta.validation.Validator;
import java.util.List;


public class PersonDao extends Dao<Person, Long> {

    private static PersonDao instance;

    public PersonDao(EntityManager em, Validator validator) {
        super(em, validator);
    }


    public static PersonDao instance(EntityManager em, Validator validator) {
        if (instance == null) {
            instance = new PersonDao(em, validator);
        }
        return instance;
    }

    public void updateName(Person p, String name) {
        em.getTransaction().begin();
        find(p.getId()).setName(name);
        em.getTransaction().commit();
    }

    @SuppressWarnings("unchecked")
    public List<Job> getJobs(Long id) {
        Query query = em.createQuery("SELECT p.jobs from Person p where p.id= :id");

        query.setParameter("id", id);
        return query.getResultList();
    }
}
