package com.danielwindel.util.photos;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class PhotoService {
    private final CloudinaryApiService cloudinaryApiService;

    public String uploadPhoto(MultipartFile photo) throws IOException {
        return cloudinaryApiService.uploadPhoto(photo);
    }
}