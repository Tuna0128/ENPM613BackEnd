package com.bookaholic.demo.controller;

import com.bookaholic.demo.entity.EnrollmentEntity;
import com.bookaholic.demo.model.EnrollmentPayload;
import com.bookaholic.demo.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/student")
public class StudentOperations {
    private final BookService bookService;

    public StudentOperations(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/allbooks")
    public ResponseEntity<?> getAllBooks(){
        return new ResponseEntity<>(bookService.queryAllBooks(),HttpStatus.OK);
    }


    @PostMapping("/books/enroll")
    public ResponseEntity<?> enrollBook(@RequestBody EnrollmentPayload enrollment){
    	if (enrollment.getBookId() == null || enrollment.getStudentId() == null){
            Map<String, String> body = new HashMap<String, String>();
            body.put("message", "Empty IDs.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    	if(bookService.isEnrolled(enrollment.getStudentId(), enrollment.getBookId())) {
    		Map<String, String> body = new HashMap<String, String>();
    		body.put("message", "Already enrolled.");
    		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    	}

        if(bookService.saveEnrollment(new EnrollmentEntity(enrollment))){
        	return new ResponseEntity<>(HttpStatus.OK);
        } else {
        	Map<String, String> body = new HashMap<String, String>();
        	body.put("message", "Oops!Something went wrong.");
            return new ResponseEntity<>(body, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
    

    @PostMapping("/books/unenroll")
    public ResponseEntity<?> unenrollBook(@RequestBody EnrollmentPayload enrollment){
        if (enrollment.getBookId() == null || enrollment.getStudentId() == null){
            Map<String, String> body = new HashMap<String, String>();
            body.put("message", "Empty IDs.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(bookService.isEnrolled(enrollment.getStudentId(), enrollment.getBookId())) {
            bookService.deleteEnrollments(enrollment.getStudentId(), enrollment.getBookId());
            return new ResponseEntity<>(HttpStatus.OK);
        } else{
            Map<String, String> body = new HashMap<String, String>();
            body.put("message", "Has not enrolled");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/books/isenrolled/{studentId}/{bookId}")
    public ResponseEntity<?> download(@PathVariable UUID studentId, @PathVariable UUID bookId){
        return new ResponseEntity<>(bookService.isEnrolled(studentId, bookId),HttpStatus.OK);
    }

    @GetMapping("/all_enrolled_books/{studentId}")
    public ResponseEntity<?> download(@PathVariable UUID studentId){
        return new ResponseEntity<>(bookService.queryBooksByStudentId(studentId),HttpStatus.OK);
    }
}
