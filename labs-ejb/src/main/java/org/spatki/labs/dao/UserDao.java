package org.spatki.labs.dao;

import jakarta.ejb.Local;
import org.spatki.labs.model.User;

@Local
public interface UserDao extends AbstractDao<User> {

    User getByLogin(String login);
}
