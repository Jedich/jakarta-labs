package org.spatki.jakarta.labs.services;

import org.spatki.jakarta.labs.dao.DaoFactory;
import org.spatki.jakarta.labs.model.Topic;
import org.spatki.jakarta.labs.model.User;
import java.util.Collection;
import java.util.stream.Collectors;

public class TopicServiceImpl implements TopicService {

    DaoFactory daoFactory;

    public TopicServiceImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public Topic getTopicById(Integer topicId) {
        return daoFactory.getTopicDao().get(topicId);
    }

    @Override
    public void addComment(Topic topic, User user, String comment) {
        daoFactory.getCommentDao().addComment(topic, user, comment);
    }

    @Override
    public Collection<Topic> getAllTopics() {
        return daoFactory.getTopicDao().findAll();
    }

    @Override
    public Collection<Topic> search(String text) {
        if (text == null || text.equals("")) {
            return getAllTopics();
        }
        return daoFactory.getTopicDao().findByText(text);
    }

    @Override
    public void addTopic(Topic topic, User user) {
        daoFactory.getTopicDao().add(topic, user);
    }

    @Override
    public void removeTopic(Topic topic, User user) {
        daoFactory.getTopicDao().remove(topic, user);
    }

    @Override
    public void editTopic(Topic topic, User user) {
       daoFactory.getTopicDao().edit(topic, user);
    }
}
