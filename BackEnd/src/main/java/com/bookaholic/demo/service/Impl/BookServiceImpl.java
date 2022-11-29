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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.TransactionRequiredException;

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
    public Boolean saveBook(BookEntity bookEntity) {
        if(bookEntity == null || bookEntity.getIsbn() == null){
            return false;
        }
        BookEntity existedBook = bookRepository.findByIsbn(bookEntity.getIsbn());
        if(existedBook != null){
            bookEntity.setBookId(existedBook.getBookId());
        }
        bookRepository.save(bookEntity);
        return true;
    }

    @Override
    public Boolean isEnrolled(UUID studentId, UUID bookId) {
        EnrollmentEntity enrollEntity = enrollRepository.findByStudentIdAndBookId(studentId, bookId);
        return enrollEntity == null;
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
	public List<BookEntity> queryAllEnrollments(UUID studentId) {
		List<EnrollmentEntity> allEnrollments = enrollRepository.findAllByStudentId(studentId);
		List<BookEntity> enrolledBooks = new ArrayList<BookEntity>(); 	
		for (EnrollmentEntity e : allEnrollments) {
			enrolledBooks.add(bookRepository.findByBookId(e.getBookId()));
		}
		return enrolledBooks;
	}
	
	@Override
	public boolean deleteEnrollments(UUID studentId, UUID bookId) {
		if(enrollRepository.findByStudentIdAndBookId(studentId, bookId)!=null) {
			//TODO find a way to dissolve no return value problem
			enrollRepository.delete(enrollRepository.findByStudentIdAndBookId(studentId, bookId)); 
			return true;
		}
		return false;
	}
	
	public boolean deleteEnrollments(EnrollmentEntity enrollEntity) {
		if(enrollRepository.findByStudentIdAndBookId(enrollEntity.getStudentId(), enrollEntity.getBookId())!=null) {
			enrollRepository.delete(enrollEntity);
			return true;
		}
		return false;
	}

    @Override
    public List<DiscussionEntity> queryDiscussionByBookId(UUID bookId) {
        return discussionRepository.findByBookId(bookId);
    }

    @Override
    public Boolean saveDiscussion(DiscussionEntity discussionEntity) {
        if(discussionEntity == null){
            return false;
        }
        discussionEntity.setCreatingTime(new Date());
        discussionRepository.save(discussionEntity);
        return true;
    }
}
