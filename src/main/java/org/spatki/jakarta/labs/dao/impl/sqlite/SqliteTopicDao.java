package org.spatki.jakarta.labs.dao.impl.sqlite;

import jakarta.persistence.EntityManager;
import org.spatki.jakarta.labs.dao.TopicDao;
import org.spatki.jakarta.labs.model.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class SqliteTopicDao extends SqliteAbstractDao<Topic> implements TopicDao {

    public SqliteTopicDao(EntityManager em) {
        super(em, Topic.class);
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
    public void add(Topic topic, User user) {
        if (user.getType() != UserType.ADMIN)
            return;
        em.getTransaction().begin();
        em.persist(topic);
        em.getTransaction().commit();
    }

    @Override
    public void remove(Topic topic, User user) {
        if (user.getType() != UserType.ADMIN)
            return;
        em.getTransaction().begin();
        Topic managed = em.contains(topic) ? topic : em.merge(topic);
        em.remove(managed);
        em.getTransaction().commit();
    }

    @Override
    public void edit(Topic topic, User user) {
        if (user.getType() != UserType.ADMIN)
            return;
        em.getTransaction().begin();
        em.merge(topic);
        em.getTransaction().commit();
    }
}
