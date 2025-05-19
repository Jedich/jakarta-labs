package org.spatki.jakarta.labs.dao.impl.inmemory;

import org.spatki.jakarta.labs.model.Comment;
import org.spatki.jakarta.labs.model.Topic;
import org.spatki.jakarta.labs.model.User;
import org.spatki.jakarta.labs.model.UserType;
import java.util.Arrays;
import java.util.List;

public class InMemoryTestData {

    public static void generateTo(InMemoryDatabase database) {
        database.users.clear();
        database.topics.clear();
        database.comments.clear();

        User mAdmin = new User(1, "ADMINICHEK", "admin", "pwd");
        mAdmin.setType(UserType.ADMIN);
        
        User user1 = new User(2, "USER1", "user1", "pwd");
        User user2 = new User(3, "USER2", "user2", "pwd");
        List<User> users = Arrays.asList(mAdmin, user1, user2);
        users.forEach(user -> database.users.put(user.getUserId(), user));

        Topic javaLang = new Topic(1, "Java lang");
        Topic justTest = new Topic(2, "Test Topic");
        List<Topic> topics = Arrays.asList(javaLang, justTest);
        topics.forEach(topic -> database.topics.put(topic.getTopicId(), topic));

        
        javaLang.getComments().add(new Comment(1, javaLang, user1, "Best language ever!!1"));
        javaLang.getComments().add(new Comment(2, javaLang, user2, "Chell..."));
        justTest.getComments().add(new Comment(3, justTest, user1, "Hi"));
        justTest.getComments().add(new Comment(4, justTest, user1, "where r u guys?"));
        justTest.getComments().add(new Comment(5, justTest, user2, "Shatup."));
        topics.stream()
                .flatMap(topic -> topic.getComments().stream())
                .forEach(comment -> database.comments.put(comment.getCommentId(), comment));
    }
}
