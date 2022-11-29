package com.bookaholic.demo.service;

import java.util.List;
import java.util.UUID;

import com.bookaholic.demo.entity.BookEntity;

public interface EnrollmentService {
	List<BookEntity> queryAllEnrollments(UUID studentId);
}
