package ru.skypro.homework.dto;

import lombok.Data;

/**
 * Сущность для хранения объявления
 */
@Data
public class FullAds {

    /**
     * id объявления
     */
    private int pk;

    /**
     * Имя автора объявления
     */
    private String authorFirstName;

    /**
     * Фамилия автора объявления
     */
    private String authorLastName;

    /**
     * Описание объявления
     */
    private String description;

    /**
     * Логин автора объявления
     */
    private String email;

    /**
     * Ссылка на изображение объявления
     */
    private String image;

    /**
     * Телефон автора объявления
     */
    private String phone;

    /**
     * Цена товара в объявлении
     */
    private int price;

    /**
     * Заголовок объявления
     */
    private String title;
}
