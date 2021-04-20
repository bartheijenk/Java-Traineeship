package jpa;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class personDaoIntTest {

    private final EntityManager em =
            Persistence.createEntityManagerFactory("MySQL-jpatest").createEntityManager();

    private Person initialPerson;

    @BeforeAll
    public void setUp() {
        em.getTransaction().begin();
        initialPerson = Person.builder()
                .age(24)
                .name("Henk")
                .build();
        em.persist(initialPerson);
        em.getTransaction().commit();
    }

    @Test
    @Transactional
    public void findAllTest() {
        PersonDao dao = PersonDao.instance(em);
        List<Person> all = dao.findAll();
        assertThat(all.get(0)).isEqualTo(initialPerson);
    }

}