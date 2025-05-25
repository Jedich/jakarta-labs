package org.spatki.labs.dao.impl.mysql;

import jakarta.ejb.Stateful;
import org.spatki.labs.model.Topic;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.spatki.labs.dao.TopicDao;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Stateless
public class MysqlTopicDao implements TopicDao {

    @PersistenceContext(unitName = "ejbPU")
    private EntityManager em;

    public MysqlTopicDao() {
    }

    @Override
    public Collection<Topic> findByText(String text) {
        String[] words = text.toLowerCase().split(" ");
        List<Topic> allTopics = em.createQuery("SELECT t FROM Topic t", Topic.class).getResultList();
        return allTopics.stream()
                .filter(topic -> containsAllWords(topic, words))
                .collect(Collectors.toList());
    }

    private static boolean containsAllWords(Topic topic, String[] words) {
        String title = topic.getTitle().toLowerCase();
        return Stream.of(words).allMatch(title::contains);
    }

    @Override
    @Transactional
    public void insert(Topic topic, boolean generateId) {
        em.persist(topic);
    }

    @Override
    public void delete(Topic topic) {
        Topic managed = em.contains(topic) ? topic : em.merge(topic);
        em.remove(managed);
    }

    @Override
    @Transactional
    public void update(Topic topic) {
        em.merge(topic);
    }

    @Override
    public Topic get(Integer id) {
        return em.find(Topic.class, id);
    }

    @Override
    public Collection<Topic> findAll() {
        String jpql = "SELECT e FROM " + Topic.class.getSimpleName() + " e";
        TypedQuery<Topic> query = em.createQuery(jpql, Topic.class);
        return query.getResultList();
    }
}
