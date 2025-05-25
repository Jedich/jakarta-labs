package org.spatki.labs.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Comments")
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @ManyToOne
    private Topic topic;

    @ManyToOne
    private User user;

    private String text;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;

    public Comment() {
    }
    
    public Comment(Integer commentId, Topic topic, User user, String text) {
        this(commentId, topic, user, text, new Date());
    }

    public Comment(Integer commentId, Topic topic, User user, String text, Date creationTime) {
        this.commentId = commentId;
        this.text = text;
        this.topic = topic;
        this.user = user;
        this.creationTime = creationTime;
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

    public Date getCreationTime() {
        return creationTime;
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

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }
    
    public Date getDate() {
       return creationTime;
    }
    
}
