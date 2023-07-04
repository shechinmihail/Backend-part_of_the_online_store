package ru.skypro.homework.entity;

import lombok.*;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Класс UserEntity, представляет сущность пользователя
 */
@Entity
@Data
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
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
     * Телефон пользователя
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
     * Ссылка на изображение пользователя
     */
    @OneToOne
    @JoinColumn(name = "image_id")
    private ImageEntity imageEntity;
}
