package jpa.util;

import javax.persistence.EntityManager;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.validation.groups.Default;
import lombok.extern.log4j.Log4j2;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Set;

@Log4j2
public abstract class Dao<E extends Identifiable<K>, K> {

    protected EntityManager em;
    protected Validator validator;

    public Dao(EntityManager em, Validator validator) {
        this.em = em;
        this.validator = validator;
    }

    public List<E> findAll() {
        return em.createNamedQuery(typeSimple() + ".findAll", E()).getResultList();
    }

    public E find(K id) {
        return em.find(E(), id);
    }

    public void save(E e) {
        Set<ConstraintViolation<E>> validate = validator.validate(e, Default.class);
        if (validate.isEmpty()) {
            em.getTransaction().begin();
            em.persist(e);
            em.getTransaction().commit();
        } else {
            validate.forEach(eConstraintViolation -> log.debug(eConstraintViolation.getMessage()));
        }
    }

    public E update(E e) {
        em.getTransaction().begin();
        E mergedE = em.merge(e);
        em.getTransaction().commit();
        return mergedE;
    }

    public void remove(E e) {
        em.getTransaction().begin();
        em.remove(e.getId());
        em.getTransaction().commit();
    }

    @SuppressWarnings("unchecked")
    private Class<E> E() {
        ParameterizedType thisDaoClass = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<E>) thisDaoClass.getActualTypeArguments()[0];
    }

    private String typeSimple() {
        return E().getSimpleName();
    }
}
