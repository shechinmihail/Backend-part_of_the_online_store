package ru.skypro.homework.dto;

import lombok.Data;

/**
 * Пользователь
 */
@Data
public class User {

    /**
     * id пользователя
     */
    private Integer id;

    /**
     * Логин пользователя
     */
    private String email;

    /**
     * Имя пользователя
     */
    private String firstName;

    /**
     * Фамилия пользователя
     */
    private String lastName;

    /**
     * Телефон пользователя
     */
    private String phone;

    /**
     * Ссылка на изображение пользователя
     */
    private String image;
}
