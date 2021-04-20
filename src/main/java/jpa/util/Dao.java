package jpa.util;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class Dao<E extends Identifiable<K>, K> {

    protected EntityManager em;

    public Dao(EntityManager em) {
        this.em = em;
    }

    public List<E> findAll() {
        return em.createNamedQuery(typeSimple() + ".findAll", E()).getResultList();
    }

    public E find(K id) {
        return em.find(E(), id);
    }

    public void save(E e) {
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
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
