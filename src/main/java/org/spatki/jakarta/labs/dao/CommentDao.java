package org.spatki.jakarta.labs.dao;

import org.spatki.jakarta.labs.model.*;
import java.util.Collection;

public interface CommentDao extends AbstractDao<Comment> {

    Collection<Comment> findByMovieId(Integer moveId);

    void addComment(Topic movie, User user, String text);
}
