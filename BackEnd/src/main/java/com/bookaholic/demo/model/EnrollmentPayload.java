package com.bookaholic.demo.model;

import java.util.Date;
import java.util.UUID;

public class EnrollmentPayload {
	private UUID enrollId;
    private UUID studentId;
    private UUID bookId;
    private Date enrollDate;
    
    public EnrollmentPayload() {
    }
    
    public EnrollmentPayload(UUID enrollId, UUID studentId, UUID bookId, Date enrollDate) {
    	this.setEnrollId(enrollId);
        this.setStudentId(studentId);
        this.setBookId(bookId);
        this.setEnrollDate(enrollDate);
    }

	public UUID getEnrollId() {
		return enrollId;
	}

	public void setEnrollId(UUID enrollId) {
		this.enrollId = enrollId;
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

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}
}
