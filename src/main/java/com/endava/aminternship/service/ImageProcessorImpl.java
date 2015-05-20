package com.endava.aminternship.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.endava.aminternship.service.interfaces.ImageProcessorService;

@Service
public class ImageProcessorImpl implements ImageProcessorService{
	private Cloudinary cloudinaryClient = null;
	
	public ImageProcessorImpl(){
		this.cloudinaryClient = new Cloudinary(ObjectUtils.asMap(
		  "cloud_name", "dyb61rwue",
		  "api_key", "959825714278263",
		  "api_secret", "M9bK9Nf6JExDvwiAZQ-QkIZef0s"));
	}
	@Override
	public boolean isValidImage(MultipartFile f) {
		ArrayList<String> validTypes = new ArrayList<String>();
		validTypes.add("image/jpg"); //todo: refactor, make it cute
		validTypes.add("image/jpeg");
		validTypes.add("image/png");
			
		if(validTypes.contains(f.getContentType())){ //todo: check size of file
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String uploadImage(MultipartFile f) {
		String resultUrl = null;
		try {
			Map uploadResult = cloudinaryClient.uploader().upload(multipartToFile(f),  ObjectUtils.emptyMap());
			resultUrl = (String) uploadResult.get("url");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultUrl;
	}
	
	public File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException 
	{
	        File convFile = new File( multipart.getOriginalFilename());
	        multipart.transferTo(convFile);
	        return convFile;
	}

}
