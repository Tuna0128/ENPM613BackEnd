package com.bookaholic.demo.controller;

import com.bookaholic.demo.entity.UserEntity;
import com.bookaholic.demo.model.UserPayload;
import com.bookaholic.demo.model.FilePayload;
import com.bookaholic.demo.service.AccountService;

import java.util.UUID;
import java.util.Map;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
@RequestMapping("/file")
public class FileOperations {
	private final AccountService accountService;
	
	public FileOperations(AccountService accountService) {
		this.accountService = accountService;
	}
	
	@PostMapping(value="/upload", consumes= {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<?> upload(@RequestParam("userId") UUID uuid, FilePayload filePayload){
		MultipartFile file = filePayload.getFile();
		if(file.isEmpty()) {
			Map<String, String> body = new HashMap<String, String>();
			body.put("message", "Please select a file to upload");
			return new ResponseEntity<>(body, HttpStatus.ACCEPTED);
		}
		UserEntity user = accountService.queryUserByUserId(uuid);
		if(user==null || user.getUserId()==null) {
			Map<String, String> body = new HashMap<String, String>();
			body.put("message", "no user found with given uuid");
			return new ResponseEntity<>(body, HttpStatus.NOT_ACCEPTABLE);
		}
		String rootPath = System.getProperty("user.dir");
		String filePath = rootPath+"/userUpload/";
		File directory = new File(filePath);
		if(!directory.exists()) {
			directory.mkdir();
		}
		filePath += user.getUserId()+"/";
		directory = new File(filePath);
		if(!directory.exists()) {
			directory.mkdir();
		}
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(filePath + file.getOriginalFilename());
			Files.write(path, bytes);
		}catch(IOException e) {
			e.printStackTrace();
			Map<String, String> body = new HashMap<String, String>();
			body.put("message", "IOException occured");
			return new ResponseEntity<>(body, HttpStatus.NOT_ACCEPTABLE);
		}
		Map<String, String> body = new HashMap<String, String>();
		body.put("message", "File uploaded successfully");
		body.put("fileLink", filePath);
		return new ResponseEntity<>(body, HttpStatus.ACCEPTED);
	}
}
