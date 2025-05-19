package org.spatki.jakarta.labs.dao;

public interface DaoFactory {

    CommentDao getCommentDao();

    TopicDao getTopicDao();

    UserDao getUserDao();
}
