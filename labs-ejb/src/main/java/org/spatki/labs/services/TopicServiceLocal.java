package org.spatki.labs.services;

import jakarta.ejb.Local;
import org.spatki.labs.model.*;
import java.util.Collection;

@Local
public interface TopicServiceLocal {

    Collection<Topic> getAllTopics();

    Collection<Topic> search(String text);

    Topic getTopicById(Integer topicId);

    void addTopic(Topic topic, User user);

    void removeTopic(Topic topic, User user);
    
    void editTopic(Topic topic, User user);

    void addComment(Topic topic, User user, String comment);
}
