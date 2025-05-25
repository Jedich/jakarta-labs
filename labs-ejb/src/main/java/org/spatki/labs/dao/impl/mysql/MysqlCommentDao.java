package org.spatki.labs.dao.impl.mysql;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.spatki.labs.dao.CommentDao;
import org.spatki.labs.model.Comment;
import org.spatki.labs.model.Topic;
import org.spatki.labs.model.User;
import java.util.*;

@Stateless
public class MysqlCommentDao implements CommentDao {

    @PersistenceContext(unitName = "ejbPU")
    private EntityManager em;

    public MysqlCommentDao() {
    }

    @Override
    @Transactional
    public void addComment(Topic topic, User user, String text) {
        Comment comment = new Comment(-1, topic, user, text);
        em.persist(comment);
        topic.getComments().add(comment);
        em.merge(topic);
    }

    @Override
    public List<Comment> findByMovieId(Integer topicId) {
        return em.createQuery(
                "SELECT c FROM Comment c WHERE c.topic.topicId = :topicId", Comment.class)
                .setParameter("topicId", topicId)
                .getResultList();
    }

    @Override
    @Transactional
    public void insert(Comment e, boolean generateId) {
        em.persist(e);
    }

    @Override
    public void delete(Comment e) {
        Comment managed = em.contains(e) ? e : em.merge(e);
        em.remove(managed);
    }

    @Override
    @Transactional
    public void update(Comment e) {
        em.merge(e);
    }

    @Override
    public Comment get(Integer id) {
        return em.find(Comment.class, id);
    }

    @Override
    public Collection<Comment> findAll() {
        String jpql = "SELECT e FROM " + Comment.class.getSimpleName() + " e";
        TypedQuery<Comment> query = em.createQuery(jpql, Comment.class);
        return query.getResultList();
    }
}
