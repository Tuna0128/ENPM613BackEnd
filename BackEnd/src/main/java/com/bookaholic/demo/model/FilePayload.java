package com.bookaholic.demo.model;

import org.springframework.web.multipart.MultipartFile;

public class FilePayload {
	private MultipartFile file;
	
	public FilePayload(MultipartFile file) {
		this.file = file;
	}
	
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile m) {
		file = m;
	}
}
