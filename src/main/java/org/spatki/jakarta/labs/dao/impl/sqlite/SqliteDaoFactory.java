package org.spatki.jakarta.labs.dao.impl.sqlite;

import jakarta.persistence.EntityManager;
import org.spatki.jakarta.labs.dao.*;

public class SqliteDaoFactory implements DaoFactory {

    private final CommentDao commentDao;
    private final TopicDao topicDao;
    private final UserDao userDao;

    public SqliteDaoFactory(EntityManager em) {
        this.commentDao = new SqliteCommentDao(em);
        this.topicDao = new SqliteTopicDao(em);
        this.userDao = new SqliteUserDao(em);
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
