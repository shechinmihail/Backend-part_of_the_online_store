package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateComment;

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

    @GetMapping("{id}/comments")
    public ResponseEntity<?> getComments (@PathVariable int id) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("{id}/comments")
    public ResponseEntity<?> addComment (@PathVariable int id, @RequestBody CreateComment createComment) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{adId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment (@PathVariable int adId,
                                               @PathVariable int commentId) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("{adId}/comments/{commentId}")
    public ResponseEntity<?> updateComment (@PathVariable int adId,
                                         @PathVariable int commentId,
                                            @RequestBody Comment comment) {
        return ResponseEntity.ok().build();
    }
}
