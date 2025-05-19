package org.spatki.jakarta.labs.dao.impl.sqlite;

import jakarta.persistence.EntityManager;
import org.spatki.jakarta.labs.dao.CommentDao;
import org.spatki.jakarta.labs.model.Comment;
import org.spatki.jakarta.labs.model.Topic;
import org.spatki.jakarta.labs.model.User;
import java.util.*;

class SqliteCommentDao extends SqliteAbstractDao<Comment> implements CommentDao {

    public SqliteCommentDao(EntityManager em) {
        super(em, Comment.class);
    }

    @Override
    public void addComment(Topic topic, User user, String text) {
        em.getTransaction().begin();
        Comment comment = new Comment();
        comment.setTopic(topic);
        comment.setUser(user);
        comment.setText(text);
        em.persist(comment);
        topic.getComments().add(comment);
        em.merge(topic); // Optional, if topic already managed
        em.getTransaction().commit();
    }

    @Override
    public List<Comment> findByMovieId(Integer topicId) {
        return em.createQuery(
                "SELECT c FROM Comment c WHERE c.topic.topicId = :topicId", Comment.class)
                .setParameter("topicId", topicId)
                .getResultList();
    }
}
