package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Класс Ad, представляет сущность объявления
 */
@Entity
@Data
@Table(name = "ads")
public class Ads {

    /**
     * Идентификационный номер (id) объявления
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Описание объявления
     */
    private String description;

    /**
     * Цена товара в объявлении
     */
    private int price;

    /**
     * Заголовок объявления
     */
    private String title;

    /**
     * id автора объявления
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    /**
     * Изображение пользователя
     */
    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;

}
