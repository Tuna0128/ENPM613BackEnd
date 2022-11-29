package com.bookaholic.demo.util;

import com.bookaholic.demo.model.EnrollmentPayload;

public class ValidateEnrollment {
	
	public static String validate(EnrollmentPayload enrollment) {
		//TODO
		if(enrollment.getEnrollId() == null) {
			return "No Enrollment ID";
		}
		if(enrollment.getStudentId() == null) {
			return "No Student ID";
		}
		if(enrollment.getBookId() == null) {
			return "No Book ID";
		}
		if(enrollment.getStudentId() == null) {
			return "No Student ID";
		}
		return "Success";
		
	}
}
