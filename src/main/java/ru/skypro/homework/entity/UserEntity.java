package ru.skypro.homework.entity;

import lombok.*;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Класс User, представляет сущность пользователя
 */
@Entity
@Data
@Table(name = "users")
public class UserEntity {

    /**
     * Идентификационный номер (id) пользователя
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    /**
     * Имя пользователя
     */
    private String firstName;

    /**
     * Фамилия пользователя
     */
    private String lastName;

    /**
     * Номер телефона пользователя
     */
    private String phone;

    /**
     * Роль пользователя
     */
    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * Пароль пользователя
     */
    private String password;

    /**
     * Логин пользователя
     */
    private String email;

    /**
     * Изображение пользователя
     */
    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;
}
