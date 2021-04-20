package jpa.dao;

import jpa.entity.Job;
import jpa.util.Dao;

import javax.persistence.EntityManager;

public class JobDao extends Dao<Job, Long> {

    private static JobDao instance = null;

    private JobDao(EntityManager em) {
        super(em);
    }

    public static JobDao instance(EntityManager em) {
        if (instance == null) {
            instance = new JobDao(em);
        }
        return instance;
    }



}
