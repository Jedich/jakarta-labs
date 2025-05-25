package org.spatki.labs.dao;

import jakarta.ejb.Local;
import org.spatki.labs.model.Topic;
import org.spatki.labs.model.User;
import org.spatki.labs.model.Comment;
import java.util.Collection;

@Local
public interface CommentDao extends AbstractDao<Comment> {

    Collection<Comment> findByMovieId(Integer moveId);

    void addComment(Topic movie, User user, String text);
}
