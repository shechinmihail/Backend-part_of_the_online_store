package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Класс Comment, представляет сущность комментария
 */

@Entity
@Data
@Table(name = "comment")
public class CommentEntity {
    /**
     * Идентификационный номер (id) комментария
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * Идентификационный номер (id) объявления
     */
    @Column(name = "ads_id")
    private Integer adsId;

    /**
     * Идентификационный номер (id) автора
     */
    @Column(name = "author_id")
    private Integer author;

    /**
     * Текст комментария
     */
    @Column(name = "text")
    private String text;

    /**
     * Дата и время создания комментария
     */
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
