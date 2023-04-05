package com.danielwindel.util.photos;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class CloudinaryApiService {
    private final Cloudinary cloudinary;

    public String uploadPhoto(MultipartFile photo) throws IOException {
        Map<String, Object> result = cloudinary.uploader().upload(photo.getBytes(), ObjectUtils.emptyMap());
        return result.get("secure_url").toString();
    }
}
