package org.spatki.jakarta.labs.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.Instant;
import java.util.Date;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @ManyToOne
    private Topic topic;

    @ManyToOne
    private User user;

    private String text;

    private Instant instant;

    public Comment() {
    }
    
    public Comment(Integer commentId, Topic topic, User user, String text) {
        this(commentId, topic, user, text, Instant.now());
    }

    public Comment(Integer commentId, Topic movie, User user, String text, Instant instant) {
        this.commentId = commentId;
        this.text = text;
        this.topic = movie;
        this.user = user;
        this.instant = instant;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public String getText() {
        return text;
    }

    public Topic getTopic() {
        return topic;
    }

    public User getUser() {
        return user;
    }

    public Instant getInstant() {
        return instant;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }
    
    public Date getDate() {
       return Date.from(instant); 
    }
    
}
