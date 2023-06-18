package ru.skypro.homework.dto;

import lombok.Data;

/**
 * Объявление
 */
@Data
public class Ads {

    /**
     * id автора объявления
     */
    private int author;

    /**
     * Ссылка на изображение объявления
     */
    private String image;

    /**
     * id объявления
     */
    private int pk;

    /**
     * Цена товара в объявлении
     */
    private int price;

    /**
     * Заголовок объявления
     */
    private String title;
}
