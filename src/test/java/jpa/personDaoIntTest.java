package jpa;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jpa.dao.PersonDao;
import jpa.entity.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class personDaoIntTest {

    private final EntityManager em =
            Persistence.createEntityManagerFactory("MySQL-jpatest").createEntityManager();
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

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
        PersonDao dao = PersonDao.instance(em, validator);
        List<Person> all = dao.findAll();
        assertThat(all.get(0)).isEqualTo(initialPerson);
    }

}