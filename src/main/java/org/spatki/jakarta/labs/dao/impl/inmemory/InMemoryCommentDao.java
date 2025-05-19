package org.spatki.jakarta.labs.dao.impl.inmemory;

import org.spatki.jakarta.labs.dao.CommentDao;
import org.spatki.jakarta.labs.model.Comment;
import org.spatki.jakarta.labs.model.Topic;
import org.spatki.jakarta.labs.model.User;
import java.util.*;

class InMemoryCommentDao extends InMemoryAbstractDao<Comment> implements CommentDao {

    InMemoryCommentDao(InMemoryDatabase database) {
        super(database.comments, Comment::getCommentId, Comment::setCommentId, database);
    }

    @Override
    public Collection<Comment> findByMovieId(Integer moveId) {
        return database.topics.get(moveId).getComments();
    }

    @Override
    public void addComment(Topic topic, User user, String text) {
        Comment comment = new Comment(-1, topic, user, text);

        this.insert(comment, true);
        topic.getComments().add(comment);
    }

}
