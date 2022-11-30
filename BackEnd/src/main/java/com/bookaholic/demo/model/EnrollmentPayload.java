package com.bookaholic.demo.model;

import java.util.UUID;

public class EnrollmentPayload {
    private UUID studentId;
    private UUID bookId;
    
    public EnrollmentPayload() {
    }

	public EnrollmentPayload(UUID studentId, UUID bookId) {
		this.studentId = studentId;
		this.bookId = bookId;
	}

	public UUID getStudentId() {
		return studentId;
	}

	public void setStudentId(UUID studentId) {
		this.studentId = studentId;
	}

	public UUID getBookId() {
		return bookId;
	}

	public void setBookId(UUID bookId) {
		this.bookId = bookId;
	}
}
