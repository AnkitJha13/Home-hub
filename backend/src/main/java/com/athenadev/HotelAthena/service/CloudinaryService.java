package com.athenadev.HotelAthena.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.athenadev.HotelAthena.exception.OurException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;

    // Inject Cloudinary credentials from application.properties
    public CloudinaryService(@Value("${cloudinary.cloud.name}") String cloudName,
                             @Value("${cloudinary.api.key}") String apiKey,
                             @Value("${cloudinary.api.secret}") String apiSecret) {
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret));
    }

    // Upload the image to Cloudinary
    public String saveImage(MultipartFile photo) {
        try {
            // Convert MultipartFile to byte array for Cloudinary upload
            Map uploadResult = cloudinary.uploader().upload(photo.getBytes(),
                    ObjectUtils.asMap("resource_type", "auto"));

            // Return the Cloudinary URL
            return uploadResult.get("secure_url").toString();
        } catch (IOException e) {
            e.printStackTrace();
            throw new OurException("Unable to upload image to Cloudinary");
        }
    }
}
