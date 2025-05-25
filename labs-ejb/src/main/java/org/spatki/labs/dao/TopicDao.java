package org.spatki.labs.dao;

import jakarta.ejb.Local;
import org.spatki.labs.model.Topic;
import java.util.Collection;

@Local
public interface TopicDao extends AbstractDao<Topic> {
    Collection<Topic> findByText(String text);
}
