package com.bookaholic.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class BookaholicApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookaholicApplication.class, args);
	}

}
