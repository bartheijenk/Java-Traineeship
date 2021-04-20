package jpa.dao;

import jpa.entity.Job;
import jpa.util.Dao;

import javax.persistence.EntityManager;
import jakarta.validation.Validator;

public class JobDao extends Dao<Job, Long> {

    private static JobDao instance = null;

    private JobDao(EntityManager em, Validator validator) {
        super(em, validator);
    }

    public static JobDao instance(EntityManager em, Validator validator) {
        if (instance == null) {
            instance = new JobDao(em, validator);
        }
        return instance;
    }


}
