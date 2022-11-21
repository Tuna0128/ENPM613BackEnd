package com.bookaholic.demo.entity;

import com.bookaholic.demo.model.BookPayload;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "book")
public class BookEntity {
    @Id
    @GeneratedValue
    @Column(name="book_id",columnDefinition="uuid")
    private UUID bookId;

    @Column(name="teacher_Id")
    private UUID teacherId;

    @Column(name="title", columnDefinition="TEXT")
    private String title;

    @Column(name = "sub_title", columnDefinition="TEXT")
    private String subTitle;

    @Column(name = "author", columnDefinition="TEXT")
    private String author;

    @Column(name = "description", columnDefinition="TEXT")
    private String description;

    @Column(name = "categories", columnDefinition="TEXT")
    private String categories;

    @Column(name = "year")
    private Integer year;

    @Column(name = "publisher", columnDefinition = "TEXT")
    private  String publisher;

    @Column(name = "pages")
    private Integer pages;

    @Column(name = "isbn", columnDefinition = "TEXT")
    private String isbn;

    @Column(name = "file_link", columnDefinition = "TEXT")
    private String fileLink;

    @Column(name = "cover_link", columnDefinition = "TEXT")
    private String coverLink;

    public BookEntity() {
    }

    public BookEntity(UUID bookId, UUID teacherId, String title, String subTitle, String author, String description, String categories, Integer year, String publisher, Integer pages, String isbn, String fileLink, String coverLink) {
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

    public BookEntity(BookPayload bookPayload){
        bookId = bookPayload.getBookId();
        title = bookPayload.getTitle();
        teacherId = bookPayload.getTeacherId();
        subTitle = bookPayload.getSubTitle();
        author = bookPayload.getAuthor();
        description = bookPayload.getDescription();
        categories = bookPayload.getCategories();
        year = bookPayload.getYear();
        publisher = bookPayload.getPublisher();
        pages = bookPayload.getPages();
        isbn = bookPayload.getIsbn();
        fileLink = bookPayload.getFileLink();
        coverLink = bookPayload.getCoverLink();
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
