package com.bookaholic.demo.controller;

import com.bookaholic.demo.entity.EnrollmentEntity;
import com.bookaholic.demo.model.EnrollmentPayload;
import com.bookaholic.demo.service.BookService;
import com.bookaholic.demo.service.EnrollmentService;
import com.bookaholic.demo.util.ValidateEnrollment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/student")
public class StudentOperations {
    private final EnrollmentService enrollmentService;
    private final BookService bookService;

    public StudentOperations(EnrollmentService enrollmentService, BookService bookService) {
        this.enrollmentService = enrollmentService;
        this.bookService = bookService;
    }

    @GetMapping("/allbooks")
    public ResponseEntity<?> getAllBooks(){
        return new ResponseEntity<>(bookService.queryAllBooks(),HttpStatus.OK);
    }
    
    //TODO check if BookService works
    @PostMapping("/books/enroll")
    public ResponseEntity<?> enrollBook(@RequestBody EnrollmentPayload enrollment){
    	System.out.println("EID:" + enrollment.getEnrollId() + 
    				" SID:" + enrollment.getStudentId() + 
    				" BID:" + enrollment.getBookId());
    	String validation = ValidateEnrollment.validate(enrollment);
    	if (validation!="Success") {
    		Map<String, String> body = new HashMap<String, String>();
    		body.put("message", validation);
    		return new ResponseEntity<>(body, HttpStatus.NOT_ACCEPTABLE);
    	}
    	if(!bookService.isEnrolled(enrollment.getStudentId(), enrollment.getBookId())) {
    		Map<String, String> body = new HashMap<String, String>();
    		body.put("message", "Already enrolled.");
    		return new ResponseEntity<>(body, HttpStatus.NOT_ACCEPTABLE);
    	}
        if(bookService.saveEnrollment(new EnrollmentEntity(enrollment))){
        	Map<String, String> body = new HashMap<String, String>();
        	body.put("message", "Successfully enrolled!");
            return new ResponseEntity<>(body, HttpStatus.OK);
        } else {
        	Map<String, String> body = new HashMap<String, String>();
        	body.put("message", "Oops!Something went wrong.");
            return new ResponseEntity<>(body, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
    
    //TODO
    @PostMapping("/books/unenroll")
    public ResponseEntity<?> unenrollBook(@RequestBody EnrollmentPayload enrollment){
    	if (bookService.deleteEnrollments(enrollment.getStudentId(), enrollment.getBookId())) {
    		Map<String, String> body = new HashMap<String, String>();
        	body.put("message", "Successfully deleted!");
            return new ResponseEntity<>(body, HttpStatus.OK);
    	} else {
        	Map<String, String> body = new HashMap<String, String>();
        	body.put("message", "Oops!Something went wrong.");
            return new ResponseEntity<>(body, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
    
    //TODO remove if not necessary
    @PostMapping("/books/unenrollbooks")
    public ResponseEntity<?> unenrollBooks(){
        return new ResponseEntity<>(bookService.queryAllBooks(),HttpStatus.OK);
    }
    
    //TODO add validation
    @GetMapping("/books/viewenrolled")
    public ResponseEntity<?> viewEnrolledBook(@RequestParam UUID studentId){
        return new ResponseEntity<>(bookService.queryAllEnrollments(studentId),HttpStatus.OK);
    }

    @GetMapping("/books/isenrolled/{userId}/{bookId}")
    public ResponseEntity<?> download(@PathVariable UUID studentId, @PathVariable UUID bookId){
        return new ResponseEntity<>(bookService.isEnrolled(studentId, bookId),HttpStatus.OK);
    }

}
