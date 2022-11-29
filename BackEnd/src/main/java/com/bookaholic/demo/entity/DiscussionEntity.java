package com.bookaholic.demo.entity;

import javax.persistence.*;

import com.bookaholic.demo.model.DiscussionPayload;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "discussion")
public class DiscussionEntity {
    @Id
    @GeneratedValue
    @Column(name="discussion_id", columnDefinition="uuid")
    private UUID discussionId;

    @Column(name = "book_id")
    private UUID bookId;

    @Column(name = "content", columnDefinition="TEXT")
    private String content;

    @Column(name = "creating_time")
    private Date creatingTime;

    @Column(name = "creator")
    private UUID creator;

    public DiscussionEntity() {
    }

    public DiscussionEntity(UUID discussionId, UUID bookId, String content, Date creatingTime, UUID creator) {
        this.discussionId = discussionId;
        this.bookId = bookId;
        this.content = content;
        this.creatingTime = creatingTime;
        this.creator = creator;
    }
    
    public DiscussionEntity(DiscussionPayload discussionPayload) {
    	this.bookId = discussionPayload.getBookId();
    	this.content = discussionPayload.getContent();
    	this.creatingTime = new Date();
    	this.creator = discussionPayload.getCreator();
    }

    public UUID getDiscussionId() {
        return discussionId;
    }

    public void setDiscussionId(UUID discussionId) {
        this.discussionId = discussionId;
    }

    public UUID getBookId() {
        return bookId;
    }

    public void setBookId(UUID bookId) {
        this.bookId = bookId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatingTime() {
        return creatingTime;
    }

    public void setCreatingTime(Date creatingTime) {
        this.creatingTime = creatingTime;
    }

    public UUID getCreator() {
        return creator;
    }

    public void setCreator(UUID creator) {
        this.creator = creator;
    }
}
