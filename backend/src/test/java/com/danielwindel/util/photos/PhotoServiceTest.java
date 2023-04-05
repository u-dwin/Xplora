package com.danielwindel.util.photos;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PhotoServiceTest {

    CloudinaryApiService cloudinaryApiService = mock(CloudinaryApiService.class);

    PhotoService photoService = new PhotoService(cloudinaryApiService);

    @Test
    void isUploadPhotoReturningUrlWithPhoto() throws IOException {
        byte[] photoData = "test photo data".getBytes();
        MultipartFile photo = new MockMultipartFile("photo.jpg", photoData);
        String expectedPhotoUrl = "https://example.com/photo.jpg";

        when(cloudinaryApiService.uploadPhoto(photo)).thenReturn(expectedPhotoUrl);

        String actualPhotoUrl = photoService.uploadPhoto(photo);

        assertEquals(expectedPhotoUrl, actualPhotoUrl);
    }
}




