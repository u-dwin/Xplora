package com.danielwindel.util.photos;

import com.google.maps.GeoApiContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class PhotoControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    CloudinaryApiService cloudinaryApiService;

    @MockBean
    GeoApiContext geoApiContext;

    byte[] photoData = "test photo data".getBytes();
    MockMultipartFile multipartFile = new MockMultipartFile(
            "photo",
            "photo.jpg",
            MediaType.IMAGE_JPEG_VALUE,
            photoData
    );

    String expectedPhotoUrl = "https://example.com/photo.jpg";

    @Test
    void isPhotosAddEndpointReturningUrlsCorrectly() throws Exception {
        when(cloudinaryApiService.uploadPhoto(multipartFile)).thenReturn("https://example.com/photo.jpg");

        mockMvc.perform(MockMvcRequestBuilders
                        .multipart("/api/photos/add")
                        .file(multipartFile))
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andExpect(content().string(expectedPhotoUrl));
    }
}




