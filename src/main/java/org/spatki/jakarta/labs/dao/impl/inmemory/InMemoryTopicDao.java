package org.spatki.jakarta.labs.dao.impl.inmemory;

import org.spatki.jakarta.labs.model.*;
import java.util.Collection;
import java.util.stream.*;
import org.spatki.jakarta.labs.dao.TopicDao;

class InMemoryTopicDao extends InMemoryAbstractDao<Topic> implements TopicDao {

    InMemoryTopicDao(InMemoryDatabase database) {
        super(database.topics, Topic::getTopicId, Topic::setTopicId, database);
    }

    

    @Override
    public Collection<Topic> findByText(String text) {
        String[] words = text.toLowerCase().split(" ");
        return database.topics.values().stream()
                .filter(movie -> containsAllWords(movie, words))
                .collect(Collectors.toList());
    }

    private static boolean containsAllWords(Topic movie, String[] words) {
        String string = movie.getTitle();
        string = string.toLowerCase();
        return Stream.of(words).allMatch(string::contains);
    }

    @Override
    public void add(Topic topic, User user) {
        if (user.getType() != UserType.ADMIN)
            return;
        this.insert(topic, true);
    }

    @Override
    public void remove(Topic topic, User user) {
        if (user.getType() != UserType.ADMIN)
            return;
        this.delete(topic);
    }

    @Override
    public void edit(Topic topic, User user) {
        if (user.getType() != UserType.ADMIN)
            return;
        this.update(topic);
    }
    
    

}
