package com.bookaholic.demo.service.Impl;

import com.bookaholic.demo.entity.BookEntity;
import com.bookaholic.demo.entity.DiscussionEntity;
import com.bookaholic.demo.entity.EnrollmentEntity;
import com.bookaholic.demo.repository.BookRepository;
import com.bookaholic.demo.repository.DiscussionRepository;
import com.bookaholic.demo.repository.EnrollRepository;
import com.bookaholic.demo.repository.UserRepository;
import com.bookaholic.demo.service.BookService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookServiceImpl implements BookService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final EnrollRepository enrollRepository;
    private final DiscussionRepository discussionRepository;

    public BookServiceImpl(UserRepository userRepository, BookRepository bookRepository, EnrollRepository enrollRepository, DiscussionRepository discussionRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.enrollRepository = enrollRepository;
        this.discussionRepository = discussionRepository;
    }

    @Override
    public List<BookEntity> queryAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public BookEntity queryBookById(UUID bookId) {
        return bookRepository.findByBookId(bookId);
    }
    
    @Override
    public List<BookEntity> queryBookByTeacherId(UUID teacherId){
    	return bookRepository.findByTeacherId(teacherId);
    }

    @Override
    public Boolean saveBook(BookEntity bookEntity) {
        if(bookEntity == null){
            return false;
        }
        bookRepository.save(bookEntity);
        return true;
    }
    @Override
    public void deleteBook(BookEntity bookEntity) {
    	bookRepository.delete(bookEntity);
    }

    @Override
    public Boolean isEnrolled(UUID studentId, UUID bookId) {
        EnrollmentEntity enrollEntity = enrollRepository.findByStudentIdAndBookId(studentId, bookId);
        return enrollEntity != null;
    }

    @Override
    public Long enrolledAmount(UUID bookId) {
        return enrollRepository.countByBookId(bookId);
    }

    @Override
    public Boolean saveEnrollment(EnrollmentEntity enrollEntity) {
        if(enrollEntity == null){
            return false;
        }
        enrollEntity.setEnrollDate(new Date());
        enrollRepository.save(enrollEntity);
        return true;
    }
    
	@Override
	public List<BookEntity> queryBooksByStudentId(UUID studentId) {
		return bookRepository.queryBooksByStudentId(studentId);
	}

	@Override
	public void deleteEnrollments(UUID studentId, UUID bookId) {
        enrollRepository.deleteByStudentIdAndBookId(studentId, bookId);
	}

    @Override
    public List<Map<String, Object>> queryDiscussionByBookId(UUID bookId) {
        return discussionRepository.ListDiscussionsOfOneBook(bookId);
    }

    @Override
    public Boolean saveDiscussion(DiscussionEntity discussionEntity) {
        if(discussionEntity == null){
            return false;
        }
        discussionRepository.save(discussionEntity);
        return true;
    }
}
