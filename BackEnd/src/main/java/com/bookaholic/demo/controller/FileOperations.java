package com.bookaholic.demo.controller;

import com.bookaholic.demo.entity.UserEntity;
import com.bookaholic.demo.model.FilePayload;
import com.bookaholic.demo.service.AccountService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


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

		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		if (filename.contains("..")) {
			Map<String, String> body = new HashMap<String, String>();
			body.put("message", "Invalid filename");
			return new ResponseEntity<>(body, HttpStatus.NOT_ACCEPTABLE);
		}

//		get file directory
		Path uploadLocation = Paths.get("").resolve("userUpload").resolve(user.getUserId().toString());

		try (InputStream inputStream = file.getInputStream()) {
			Files.createDirectories(uploadLocation);
			Files.copy(inputStream, uploadLocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
			Map<String, String> body = new HashMap<String, String>();
			body.put("message", "Cannot Save file");
			return new ResponseEntity<>(body, HttpStatus.NOT_ACCEPTABLE);
		}

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/file/download/"+user.getUserId().toString()+"/")
				.path(filename)
				.toUriString();

		Map<String, String> body = new HashMap<String, String>();
		body.put("message", "File uploaded successfully");
		body.put("fileLink", fileDownloadUri);
		return new ResponseEntity<>(body, HttpStatus.ACCEPTED);
	}

	@GetMapping("/download/{userId}/{filename:.+}")
	public ResponseEntity<?> download(@PathVariable String userId, @PathVariable String filename){
		Path filePath = Paths.get("").resolve("userUpload").resolve(userId).resolve(filename).normalize();
		Resource file = null;
		try {
			file = new UrlResource(filePath.toUri());
			if(!file.exists()){
				throw new MalformedURLException("File not found " + filename);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			Map<String, String> body = new HashMap<String, String>();
			body.put("message", "Invalid file");
			return new ResponseEntity<>(body, HttpStatus.NOT_ACCEPTABLE);
		}

		// Try to determine file's content type
		String contentType= URLConnection.guessContentTypeFromName(filename);

		// Fallback to the default content type if type could not be determined
		if(contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
}
