package com.endava.aminternship.service.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface ImageProcessorService {
	public boolean isValidImage(MultipartFile f);
	public String uploadImage(MultipartFile f);

}
