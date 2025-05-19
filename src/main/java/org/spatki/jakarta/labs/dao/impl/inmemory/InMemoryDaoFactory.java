package org.spatki.jakarta.labs.dao.impl.inmemory;

import org.spatki.jakarta.labs.dao.*;

class InMemoryDaoFactory implements DaoFactory {

    InMemoryDatabase database;

    TopicDao topicDao;
    CommentDao commentDao;
    UserDao userDao;

    InMemoryDaoFactory(InMemoryDatabase database) {
        this.database = database;

        topicDao = new InMemoryTopicDao(database);
        commentDao = new InMemoryCommentDao(database);
        userDao = new InMemoryUserDao(database);
    }

    @Override
    public CommentDao getCommentDao() {
        return commentDao;
    }

    @Override
    public TopicDao getTopicDao() {
        return topicDao;
    }

    @Override
    public UserDao getUserDao() {
        return userDao;
    }

}
