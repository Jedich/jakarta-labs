package org.spatki.jakarta.labs.services;

import org.spatki.jakarta.labs.model.User;

public interface UserService {

    User getByLogin(String login);

    boolean checkPassword(User user, String password);
}
