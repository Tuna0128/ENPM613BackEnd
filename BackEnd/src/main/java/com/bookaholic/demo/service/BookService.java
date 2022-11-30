package com.bookaholic.demo.service;

import com.bookaholic.demo.entity.BookEntity;
import com.bookaholic.demo.entity.DiscussionEntity;
import com.bookaholic.demo.entity.EnrollmentEntity;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface BookService {
    List<BookEntity> queryAllBooks();
    BookEntity queryBookById(UUID bookId);
    List<BookEntity> queryBookByTeacherId(UUID teacherId);
    Boolean saveBook(BookEntity bookEntity);
    void deleteBook(BookEntity bookEntity);
    Boolean isEnrolled(UUID studentId, UUID bookId);
    Boolean saveEnrollment(EnrollmentEntity enrollEntity);
    List<Map<String, Object>> queryDiscussionByBookId(UUID bookId);
    Boolean saveDiscussion(DiscussionEntity discussionEntity);
	List<BookEntity> queryAllEnrollments(UUID studentId);
	boolean deleteEnrollments(UUID studentId, UUID bookId);
}
