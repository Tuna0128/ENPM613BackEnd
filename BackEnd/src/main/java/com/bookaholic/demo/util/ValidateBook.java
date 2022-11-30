package com.bookaholic.demo.util;

import java.util.ArrayList;
import com.bookaholic.demo.model.BookPayload;

public class ValidateBook {
	private static final int titleMaxLength = 200;
	private static final int subTitleMaxLength = 200;
	private static final int authorMaxLength = 150;
	private static final int categoriesMaxLength = 150;
	private static final int publisherMaxLength = 150;
	
	public static String validate(BookPayload payload) {
		
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
	    
	    String isbn = payload.getIsbn();
	    if(isbn!=null && !validateISBN(isbn))
	    	return "ISBN is invalid";
	    
	    String fileLink = payload.getFileLink();
	    if(fileLink==null || fileLink=="")
	    	return "Please upload a file";
	    
	    return "Success";
	}
	
	private static boolean validateISBN(String isbn) {
		if (isbn == null){
			return false;
		}
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		for(int i = 0 ; i< isbn.length(); i++){
			if(isbn.charAt(i) <= '9' && isbn.charAt(i)>='0'){
				numbers.add(isbn.charAt(i) - '0');
			}
		}
		int sum = 0;
		if(numbers.size()==10) {
			for(int i=0;i<10;i++)
				sum += numbers.get(i)*(10-i);
			if(sum%11==0) return true;
		}else if(numbers.size()==13) {
			for(int i=0;i<12;i++) {
				if(i%2==0)
					sum += numbers.get(i);
				else
					sum += numbers.get(i)*3;
			}
			int lastDigit = 10 - sum%10;
			if(lastDigit==10)
				lastDigit = 0;
			if(numbers.get(12)==lastDigit) return true;
		}
		return false;
	}
}
