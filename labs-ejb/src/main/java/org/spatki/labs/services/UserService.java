package org.spatki.labs.services;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateful;
import org.spatki.labs.model.User;
import java.util.function.UnaryOperator;
import org.spatki.labs.dao.UserDao;

@Stateful
public class UserService implements UserServiceLocal {

    @EJB
    UserDao userDao;
    
    UnaryOperator<String> passwordHasher = UnaryOperator.identity();

    public UserService() {
    }

    @Override
    public User getByLogin(String login) {
        return userDao.getByLogin(login);
    }

    @Override
    public boolean checkPassword(User user, String password) {
        return user.getPasswordHash().equals(passwordHasher.apply(password));
    }
}
