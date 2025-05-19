package org.spatki.jakarta.labs.dao.impl.inmemory;

import org.spatki.jakarta.labs.dao.DaoFactory;
import org.spatki.jakarta.labs.model.*;
import java.util.*;

public class InMemoryDatabase {

    Map<Integer, Topic> topics;
    Map<Integer, Comment> comments;
    Map<Integer, User> users;

    public InMemoryDatabase() {
        topics = new TreeMap<>();
        comments = new TreeMap<>();
        users = new TreeMap<>();
    }

    public DaoFactory getDaoFactory() {
        return new InMemoryDaoFactory(this);
    }

}
