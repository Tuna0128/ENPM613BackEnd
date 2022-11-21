package com.bookaholic.demo.model;

import java.util.UUID;

public class DiscussionPayload {
    private UUID bookId;
    private String content;
    private UUID creator;

    public DiscussionPayload(UUID bookId, String content, UUID creator) {
        this.bookId = bookId;
        this.content = content;
        this.creator = creator;
    }

    public DiscussionPayload() {
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

    public UUID getCreator() {
        return creator;
    }

    public void setCreator(UUID creator) {
        this.creator = creator;
    }
}
