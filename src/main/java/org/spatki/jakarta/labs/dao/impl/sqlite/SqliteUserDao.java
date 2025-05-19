package org.spatki.jakarta.labs.dao.impl.sqlite;

import jakarta.persistence.EntityManager;
import org.spatki.jakarta.labs.dao.UserDao;
import org.spatki.jakarta.labs.model.User;

class SqliteUserDao extends SqliteAbstractDao<User> implements UserDao {

    public SqliteUserDao(EntityManager em) {
        super(em, User.class);
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
}
