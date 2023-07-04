package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.ImageEntity;

import java.io.IOException;

public interface ImageService {
    ImageEntity downloadImage(MultipartFile image) throws IOException;
    void deleteImage(Integer id);
    byte[] getImage(Integer id);
}
