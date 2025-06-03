package org.spatki.labs.services;

import org.spatki.labs.dao.CommentDao;
import org.spatki.labs.dao.TopicDao;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import org.spatki.labs.model.Topic;
import org.spatki.labs.model.User;
import java.util.Collection;

@Stateless
public class TopicService implements TopicServiceLocal {

    @EJB
    TopicDao topicDao;

    @EJB
    CommentDao commentDao;

    public TopicService() {
    }

    @Override
    public Topic getTopicById(Integer topicId) {
        return topicDao.get(topicId);
    }

    @Override
    public void addComment(Topic topic, User user, String comment) {
        commentDao.addComment(topic, user, comment);
    }

    @Override
    public Collection<Topic> getAllTopics() {
        return topicDao.findAll();
    }

    @Override
    public Collection<Topic> search(String text) {
        if (text == null || text.equals("")) {
            return getAllTopics();
        }
        return topicDao.findByText(text);
    }

    @Override
    public void addTopic(Topic topic, User user) {
        topicDao.insert(topic, true);
    }

    @Override
    public void removeTopic(Topic topic, User user) {
        topicDao.delete(topic);
    }

    @Override
    public void editTopic(Topic topic, User user) {
        topicDao.update(topic);
    }
}
