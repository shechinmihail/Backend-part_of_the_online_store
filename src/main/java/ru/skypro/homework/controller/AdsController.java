package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;

/**
 * AdsController
 * Контроллер для обработки REST-запросов, в данном случае добавления, удаления, редактирования и поиска объявлений
 *
 * @see
 */
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("ads")
public class AdsController {

    /**
     * Функция получения всех объявлений, хранящихся в базе данных
     *
     * @return возвращает все объявления
     */
    @Operation(
            summary = "Получение списка всех объявлений",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Получены все объявления",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseWrapperAds.class)
                            )
                    )
            }
    )
    @GetMapping(path = "all")  //GET http://localhost:8080/abs/all
    public ResponseEntity<ResponseWrapperAds> getAllAds() {
        return ResponseEntity.ok().build();
    }

    /**
     * Функция добавление объявления
     *
     * @param properties характеристики объявления
     * @param image      картинка объявления
     * @return возвращает объект, содержащий данные созданного объявления
     */
    @Operation(
            summary = "Функция добавления объявления в базу данных",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Добавление объявления в базу данных",
                            content = @Content(
                                    mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                                    schema = @Schema(implementation = Ads.class)
                            )
                    )

            }
    )
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE) //POST http://localhost:8080/abs
    public ResponseEntity<Ads> createAds(@RequestPart MultipartFile properties,
                                         @RequestPart MultipartFile image) {
        return ResponseEntity.ok().build();
    }

    /**
     * Функция получения объявления по идентификатору (id), хранящихся в базе данных
     *
     * @param id идентификатор объявления, не может быть null
     * @return возвращает объявление по идентификатору (id)
     */
    @Operation(
            summary = "Функция получения объявления по идентификатору из базы данных",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Объявление найдено",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Ads.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Объявление не найдено",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Ads.class)
                            )
                    )
            }
    )
    @GetMapping("{id}") //GET http://localhost:8080/abs/{id}
    public ResponseEntity<FullAds> getAds(@PathVariable int id) {
        return ResponseEntity.ok().build();
    }

    /**
     * Функция удаления объявления по идентификатору (id), хранящихся в базе данных
     *
     * @param id идентификатор объявления, не может быть null
     */
    @Operation(
            summary = "Удаление  объявления из базы данных по идентификатору (id)",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Объявление удаленно",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Ads.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Объявление с таким id, не найдено",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Ads.class))
                    )
            }
    )
    @DeleteMapping("{id}") //DELETE http://localhost:8080/abs/{id}
    public ResponseEntity<Void> deleteAds(@PathVariable int id) {
        return ResponseEntity.ok().build();
    }

    /**
     * Функция обновления объявления по идентификатору (id), хранящихся в базе данных
     *
     * @param id        идентификатор объявления, не может быть null
     * @param createAds данные объявления
     * @return возвращает обновленное объявление по идентификатору (id)
     */
    @Operation(
            summary = "Функция обновления объявления по идентификатору из базы данных",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Объявление обновлено",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Ads.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Объявление не найдено",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Ads.class)
                            )
                    )
            }
    )
    @PatchMapping("{id}") //PATCH http://localhost:8080/abs/{id}
    public ResponseEntity<Ads> updateAds(@PathVariable int id,
                                         @RequestBody CreateAds createAds) {
        return ResponseEntity.ok().build();
    }

    /**
     * Функция получения объявления авторизованного пользователя, хранящихся в базе данных
     *
     * @return возвращает объявление авторизованного пользователя
     */
    @Operation(
            summary = "Функция получения объявления авторизованного пользователя из базы данных",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Объявление найдено",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Ads.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Объявление не найдено",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Ads.class)
                            )
                    )
            }
    )
    @GetMapping("me") //GET http://localhost:8080/abs/me
    public ResponseEntity<ResponseWrapperAds> getAdsMe() {
        return ResponseEntity.ok().build();
    }

    /**
     * Функция обновления картинки объявления
     *
     * @param id    идентификатор объявления, не может быть null
     * @param image картинка объявления
     * @return обновленная картинка объявления
     */
    @Operation(
            summary = "Обновление картинки объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Картинка объявления обновлена",
                            content = @Content(
                                    mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                                    schema = @Schema(implementation = Ads.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Объявление не найдено",
                            content = @Content(
                                    mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                                    schema = @Schema(implementation = Ads.class))
                    )
            }
    )
    @PatchMapping(value = "{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    //PATCH http://localhost:8080/abs/{id}/image
    public ResponseEntity<String> updateImage(@PathVariable int id,
                                                 @RequestPart MultipartFile image) {
        return ResponseEntity.ok().build();
    }
}
