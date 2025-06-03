package org.spatki.labs.dao.impl.mysql;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.Collection;
import org.spatki.labs.dao.UserDao;
import org.spatki.labs.model.User;

@Stateless
public class MysqlUserDao implements UserDao {

    @PersistenceContext(unitName = "ejbPU")
    private EntityManager em;

    public MysqlUserDao() {
    }
    
    @Override
    public User getByLogin(String login) {
        return em.createQuery(
                "SELECT u FROM User u WHERE u.login = :login", User.class)
                .setParameter("login", login)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    @Override
    @Transactional
    public void insert(User e, boolean generateId) {
        em.persist(e);
    }

    @Override
    public void delete(User e) {
        User managed = em.contains(e) ? e : em.merge(e);
        em.remove(managed);
    }

    @Override
    @Transactional
    public void update(User e) {
        em.merge(e);
    }
    
    @Override
    public User get(Integer id) {
        return em.find(User.class, id);
    }
    
    @Override
    public Collection<User> findAll() {
        String jpql = "SELECT e FROM " + User.class.getSimpleName() + " e";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        return query.getResultList();
    }
}
