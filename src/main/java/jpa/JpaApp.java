package jpa;

import jpa.dao.JobDao;
import jpa.dao.PersonDao;
import jpa.entity.Job;
import jpa.entity.Person;
import lombok.extern.log4j.Log4j2;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

@Log4j2
public class JpaApp {

    private final EntityManager em =
            Persistence.createEntityManagerFactory("MySQL-jpademo").createEntityManager();

    public static void main(String[] args) {
        new JpaApp().start();
    }

    private void start() {
        PersonDao personDao = PersonDao.instance(em);
        JobDao jobDao = JobDao.instance(em);



        personDao.getJobs(8L).forEach(log::debug);
    }
}
