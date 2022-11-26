package com.bookaholic.demo.service;

import com.bookaholic.demo.entity.BookEntity;
import com.bookaholic.demo.entity.DiscussionEntity;
import com.bookaholic.demo.entity.EnrollmentEntity;

import java.util.List;
import java.util.UUID;

public interface BookService {
    List<BookEntity> queryAllBooks();
    BookEntity queryBookById(UUID bookId);
    Boolean saveBook(BookEntity bookEntity);
    Boolean isEnrolled(UUID studentId, UUID bookId);
    Boolean saveEnrollment(EnrollmentEntity enrollEntity);
    List<DiscussionEntity> queryDiscussionByBookId(UUID bookId);
    Boolean saveDiscussion(DiscussionEntity discussionEntity);
}
