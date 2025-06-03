package org.spatki.labs.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "Topics")
public class Topic implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer topicId;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    private String title;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;

    public Topic() {
    }

    public Topic(Integer topicId, String title) {
        this(topicId, title, new Date());
    }

    public Topic(Integer topicId, String title, Date creationTime) {
        this.topicId = topicId;
        this.title = title;
        this.creationTime = creationTime;
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

    public Date getCreationTime() {
        return creationTime;
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

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public int getNumberOfComments() {
        return comments.size();
    }
}
