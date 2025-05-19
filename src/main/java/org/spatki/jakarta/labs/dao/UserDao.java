package org.spatki.jakarta.labs.dao;

import org.spatki.jakarta.labs.model.User;

public interface UserDao extends AbstractDao<User> {

    User getByLogin(String login);
}
