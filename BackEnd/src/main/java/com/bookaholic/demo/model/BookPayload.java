package com.bookaholic.demo.model;

import java.util.UUID;

public class BookPayload {
    private UUID bookId;
    private UUID teacherId;
    private String title;
    private String subTitle;
    private String author;
    private String description;
    private String categories;
    private Integer year;
    private String publisher;
    private Integer pages;
    private String isbn;
    private String fileLink;
    private String coverLink;

    public BookPayload() {
    }

    public BookPayload(UUID bookId, UUID teacherId, String title, String subTitle, String author, String description, String categories, Integer year, String publisher, Integer pages, String isbn, String fileLink, String coverLink) {
        this.bookId = bookId;
        this.teacherId = teacherId;
        this.title = title;
        this.subTitle = subTitle;
        this.author = author;
        this.description = description;
        this.categories = categories;
        this.year = year;
        this.publisher = publisher;
        this.pages = pages;
        this.isbn = isbn;
        this.fileLink = fileLink;
        this.coverLink = coverLink;
    }

    public UUID getBookId() {
        return bookId;
    }

    public void setBookId(UUID bookId) {
        this.bookId = bookId;
    }

    public UUID getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(UUID teacherId) {
        this.teacherId = teacherId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getFileLink() {
        return fileLink;
    }

    public void setFileLink(String fileLink) {
        this.fileLink = fileLink;
    }

    public String getCoverLink() {
        return coverLink;
    }

    public void setCoverLink(String coverLink) {
        this.coverLink = coverLink;
    }

}
