package org.spatki.jakarta.labs.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Entity;
import java.time.Instant;
import java.util.*;

@Entity
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer topicId;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    private String title;
    private Instant instant;

    public Topic() {
    }

    public Topic(Integer topicId, String title) {
        this(topicId, title, Instant.now());
    }

    public Topic(Integer topicId, String title, Instant instant) {
        this.topicId = topicId;
        this.title = title;
        this.instant = instant;
        comments = new ArrayList<>();
    }

    public Integer getTopicId() {
        return topicId;
    }

    public String getTitle() {
        return title;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public Instant getInstant() {
        return instant;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    public int getNumberOfComments() {
        return comments.size();
    }

}
