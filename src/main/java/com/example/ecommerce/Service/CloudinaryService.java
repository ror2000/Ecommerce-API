package com.example.ecommerce.Service;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import org.springframework.web.multipart.MultipartFile;

@Service
public class CloudinaryService {
	
	private final Cloudinary cloudinary = new Cloudinary();
	
	public String uploadImage(MultipartFile file) throws IOException {
		if(file == null || file.isEmpty()) {
			throw new IllegalArgumentException("Image file is required");
			
		}
		
		Map<?, ?> result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type", "image", "folder", "ecommerce-products"));
		
		return result.get("secure_url").toString();
	}

}
