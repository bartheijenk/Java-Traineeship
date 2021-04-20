package jpa;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jpa.dao.JobDao;
import jpa.dao.PersonDao;
import jpa.entity.Job;
import lombok.extern.log4j.Log4j2;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.Set;

@Log4j2
public class JpaApp {

    private final EntityManager em =
            Persistence.createEntityManagerFactory("MySQL-jpademo").createEntityManager();

    public static void main(String[] args) {
        new JpaApp().start();
    }

    private void start() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        PersonDao personDao = PersonDao.instance(em, validator);
        JobDao jobDao = JobDao.instance(em, validator);

        Job j = Job.builder()
                .name("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
                .build();

        jobDao.save(j);

//        validator.validate()

        personDao.getJobs(8L).forEach(log::debug);
    }
}
