package org.spatki.jakarta.labs.dao.impl.sqlite;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.spatki.jakarta.labs.dao.DaoFactory;

public class SqliteDatabase {

    private final EntityManagerFactory emf;
    private final EntityManager em;

    public SqliteDatabase() {
        this.emf = Persistence.createEntityManagerFactory("sqlite");
        this.em = emf.createEntityManager();
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public void close() {
        if (em.isOpen()) em.close();
        if (emf.isOpen()) emf.close();
    }

    public DaoFactory getDaoFactory() {
        return new SqliteDaoFactory(em);
    }
}
