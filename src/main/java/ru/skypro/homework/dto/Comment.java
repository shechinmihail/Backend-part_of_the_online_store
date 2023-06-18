package ru.skypro.homework.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Комментарий к объявлению
 */
@Data
public class Comment {

    /**
     * id автора комментария
     */
    private int author;

    /**
     * Ссылка на изображение автора комментария
     */
    private String authorImage;

    /**
     * Имя создателя комментария
     */
    private String authorFirstName;

    /**
     * Дата и время создания комментария в миллисекундах с 00:00:00 01.01.1970
     */
    private LocalDateTime createdAt;

    /**
     * id комментария
     */
    private int pk;

    /**
     * Текст комментария
     */
    private String text;
}
