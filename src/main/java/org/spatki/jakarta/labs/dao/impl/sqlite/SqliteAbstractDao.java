package org.spatki.jakarta.labs.dao.impl.sqlite;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.spatki.jakarta.labs.dao.AbstractDao;

import java.util.Collection;

public abstract class SqliteAbstractDao<T> implements AbstractDao<T> {

    protected final EntityManager em;
    private final Class<T> entityClass;

    protected SqliteAbstractDao(EntityManager em, Class<T> entityClass) {
        this.em = em;
        this.entityClass = entityClass;
    }

    @Override
    public T get(Integer id) {
        return em.find(entityClass, id);
    }

    @Override
    public Collection<T> findAll() {
        String jpql = "SELECT e FROM " + entityClass.getSimpleName() + " e";
        TypedQuery<T> query = em.createQuery(jpql, entityClass);
        return query.getResultList();
    }

    @Override
    public void insert(T entity, boolean generateId) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }

    @Override
    public void delete(T entity) {
        em.getTransaction().begin();
        em.remove(em.contains(entity) ? entity : em.merge(entity));
        em.getTransaction().commit();
    }

    @Override
    public void update(T entity) {
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
    }
}
