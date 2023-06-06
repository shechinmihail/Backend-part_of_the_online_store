package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.User;

/**
 * UserController
 * Контроллер для обработки REST-запросов, добавление, удаление, редактирование и поиска пользователей
 *
 * @see
 */

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {


    /**
     * Обновление пароля
     *
     * @param newPassword новый пароль
     * @return
     */
    @Operation(
            summary = "Обновление пароля",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "ОК",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = NewPassword.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = NewPassword.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = NewPassword.class))
                    )
            }
    )
    @PostMapping("/set_password")
    public ResponseEntity<?> setPassword(@RequestBody NewPassword newPassword) {
        return ResponseEntity.ok().build();
    }

    /**
     * Получение информации об авторизованном пользователе
     *
     * @param authentication
     * @return
     */
    @Operation(
            summary = "Получить информацию об авторизованном пользователе пароля",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "ОК",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Authentication.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Authentication.class))
                    )
            }
    )
    @GetMapping("/me")
    public ResponseEntity<?> getUser(@RequestBody Authentication authentication) {
        return ResponseEntity.ok().build();
    }

    /**
     * Обновить информацию об авторизованном пользователе
     *
     * @param user
     * @return
     */
    @Operation(
            summary = "Обновить информацию об авторизованном пользователе",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "ОК",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = User.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = User.class))
                    )
            }
    )
    @PatchMapping("/me")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        return ResponseEntity.ok().build();
    }

    /**
     * Обновить аватар авторизованного пользователя
     *
     * @param image
     * @return
     */
    @Operation(
            summary = "Обновить аватар авторизованного пользователя",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "ОК",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = MultipartFile.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = MultipartFile.class))
                    )
            }
    )
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateUserImage(@RequestPart MultipartFile image) {
        return ResponseEntity.ok().build();
    }
}
