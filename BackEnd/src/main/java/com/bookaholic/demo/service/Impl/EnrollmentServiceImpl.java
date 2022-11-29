package com.bookaholic.demo.service.Impl;

import com.bookaholic.demo.entity.BookEntity;
import com.bookaholic.demo.entity.EnrollmentEntity;
import com.bookaholic.demo.repository.BookRepository;
import com.bookaholic.demo.repository.DiscussionRepository;
import com.bookaholic.demo.repository.EnrollRepository;
import com.bookaholic.demo.repository.UserRepository;
import com.bookaholic.demo.service.EnrollmentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
	private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final EnrollRepository enrollRepository;
    private final DiscussionRepository discussionRepository;
	
	public EnrollmentServiceImpl(UserRepository userRepository, BookRepository bookRepository, EnrollRepository enrollRepository, DiscussionRepository discussionRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.enrollRepository = enrollRepository;
        this.discussionRepository = discussionRepository;
    }

	@Override
	public List<BookEntity> queryAllEnrollments(UUID studentId) {
		List<EnrollmentEntity> allEnrollments = enrollRepository.findAllByStudentId(studentId);
		List<BookEntity> enrolledBooks = new ArrayList<BookEntity>(); 
		
		for (EnrollmentEntity e : allEnrollments) {
			System.out.println("BookID:" + e.getBookId());
			System.out.println(e.getEnrollDate().toString());
			enrolledBooks.add(bookRepository.findByBookId(e.getBookId()));
		}
		return enrolledBooks;
	}
}
