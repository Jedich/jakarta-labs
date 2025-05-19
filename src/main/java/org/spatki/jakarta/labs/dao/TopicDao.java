package org.spatki.jakarta.labs.dao;

import org.spatki.jakarta.labs.model.*;
import java.util.Collection;

public interface TopicDao extends AbstractDao<Topic> {

    void add(Topic topic, User user);

    void remove(Topic topic, User user);
    
    void edit(Topic topic, User user);

    Collection<Topic> findByText(String text);
}
