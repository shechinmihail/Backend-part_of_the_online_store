package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateAds;
import ru.skypro.homework.dto.FullAds;
import ru.skypro.homework.dto.ResponseWrapperAds;

/**
 * AdsController
 * Контроллер для обработки REST-запросов, в данном случае добавления, удаления, редактирования и поиска объявлений
 */
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("ads")
public class AdsController {

    @GetMapping(path = "all")  //GET http://localhost:3000/abs/all
    public ResponseEntity<ResponseWrapperAds> getAllAds() {
        return ResponseEntity.ok().build();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE) //POST http://localhost:3000/abs
    public ResponseEntity<Ads> createAds(@RequestPart MultipartFile properties,
                                         @RequestPart MultipartFile image) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}") //GET http://localhost:3000/abs/{id}
    public ResponseEntity<FullAds> getAds(@PathVariable int id) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}") //DELETE http://localhost:3000/abs/{id}
    public ResponseEntity<Void> deleteAds(@PathVariable int id) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("{id}") //PATCH http://localhost:3000/abs/{id}
    public ResponseEntity<Ads> updateAds(@PathVariable int id,
                                         @RequestBody CreateAds createAds) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("me") //GET http://localhost:3000/abs/me
    public ResponseEntity<ResponseWrapperAds> getMeAds() {
        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE) //PATCH http://localhost:3000/abs/{id}/image
    public ResponseEntity<String> updateImageAds(@PathVariable int id, @RequestPart MultipartFile image) {
        return ResponseEntity.ok().build();
    }
}
