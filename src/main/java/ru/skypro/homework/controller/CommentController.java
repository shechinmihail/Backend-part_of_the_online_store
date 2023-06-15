package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateComment;
import ru.skypro.homework.dto.ResponseWrapperComment;
import ru.skypro.homework.service.impl.CommentServiceImpl;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * CommentController
 * Контроллер для обработки REST-запросов, добавление, удаление, редактирование и поиска комментарии
 * @see
 */

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("ads")
public class CommentController {

    private final CommentServiceImpl commentService;

    /**
     * Получить комментарии объявления
     * @param id идентификатор объявления, не может быть null
     * @return комментарии
     */
    @Operation(
            summary = "Получить комментарии объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "ОК",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Integer.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Integer.class))
                    )
            }
    )
    @GetMapping("{id}/comments")
    public ResponseEntity<ResponseWrapperComment> getComments (@PathVariable int id) {
        ResponseWrapperComment comment = new ResponseWrapperComment((List< Comment >) commentService.getComments(id));
        return ResponseEntity.ok(comment);
    }

    /**
     * Добавить комментарий к объявлению
     * @param id идентификатор объявления, не может быть null
     * @param createComment данные комментария
     * @param authentication авторизованный пользователь
     * @return возвращает объект, содержащий данные созданного комментария
     */
    @Operation(
            summary = "Добавить комментарий к объявлению",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "ОК",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Integer.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Integer.class))
                    )
            }
    )
    @PostMapping("{id}/comments")
    public ResponseEntity<Comment> addComment (@PathVariable int id,
                                         @RequestPart("properties") @NotNull CreateComment createComment,
                                         @NonNull Authentication authentication) {
        return ResponseEntity.ok().build();
    }

    /**
     * Обновить комментарий
     * @param commentId идентификатор комментария, не может быть null
     * @param createComment обновленный комментарий
     * @param authentication авторизованный пользователь
     * @return обновленный комментарий
     */
    @Operation(
            summary = "Обновить комментарий",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "ОК",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Integer.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Integer.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Integer.class))
                    )
            }
    )
    @PatchMapping("{adId}/comments/{commentId}")
    public ResponseEntity<Comment> updateComment (@PathVariable int commentId,
                                            @RequestBody CreateComment createComment,
                                            Authentication authentication) {
        return ResponseEntity.ok(commentService.updateComment(commentId,createComment,authentication));
    }

    /**
     * Удалить комментарий
     * @param commentId идентификатор комментария, не может быть null
     * @param  authentication авторизованный пользователь
     *
     */
    @Operation(
            summary = "Удалить комментарий",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "ОК",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Integer.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Integer.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Integer.class))
                    )
            }
    )
    @DeleteMapping("{adId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment (@PathVariable int commentId,
                                               Authentication authentication){
        commentService.deleteComment(commentId,authentication);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
