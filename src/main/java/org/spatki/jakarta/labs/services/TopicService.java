package org.spatki.jakarta.labs.services;

import org.spatki.jakarta.labs.model.*;
import java.util.Collection;

public interface TopicService {

    Collection<Topic> getAllTopics();

    Collection<Topic> search(String text);

    Topic getTopicById(Integer topicId);

    void addTopic(Topic topic, User user);

    void removeTopic(Topic topic, User user);
    
    void editTopic(Topic topic, User user);

    void addComment(Topic topic, User user, String comment);
}
