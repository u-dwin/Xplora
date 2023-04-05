package com.danielwindel.util.photos;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/photos")
public class PhotoController {
    private final PhotoService photoService;

    PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @PostMapping("/add")
    public String uploadPhoto(MultipartFile photo) throws IOException {
        return photoService.uploadPhoto(photo);
    }

}
