package com.bookaholic.demo.util;

import com.bookaholic.demo.model.BookPayload;

import java.util.Scanner;
import java.util.ArrayList;

public class ValidateBook {
	private static final int titleMaxLength = 200;
	private static final int subTitleMaxLength = 200;
	private static final int authorMaxLength = 150;
	private static final int categoriesMaxLength = 150;
	private static final int publisherMaxLength = 150;
	
	public static String validate(BookPayload payload) {
		
		if(payload.getBookId()==null) 
			return "Book ID missing";
		
		if(payload.getTeacherId()==null) 
			return "Teacher ID missing";
		
		String title = payload.getTitle();
		if(title==null || title=="") 
			return "Please enter a title for the book";
		else if(title.length()>titleMaxLength) 
			return "Title is too long. Please keep the title length under 255 characters";
		
		String subTitle = payload.getSubTitle();
		if(subTitle!=null && subTitle.length()>subTitleMaxLength) 
			return "Subtitle is too long. Please keep the subtitle length under 255 characters";
		
		String author = payload.getAuthor();
		if(author==null || author=="") 
			return "Please enter the name of the author";
		else if(author.length()>authorMaxLength) 
			return "Name of the author is too long. Please keep the length under 255 characters";
		
		String categories = payload.getCategories();
		if(categories!=null && categories.length()>categoriesMaxLength) 
			return "Categories too long. Please keep the length under 255 characters";
		
	    String publisher = payload.getPublisher();
	    if(publisher!=null && publisher.length()>publisherMaxLength) 
	    	return "Publisher is too long";
	    
	    if(!validateISBN(payload.getIsbn()))
	    	return "ISBN is invalid";
	    
	    String fileLink = payload.getFileLink();
	    if(fileLink==null || fileLink=="")
	    	return "Please upload a file";
	    
	    return "Success";
	}
	
	private static boolean validateISBN(String isbn) {
		Scanner scanner = new Scanner(isbn);
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		while(scanner.hasNext()) {
			char c = scanner.next().charAt(0);
			if(Character.isDigit(c)) numbers.add((int)c);
		}
		scanner.close();
		int sum = 0;
		if(numbers.size()==10) {
			for(int i=0;i<10;i++) 
				sum += numbers.get(i)*(10-i);
			if(sum%11==0) return true;
		}else if(numbers.size()==13) {
			for(int i=0;i<13;i++)
				sum += numbers.get(i)*(13-i);
			if(sum%10==0) return true;
		}
		return false;
	}
}
