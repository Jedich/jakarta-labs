package org.spatki.jakarta.labs.dao.impl.sqlite;

import jakarta.persistence.EntityManager;
import org.spatki.jakarta.labs.model.*;

import java.util.Arrays;
import java.util.List;

public class SqliteTestData {

    public static void generateTo(EntityManager em) {
        em.getTransaction().begin();

        em.createQuery("DELETE FROM Comment").executeUpdate();
        em.createQuery("DELETE FROM Topic").executeUpdate();
        em.createQuery("DELETE FROM User").executeUpdate();

        User mAdmin = new User(1, "ADMINICHEK", "admin", "pwd");
        mAdmin.setType(UserType.ADMIN);

        User user1 = new User(2, "USER1", "user1", "pwd");
        User user2 = new User(3, "USER2", "user2", "pwd");
        List<User> users = Arrays.asList(mAdmin, user1, user2);
        users.forEach(em::persist);

        Topic javaLang = new Topic(1, "Java lang");
        Topic justTest = new Topic(2, "Test Topic");

        Comment c1 = new Comment(1, javaLang, user1, "Best language ever!!1");
        Comment c2 = new Comment(2, javaLang, user2, "Chell...!");
        Comment c3 = new Comment(3, justTest, user1, "Hi");
        Comment c4 = new Comment(4, justTest, user1, "where r u guys?");
        Comment c5 = new Comment(5, justTest, user2, "Shatup.");

        javaLang.getComments().addAll(List.of(c1, c2));
        justTest.getComments().addAll(List.of(c3, c4, c5));

        em.persist(javaLang);
        em.persist(justTest);

        List<Comment> comments = List.of(c1, c2, c3, c4, c5);
        comments.forEach(em::persist);

        em.getTransaction().commit();
    }
}
