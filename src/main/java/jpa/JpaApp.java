package jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class JpaApp {

    private final Logger log = LoggerFactory.getLogger(JpaApp.class);
    private final EntityManager em =
            Persistence.createEntityManagerFactory("MySQL-jpatest").createEntityManager();

    public static void main(String[] args) {
        new JpaApp().start();
    }

    private void start(){
        PersonDao personDao = PersonDao.instance(em);

        Person p = Person.builder()
                .name("Henk")
                .age(24)
                .build();
        personDao.save(p);

        personDao.findAll().forEach(ps -> log.debug(ps.getName()));
    }
}
