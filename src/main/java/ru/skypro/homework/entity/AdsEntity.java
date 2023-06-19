package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Класс AdsEntity, представляет сущность объявления
 */
@Entity
@Data
@Table(name = "ads")
public class AdsEntity {

    /**
     * Идентификационный номер (id) объявления
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
     *  автор объявления
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity author;

    /**
     * Изображение объявления
     */
    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;

}
