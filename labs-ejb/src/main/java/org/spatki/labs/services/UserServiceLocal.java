package org.spatki.labs.services;

import jakarta.ejb.Local;
import org.spatki.labs.model.User;

@Local
public interface UserServiceLocal {

    User getByLogin(String login);

    boolean checkPassword(User user, String password);
}
